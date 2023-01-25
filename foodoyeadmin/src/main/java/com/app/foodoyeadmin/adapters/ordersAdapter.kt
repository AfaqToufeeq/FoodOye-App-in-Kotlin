package com.app.foodoyeadmin.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.*
import android.widget.*
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.app.foodoyeadmin.R
import com.app.foodoyeadmin.dataclass.ordersClass
import com.app.foodoyeadmin.interfaces.onOrdersMenuItemClick
import com.app.foodoyeadmin.ui.fragments.ordersFragment

class ordersAdapter(
    private val context: Context,
    private var orderList: MutableList<ordersClass>,
    val clickListener: onOrdersMenuItemClick

): RecyclerView.Adapter<ordersAdapter.ViewHolder>() {
    val builderFood = StringBuilder()
    val builderQuantity = StringBuilder()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ordersAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.orders_layout, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ordersAdapter.ViewHolder, @SuppressLint("RecyclerView") position: Int) {
        //Set the position here!!!
        val currentItem = orderList[position]

        holder.customerAddressTV!!.text= currentItem.address
        holder.customerPhoneTV!!.text= currentItem.phone
        holder.customerCommentsTV!!.text= currentItem.comments
        holder.customerNameTV!!.text= currentItem.fullName
        holder.finalPriceTV!!.text= currentItem.totalPrice
        holder.orderNumberTV!!.text= currentItem.orderNo
        holder.statusTV!!.text= currentItem.status
        holder.dateTV!!.text= currentItem.date
        holder.timeTV!!.text= currentItem.time

        currentItem.foodList!!.forEach {
            builderFood.append("${it.foodTitle}")
            .append("\n")
            builderQuantity.append("${it.quantity}").append("\n")
        }

        holder.foodNameTV!!.text= builderFood.toString()
        holder.customerquantityTV!!.text= builderQuantity.toString()
        builderFood.clear()
        builderQuantity.clear()

        /** Long ClickListener for the popMenu
         **/

        holder.itemView.setOnLongClickListener {
            clickListener.onLongClick(position)
            true
        }

        holder.itemView.setOnClickListener {
            clickListener.onItemClick(position)
        }

        holder.itemView.setOnLongClickListener{
            val popup = PopupMenu(context, holder.itemView)
            popup.inflate(R.menu.orders_menu)
            popup.setOnMenuItemClickListener(object : PopupMenu.OnMenuItemClickListener {
                override fun onMenuItemClick(item: MenuItem): Boolean {
                     when (item.getItemId()) {
                        R.id.acceptedOrdersMenu -> clickListener.onLongMenuClick(1,position)
                        R.id.rejectedOrdersMenu -> clickListener.onLongMenuClick(2,position)
                        R.id.completedOrdersMenu -> clickListener.onLongMenuClick(3,position)
                        else -> return false
                    }
                    return true
                }

            })
            popup.show()
            true
        }

    }

    override fun getItemCount(): Int {
        return orderList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var foodNameTV: TextView?=null
        var finalPriceTV: TextView?=null
        var orderNumberTV: TextView?=null
        var statusTV: TextView?=null
        var customerNameTV: TextView?=null
        var customerPhoneTV: TextView?=null
        var customerAddressTV: TextView?=null
        var customerCommentsTV: TextView?=null
        var customerquantityTV: TextView?=null
        var dateTV: TextView?=null
        var timeTV: TextView?=null


        init {
            foodNameTV = itemView.findViewById(R.id.foodNameTV)
            customerCommentsTV = itemView.findViewById(R.id.customerCommentsTV)
            customerNameTV = itemView.findViewById(R.id.customerNameTV)
            customerAddressTV = itemView.findViewById(R.id.customerAddressTV)
            customerPhoneTV = itemView.findViewById(R.id.customerPhoneTV)
            finalPriceTV = itemView.findViewById(R.id.TotalBillTV)
            orderNumberTV = itemView.findViewById(R.id.orderNumberTV)
            statusTV = itemView.findViewById(R.id.statusTV)
            customerquantityTV = itemView.findViewById(R.id.quantityTVV)
            dateTV = itemView.findViewById(R.id.dateTV)
            timeTV = itemView.findViewById(R.id.timeTV)


//            val l = View.OnClickListener { v: View? ->
//                notifyItemRangeChanged(0, orderList.size)
//                clickListener.onItemClick(position)
//                notifyDataSetChanged()
//            }
//        itemView.setOnClickListener(l)
        }
    }
}
