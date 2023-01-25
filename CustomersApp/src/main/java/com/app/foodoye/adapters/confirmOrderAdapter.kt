package com.app.foodoye.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.foodoye.R
import com.app.foodoye.dataclass.fooditems
import com.app.foodoye.utils.PickerManager

class confirmOrderAdapter(
    private val context: Context,
    private var addCartList: MutableList<fooditems>,
): RecyclerView.Adapter<confirmOrderAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): confirmOrderAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.confirmorder_layout, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: confirmOrderAdapter.ViewHolder, position: Int) {
        //Set the position here!!!
        val currentItem = addCartList[position]

        holder.itemNo.text = "${position+1}"
        holder.foodNameTV.text = currentItem.foodTitle
        holder.foodPriceTV.text=currentItem.foodPrice
        holder.quantityTV.text= currentItem.quantity!!.toInt().toString()
        val quantityPrice= (PickerManager.addCartList!![position].foodPrice)!!.toInt() * (PickerManager.addCartList!![position].quantity)!!.toInt()
//        Log.d("checkingValues","price * quantity: ${(PickerManager.addCartList!![position].foodPrice)!!.toInt() * (PickerManager.addCartList!![position].quantity)!!.toInt()}  updated: ${quantityPrice}")
        holder.updatedPriceTV.text= "${quantityPrice} Rs"

    }

    override fun getItemCount(): Int {
        return addCartList!!.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemNo: TextView
        val foodNameTV: TextView
        val foodPriceTV: TextView
        val quantityTV: TextView
        val updatedPriceTV: TextView

        init {
            itemNo = itemView.findViewById(R.id.itemNoTV)
            foodNameTV = itemView.findViewById(R.id.foodNameTV)
            foodPriceTV = itemView.findViewById(R.id.foodPriceTV)
            quantityTV = itemView.findViewById(R.id.quantityTV)
            updatedPriceTV = itemView.findViewById(R.id.updatedPriceTV)
        }
    }
}