package com.app.foodoye.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.*
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.app.foodoye.R
import com.app.foodoye.dataclass.ordersClass
import com.app.foodoye.interfaces.onfoodMenuItemClick

class ordersAdapter(
    private val context: Context,
    private var orderList: MutableList<ordersClass>,
    val clickListener: onfoodMenuItemClick

): RecyclerView.Adapter<ordersAdapter.ViewHolder>() {
    val builderFood = StringBuilder()
    val builderQuantity = StringBuilder()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ordersAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.myorders_layout, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ordersAdapter.ViewHolder, position: Int) {
        //Set the position here!!!
        val currentItem = orderList[position]

        holder.finalPriceTV!!.text= currentItem.totalPrice
        holder.orderNumberTV!!.text= currentItem.orderNo
        holder.statusTV!!.text= currentItem.status

        currentItem.foodList!!.forEach {
            builderFood.append("${it.foodTitle}")
            .append("\n")
            builderQuantity.append("${it.quantity}").append("\n")
        }

        holder.foodNameTV!!.text= builderFood.toString()
        holder.customerquantityTV!!.text= builderQuantity.toString()
        builderFood.clear()
        builderQuantity.clear()


    }

    override fun getItemCount(): Int {
        return orderList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var foodNameTV: TextView?=null
        var finalPriceTV: TextView?=null
        var orderNumberTV: TextView?=null
        var statusTV: TextView?=null
        var customerquantityTV: TextView?=null

        init {
            foodNameTV = itemView.findViewById(R.id.foodNameTV)
            finalPriceTV = itemView.findViewById(R.id.TotalBillTV)
            orderNumberTV = itemView.findViewById(R.id.orderNumberTV)
            statusTV = itemView.findViewById(R.id.statusTV)
            customerquantityTV = itemView.findViewById(R.id.quantityTVV)

        }
    }
}
