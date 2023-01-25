package com.app.foodoye.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.widget.AppCompatCheckBox
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.app.foodoye.R
import com.app.foodoye.dataclass.fooditems
import com.app.foodoye.extensions.SmoothCheckBox
import com.app.foodoye.interfaces.onfoodMenuItemClick
import com.app.foodoye.ui.activities.viewFoodItemActivity
import com.app.foodoye.utils.PickerManager
import com.app.foodoye.utils.PickerManager.checkIncrementValue
import com.app.foodoye.utils.PickerManager.incrementNumberValue
import com.bumptech.glide.Glide
import com.bumptech.glide.annotation.GlideModule
import org.w3c.dom.Text

class foodMenuAdapter(
    private val context: Context,
    private var allFoodList: MutableList<fooditems>,
    val clickListener: onfoodMenuItemClick
): RecyclerView.Adapter<foodMenuAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): foodMenuAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fooditems_layout, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: foodMenuAdapter.ViewHolder, position: Int) {
        //Set the position here!!!
        var foodUri:Uri?=null
        val currentItem = allFoodList[position]
        var incrementNumber=1
        holder.foodTitleTV.text = currentItem.foodTitle
        holder.foodDesTV.text = currentItem.foodDesc
        holder.foodPriceTV.text = "${currentItem.foodPrice} Rs"
       try {
           foodUri = currentItem.foodUrl!!.toUri()
       }catch (e:Exception)
       {
           Log.d("foodMenuAdapter","${e.message}")
       }

        Glide.with(context)
            .load(foodUri)
            .placeholder(R.drawable.add_item_icon)
            .into(holder.foodImageIV)

        holder.itemView.setOnClickListener {

            val intent = Intent(context,viewFoodItemActivity::class.java)
            intent.putExtra("positionFoodItem",position)
            context.startActivity(intent)
        }

        holder.addCardBtn.setOnClickListener {
            clickListener.onItemClick(position)
        }

        holder.less.setOnClickListener {
            if(incrementNumber==1 || incrementNumber<0)
            {
                holder.number.text="1"
                checkIncrementValue=false
            }
            else{
                incrementNumberValue= --incrementNumber
                holder.number.text="$incrementNumberValue"
                checkIncrementValue=true
            }
        }

        holder.more.setOnClickListener {
            incrementNumberValue= ++incrementNumber
                holder.number.text="$incrementNumberValue"
            checkIncrementValue=true
        }

    }

    override fun getItemCount(): Int {
        return allFoodList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val foodTitleTV: TextView
        val foodDesTV: TextView
        val foodPriceTV: TextView
        val foodImageIV: ImageView
        val less: Button
        val more: Button
        val number: TextView
        val addCardBtn:Button
        val viewCardBtn:Button
        val quantity_LinearL:LinearLayout

        init {
            foodTitleTV = itemView.findViewById(R.id.food_title)
            foodDesTV = itemView.findViewById(R.id.food_description)
            foodPriceTV = itemView.findViewById(R.id.food_price)
            foodImageIV = itemView.findViewById(R.id.foodImage)
            less = itemView.findViewById(R.id.less)
            more = itemView.findViewById(R.id.more)
            number = itemView.findViewById(R.id.number)
            addCardBtn = itemView.findViewById(R.id.addCardBtn)
            viewCardBtn = itemView.findViewById(R.id.viewCardBtn)
            quantity_LinearL = itemView.findViewById(R.id.quantity_LinearL)

        }
    }

}