package com.app.foodoye.ui.fragments

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.view.updatePadding
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.foodoye.R
import com.app.foodoye.adapters.ordersAdapter
import com.app.foodoye.databinding.FragmentMyOrdersBinding
import com.app.foodoye.dataclass.ordersClass
import com.app.foodoye.extensions.Utils
import com.app.foodoye.interfaces.onfoodMenuItemClick
import com.app.foodoye.utils.PickerManager.ordersList
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase

class myOrdersFragment : Fragment(),onfoodMenuItemClick {
    lateinit var binding: FragmentMyOrdersBinding
    private lateinit var auth: FirebaseAuth
    lateinit var ref : DatabaseReference
    lateinit var loader: Dialog
    var adapter:ordersAdapter?=null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentMyOrdersBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        setVisibility()
        onbackPressed()
        buildRecyclerView()
        buttonClicks()
    }

    @SuppressLint("SetTextI18n")
    private fun initViews() {
        loader = Utils.progressDialog(requireActivity())
        binding.tvTitleSpecific.text = "My Orders"
        ordersList=ArrayList()
        auth = Firebase.auth
    }

    private fun setVisibility() {

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
        binding.backButton.setOnClickListener {
            setFragment(foodMenuFragment())
        }
    }

    private fun onbackPressed() {
        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                setFragment(foodMenuFragment())
            }
        })
    }

    private fun setFragment(fragment: Fragment) {
        if (fragment == null) return
        val fm = requireActivity().supportFragmentManager
        val tr = fm.beginTransaction()
        tr.replace(R.id.dashboard_FL, fragment).addToBackStack(null).commit()
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
        ordersList!!.clear()
        getOrderdatafromFirebase()
    }

    private fun getOrderdatafromFirebase() {
        if(!loader.isShowing) loader.show()

        ref= FirebaseDatabase.getInstance().getReference("Orders")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists())
                {
                    for(itemsSnapshot in snapshot.children)
                    {
                        val foodItems = itemsSnapshot.getValue(ordersClass::class.java)

                        if(foodItems!!.uid==auth.uid)
                        {
                            ordersList?.add(foodItems)
                        }

                    }
                    if (loader.isShowing) loader.dismiss()
                    adapter =ordersAdapter(
                        requireActivity(),
                        ordersList!!,
                        this@myOrdersFragment)
                    setVisibility()
                    binding.Recyclerview.adapter = adapter

                }
                else{
                    if (loader.isShowing) loader.dismiss()
                    setVisibility()
                }
            }
            override fun onCancelled(error: DatabaseError) {
                if (loader.isShowing) loader.dismiss()
                Toast.makeText(requireActivity(),error.message+"", Toast.LENGTH_SHORT).show()
            }

        })
    }

    override fun onItemClick(position: Int) {

    }

    override fun onLongClick(position: Int) {

    }

}