package com.app.foodoye.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.app.foodoye.R
import com.app.foodoye.dataclass.restaurantDetails
import com.app.foodoye.interfaces.onfoodMenuItemClick
import com.app.foodoye.utils.PickerManager.chooseRestaurant
import com.bumptech.glide.Glide

class restaurantAdapter(
    private val context: Context,
    var restaurantList: MutableList<restaurantDetails>,
    val clickListener: onfoodMenuItemClick
): RecyclerView.Adapter<restaurantAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): restaurantAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.restaurant_layout, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: restaurantAdapter.ViewHolder, position: Int) {
        //Set the position here!!!
        var restaurantUri:Uri?=null
        val currentItem = restaurantList[position]

//        Log.d("ABC","rest: ${currentItem.RestaurantDetails!![0].restaurantName}")
//        currentItem.restaurantDetails!!.forEach {
//            Log.d("checkingFoodL","resta: $it")
//        }

        holder.restaurant_ID.text = "ID: ${currentItem.restaurantID}"
        holder.restaurantTitle_TV.text = currentItem.restaurantName
        holder.restaurantDes_TV.text = currentItem.description

        try{
            restaurantUri = currentItem.restaurantUrl!!.toUri()
        }catch (e:Exception)
        {
            Log.d("restaurantAdapter","${e.message}")
        }

        Glide.with(context)
            .load(restaurantUri)
            .placeholder(R.drawable.add_item_icon)
            .into(holder.restaurantImage_IV)

//        Glide.with(context)
//            .load(foodUri)
//            .placeholder(R.drawable.add_item_icon)
//            .into(holder.foodImageIV)


        holder.itemView.setOnClickListener {
            chooseRestaurant=currentItem.restaurantID
            clickListener.onItemClick(position)
        }

    }

    override fun getItemCount(): Int {
        return restaurantList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val restaurantTitle_TV: TextView
        val restaurantDes_TV: TextView
        val restaurant_ID: TextView
        val restaurantImage_IV: ImageView

        init {
            restaurantTitle_TV = itemView.findViewById(R.id.restaurantTitle_TV)
            restaurantDes_TV = itemView.findViewById(R.id.restaurantDes_TV)
            restaurantImage_IV = itemView.findViewById(R.id.restaurant_IV)
            restaurant_ID = itemView.findViewById(R.id.restaurant_ID)

        }
    }
}