package com.app.foodoye.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.app.foodoye.R
import com.app.foodoye.dataclass.fooditems
import com.app.foodoye.interfaces.onfoodMenuItemClick
import com.app.foodoye.ui.fragments.addToCartFragment
import com.app.foodoye.utils.PickerManager
import com.app.foodoye.utils.PickerManager.incrementNumberValue
import com.bumptech.glide.Glide

class addCartAdapter(
    private val context: Context,
    private var addCartList: MutableList<fooditems>,
    val clickListener: onfoodMenuItemClick
): RecyclerView.Adapter<addCartAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): addCartAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.addcart_layout, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: addCartAdapter.ViewHolder, position: Int) {
        //Set the position here!!!
        val currentItem = addCartList[position]

        var incrementNumber=1
        holder.foodTitleTV.text = currentItem.foodTitle
        holder.foodPriceTV.text = "${currentItem.foodPrice} Rs"
        holder.number.text=currentItem.quantity
        incrementNumber= currentItem.quantity!!.toInt()
        val foodUri = currentItem.foodUrl!!.toUri()
        Glide.with(context)
            .load(foodUri)
            .into(holder.foodImageIV)

        holder.deleteItem_IV.setOnClickListener {
            addCartList.removeAt(position)
            notifyDataSetChanged()

            if(addCartList.isNullOrEmpty())
            {
                clickListener.onItemClick(-1)
            }
            Toast.makeText(context,"Item removed from cart",Toast.LENGTH_SHORT).show()
        }

        holder.less.setOnClickListener {
            clickListener.onItemClick(position)
            if(incrementNumber==1 || incrementNumber<0)
            {
                holder.number.text="1"

            }
            else{
                incrementNumberValue = --incrementNumber
                holder.number.text="${incrementNumberValue}"
                PickerManager.addCartList!![position].quantity="$incrementNumberValue"
            }
        }

        holder.more.setOnClickListener {
            incrementNumberValue = ++incrementNumber
            holder.number.text="${incrementNumberValue}"
            PickerManager.addCartList!![position].quantity="$incrementNumberValue"

        }

    }

    override fun getItemCount(): Int {
        return addCartList!!.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val foodTitleTV: TextView
        val foodPriceTV: TextView
        val foodImageIV: ImageView
        val less: Button
        val more: Button
        val number: TextView
        val deleteItem_IV: ImageView

        init {
            foodTitleTV = itemView.findViewById(R.id.food_title)
            foodPriceTV = itemView.findViewById(R.id.food_price)
            foodImageIV = itemView.findViewById(R.id.foodImage)
            less = itemView.findViewById(R.id.less)
            more = itemView.findViewById(R.id.more)
            number = itemView.findViewById(R.id.number)
            deleteItem_IV = itemView.findViewById(R.id.deleteItem_IV)


            val l = View.OnClickListener { v: View? ->
                notifyItemRangeChanged(0, addCartList.size)
                clickListener.onItemClick(position)
                notifyDataSetChanged()
            }
            itemView.setOnClickListener(l)
        }
    }
}