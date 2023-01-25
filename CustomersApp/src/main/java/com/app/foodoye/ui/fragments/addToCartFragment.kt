package com.app.foodoye.ui.fragments

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.foodoye.R
import com.app.foodoye.adapters.addCartAdapter
import com.app.foodoye.databinding.FragmentFoodMenuBinding
import com.app.foodoye.extensions.Utils
import com.app.foodoye.interfaces.onfoodMenuItemClick
import com.app.foodoye.ui.activities.confirmOrderActivity
import com.app.foodoye.utils.PickerManager.addCartList

class addToCartFragment : Fragment(),onfoodMenuItemClick {
    private lateinit var binding: FragmentFoodMenuBinding
    lateinit var loader: Dialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentFoodMenuBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        buttonClicks()
        onbackPressed()
        setVisibility()
        buildRecyclerView()
    }

    private fun initViews() {
        binding.tvTitleSpecific.text="Cart Items"
        loader = Utils.progressDialog(requireActivity())
    }

    private fun buttonClicks() {
        binding.backButton.setOnClickListener {
            setFragment(foodMenuFragment())
        }

        binding.orderCartButton.setOnClickListener {
            if(addCartList.isNullOrEmpty())
            {
                setVisibility()
                Toast.makeText(requireActivity(),"Cart is Empty, to proceed add items in cart",Toast.LENGTH_SHORT).show()
            }else{

                startActivity(Intent(requireActivity(),confirmOrderActivity::class.java))
            }
        }
    }

    private fun setFragment(fragment: Fragment) {
        if (fragment == null) return
        val fm = requireActivity().supportFragmentManager
        val tr = fm.beginTransaction()
        tr.replace(R.id.dashboard_FL, fragment).addToBackStack(null).commit()
    }


     fun setVisibility() {
        binding.backButton.visibility=View.VISIBLE
        binding.CartfloatButton.visibility=View.GONE

        if(!addCartList.isNullOrEmpty())
        {
            binding.layoutEmptyList.visibility=View.GONE
            binding.orderCartButton.visibility=View.VISIBLE
            binding.foodMenuRecyclerview.visibility=View.VISIBLE
        }
        else{
            binding.orderCartButton.visibility=View.GONE
            binding.layoutEmptyList.visibility=View.VISIBLE
            binding.foodMenuRecyclerview.visibility=View.GONE
        }

    }

    private fun buildRecyclerView() {
        binding.foodMenuRecyclerview.layoutManager = LinearLayoutManager(requireActivity())
        binding.foodMenuRecyclerview.setHasFixedSize(true)
        binding.foodMenuRecyclerview.addItemDecoration(
            DividerItemDecoration(
                requireActivity(),
                (binding.foodMenuRecyclerview.layoutManager as LinearLayoutManager).orientation
            )
        )

        if(!loader.isShowing) loader.show()

        getDataFromList()
    }

    private fun getDataFromList() {
        if(loader.isShowing)  loader.dismiss()

        binding.foodMenuRecyclerview.adapter = addCartAdapter(
            requireActivity(),
            addCartList!!,
            this@addToCartFragment
        )

        setVisibility()
    }

    override fun onItemClick(position: Int) {
        when(position)
        {
            -1 -> setVisibility()
        }
    }

    override fun onLongClick(position: Int) {
//        binding.trashDelete.visibility=View.VISIBLE
    }

    private fun onbackPressed() {
        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                setFragment(foodMenuFragment())
            }
        })
    }


}