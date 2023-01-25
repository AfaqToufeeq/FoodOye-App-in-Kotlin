package com.app.foodoyeadmin.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.app.foodoyeadmin.R
import com.app.foodoyeadmin.databinding.ActivityDashboardBinding
import com.app.foodoyeadmin.ui.fragments.addFoodItemsFragment
import com.app.foodoyeadmin.ui.fragments.ordersFragment
import com.app.foodoyeadmin.ui.fragments.profileFragment
import com.app.foodoyeadmin.utils.PickerManager.checkOrderListRequest

class DashboardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDashboardBinding
    var fragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragment= getData()
        addFragmentToActivity(fragment)
        bottomNavigation()

    }

    private fun getData(): Fragment {
        val fragment = intent.getStringExtra("fragment")
        when(fragment){
            "addFoodItemsFragment" -> return addFoodItemsFragment()
            else -> return ordersFragment()
        }
    }

    private fun addFragmentToActivity(fragment: Fragment?){
        if (fragment == null) return
        val fm = supportFragmentManager
        val tr = fm.beginTransaction()
        tr.replace(R.id.dashboard_FL, fragment).addToBackStack(null).commit()
    }

    private fun bottomNavigation() {
        binding.bottomNavBar.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.OrderMenu -> {
                    addFragmentToActivity(ordersFragment())
                    checkOrderListRequest=1
                }
                R.id.addRestaurant -> {
                    startActivity(Intent(this, addRestaurantActivity::class.java))
                }
                R.id.addMenu -> addFragmentToActivity(addFoodItemsFragment())
                R.id.ProfileMenu -> addFragmentToActivity(profileFragment())
            }
            true
        }
    }
}