package com.app.foodoye.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.app.foodoye.R
import com.app.foodoye.databinding.ActivityDashboardBinding
import com.app.foodoye.ui.fragments.*

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
        val fragment = intent.getStringExtra("fragmentCart")
        when(fragment){
            "addToCartFragment" -> return addToCartFragment()
             else -> return restaurantsFragment()
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
                R.id.restaurantMenu -> addFragmentToActivity(restaurantsFragment())
                R.id.OrdersMenu -> addFragmentToActivity(myOrdersFragment())
                R.id.ProfileMenu -> addFragmentToActivity(profileFragment())
            }
            true
        }
    }
}