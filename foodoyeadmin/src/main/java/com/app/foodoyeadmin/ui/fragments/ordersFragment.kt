package com.app.foodoyeadmin.ui.fragments

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.core.view.updatePadding
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.foodoyeadmin.R
import com.app.foodoyeadmin.adapters.ordersAdapter
import com.app.foodoyeadmin.databinding.FragmentOrdersBinding
import com.app.foodoyeadmin.dataclass.ordersClass
import com.app.foodoyeadmin.extensions.Utils
import com.app.foodoyeadmin.interfaces.onOrdersMenuItemClick
import com.app.foodoyeadmin.ui.activities.addRestaurantActivity
import com.app.foodoyeadmin.utils.PickerManager
import com.app.foodoyeadmin.utils.PickerManager.ordersList
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase

class ordersFragment : Fragment(),onOrdersMenuItemClick {
    lateinit var binding: FragmentOrdersBinding
    private lateinit var auth: FirebaseAuth
    lateinit var ref : DatabaseReference
    var adapter:ordersAdapter?=null
    lateinit var orderItems:ordersClass
    lateinit var loader: Dialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentOrdersBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        setVisibility()
        buildRecyclerView()
        buttonClicks()

    }

    private fun initViews() {
        loader = Utils.progressDialog(requireActivity())
        binding.tvTitleSpecific.text = "Orders"
        binding.tvTitleSpecific.updatePadding(left = 50)
        ordersList=ArrayList()
        auth = Firebase.auth
    }

    private fun setVisibility() {
        binding.backButton.visibility=View.GONE
        if(ordersList.isNullOrEmpty())
        {
            binding.Recyclerview.visibility=View.GONE
            binding.layoutEmptyList.visibility=View.VISIBLE
        }
        else{
            binding.Recyclerview.visibility=View.VISIBLE
            binding.layoutEmptyList.visibility=View.GONE
        }
    }

    private fun buttonClicks() {

        //PopMenu
        binding.ivToogle.setOnClickListener {
            val popup = PopupMenu(requireActivity(),binding.ivToogle)
            popup.inflate(R.menu.toogleorder_menu)
            popup.setOnMenuItemClickListener(object : PopupMenu.OnMenuItemClickListener {
                override fun onMenuItemClick(item: MenuItem): Boolean {
                    when (item.getItemId()) {
                        R.id.pendingMenu -> {
                            PickerManager.checkOrderListRequest = 2
                            getOrderdatafromFirebase()
                        }
                        R.id.acceptedMenu -> {
                            PickerManager.checkOrderListRequest = 3
                            getOrderdatafromFirebase()
                        }
                        R.id.rejectedMenu -> {
                            PickerManager.checkOrderListRequest = 4
                            getOrderdatafromFirebase()
                        }
                        R.id.completedMenu -> {
                            PickerManager.checkOrderListRequest = 5
                            getOrderdatafromFirebase()
                        }
                        else -> {
                            PickerManager.checkOrderListRequest =1
                            return false
                        }
                    }
                    return true
                }
            })
            popup.show()

        }
    }

    private fun buildRecyclerView() {
        binding.Recyclerview.layoutManager = LinearLayoutManager(requireActivity())
        binding.Recyclerview.setHasFixedSize(true)
        binding.Recyclerview.addItemDecoration(
            DividerItemDecoration(
                requireActivity(),
                (binding.Recyclerview.layoutManager as LinearLayoutManager).orientation
            )
        )
        getOrderdatafromFirebase()
//        pendingOrders()
    }

    private fun getOrderdatafromFirebase() {
        if(!loader.isShowing) loader.show()
        ordersList!!.clear()

        ref= FirebaseDatabase.getInstance().getReference("Orders")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists())
                {
                    for(itemsSnapshot in snapshot.children)
                    {
                        orderItems= itemsSnapshot.getValue(ordersClass::class.java)!!
                        when (PickerManager.checkOrderListRequest)
                        {
                            1->{
                                ordersList?.add(orderItems)
                            }

                            2->{
                                if(orderItems.status=="pending")
                                {
                                    ordersList?.add(orderItems!!)
                                }
                            }

                            3->{
                                if(orderItems.status=="Accepted")
                                {
                                    ordersList?.add(orderItems!!)
                                }
                            }

                            4->{
                                if(orderItems.status=="Rejected")
                                {
                                    ordersList?.add(orderItems!!)
                                }
                            }

                            5->{
                                if(orderItems.status=="Completed")
                                {
                                    ordersList?.add(orderItems!!)
                                }
                            }
                        }

                    }

                    if (loader.isShowing) loader.dismiss()

                    adapter= ordersAdapter(
                        requireActivity(),
                        ordersList!!,
                        this@ordersFragment)

                    binding.Recyclerview.adapter = adapter
                    setVisibility()
                }
                else{
                    if (loader.isShowing) loader.dismiss()
                    setVisibility()
                }
            }
            override fun onCancelled(error: DatabaseError) {
                if (loader.isShowing) loader.dismiss()
                setVisibility()
                Toast.makeText(requireActivity(),error.message+"", Toast.LENGTH_SHORT).show()
            }

        })
    }

    fun pendingOrders()
    {
        if(!loader.isShowing) loader.show()

        ref= FirebaseDatabase.getInstance().getReference("Orders")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists())
                {
                    for(itemsSnapshot in snapshot.children)
                    {
                        val foodItems = itemsSnapshot.getValue(ordersClass::class.java)
                        if(foodItems!!.status=="pending")
                        {
                            ordersList?.add(foodItems!!)
                        }

                    }

                    if (loader.isShowing) loader.dismiss()

                    adapter= ordersAdapter(
                        requireActivity(),
                        ordersList!!,
                        this@ordersFragment)

                    binding.Recyclerview.adapter = adapter
                    setVisibility()
                }
                else{
                    if (loader.isShowing) loader.dismiss()
                    setVisibility()
                }
            }
            override fun onCancelled(error: DatabaseError) {
                if (loader.isShowing) loader.dismiss()
                setVisibility()
                Toast.makeText(requireActivity(),error.message+"", Toast.LENGTH_SHORT).show()
            }

        })
    }



    override fun onItemClick(position: Int) {

    }

    override fun onLongClick(position: Int) {

    }

    override fun onLongMenuClick(status: Int, position: Int) {
        orderStatus(status,position)
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun orderStatus(status: Int, position: Int) {
        when (status)
        {
            1-> { ref.child(ordersList!![position].uniqueKey!!).child("status").setValue("Accepted")  }
            2-> { ref.child(ordersList!![position].uniqueKey!!).child("status").setValue("Rejected")  }
            3-> { ref.child(ordersList!![position].uniqueKey!!).child("status").setValue("Completed")  }
        }
        ordersList!!.clear()
        adapter!!.notifyDataSetChanged()
    }

    private fun setFragment(fragment: Fragment) {
        if (fragment == null) return
        val fm = requireActivity().supportFragmentManager
        val tr = fm.beginTransaction()
        tr.replace(R.id.dashboard_FL, fragment).addToBackStack(null).commit()
    }


    private fun addRestaurant() {
        startActivity(Intent(requireActivity(),addRestaurantActivity::class.java))
    }

    private fun addMenu() {
       setFragment(addFoodItemsFragment())
    }

}