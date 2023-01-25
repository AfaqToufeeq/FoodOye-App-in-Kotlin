package com.app.foodoye.ui.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.app.foodoye.R
import com.app.foodoye.databinding.ActivityViewFoodItemBinding
import com.app.foodoye.dataclass.fooditems
import com.app.foodoye.ui.fragments.addToCartFragment
import com.app.foodoye.utils.PickerManager
import com.bumptech.glide.Glide

class viewFoodItemActivity : AppCompatActivity() {
    lateinit var binding: ActivityViewFoodItemBinding
    var position:Int=0
    var fooditemsClass: fooditems?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityViewFoodItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getData()
        buttonClicks()
    }

    private fun getData() {
        position  = intent.getIntExtra("positionFoodItem",0)
        binding.FoodName.text= PickerManager.allFoodList!![position].foodTitle
        binding.FoodDesc.text= PickerManager.allFoodList!![position].foodDesc
        binding.FoodPrice.text= "${PickerManager.allFoodList!![position].foodPrice} Rs"
        Glide.with(this@viewFoodItemActivity)
            .load(PickerManager.allFoodList!![position].foodUrl)
            .placeholder(R.drawable.add_item_icon)
            .into(binding.bigImage)
    }

    @SuppressLint("SetTextI18n")
    private fun buttonClicks() {
       binding.backButton.setOnClickListener {
           finish()
       }

        binding.directOrderbutton.setOnClickListener {
            orderItem()
            var button= binding.directOrderbutton
            button.text = "View Order"
            if(button.text=="View Order")
            {
                button.setOnClickListener {

                    val intent = Intent(this@viewFoodItemActivity,DashboardActivity::class.java)
                    intent.putExtra("fragmentCart","addToCartFragment")
                    startActivity(intent)
                    finish()
                }
            }

        }
    }

    private fun orderItem() {
        fooditemsClass= fooditems(
            PickerManager.allFoodList!![position].foodTitle,
            PickerManager.allFoodList!![position].foodDesc,
            PickerManager.allFoodList!![position].foodPrice,
            PickerManager.allFoodList!![position].foodUrl,
            PickerManager.allFoodList!!.get(position).restaurantID,
            "1"
        )
        PickerManager.addCartList!!.add(fooditemsClass!!)
    }
}