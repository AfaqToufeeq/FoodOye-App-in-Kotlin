package com.app.foodoye.ui.fragments

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.view.updatePadding
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.foodoye.R
import com.app.foodoye.adapters.foodMenuAdapter
import com.app.foodoye.databinding.FragmentFoodMenuBinding
import com.app.foodoye.dataclass.fooditems
import com.app.foodoye.extensions.Utils
import com.app.foodoye.interfaces.AlertDialogListener
import com.app.foodoye.interfaces.onfoodMenuItemClick
import com.app.foodoye.utils.CommonUtils
import com.app.foodoye.utils.PickerManager.addCartList
import com.app.foodoye.utils.PickerManager.allFoodList
import com.app.foodoye.utils.PickerManager.checkIncrementValue
import com.app.foodoye.utils.PickerManager.incrementNumberValue
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import kotlin.system.exitProcess


class foodMenuFragment : Fragment(),onfoodMenuItemClick {
    private lateinit var binding: FragmentFoodMenuBinding
    private lateinit var auth: FirebaseAuth
    lateinit var ref : DatabaseReference
    var fooditemsClass: fooditems?=null
    lateinit var loader: Dialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentFoodMenuBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        setVisibility()
        onbackPressed()
        buttonClicks()
        buildRecyclerView()
    }

    override fun onResume() {
        super.onResume()
        setVisibility()
    }

    override fun onPause() {
        super.onPause()
        incrementNumberValue=1
    }

    private fun setVisibility() {
        binding.orderCartButton.visibility=View.GONE

        if(!allFoodList.isNullOrEmpty())
        {
            binding.layoutEmptyList.visibility= View.GONE
            binding.foodMenuRecyclerview.visibility=View.VISIBLE
            binding.CartfloatButton.visibility=View.VISIBLE
        }
        else{
            binding.layoutEmptyList.visibility= View.VISIBLE
            binding.foodMenuRecyclerview.visibility=View.GONE
            binding.CartfloatButton.visibility=View.GONE
        }
    }

    private fun buttonClicks() {
        binding.CartfloatButton.setOnClickListener {

          setFragment(addToCartFragment())
        }

        binding.backButton.setOnClickListener {

            setFragment(restaurantsFragment())
        }
    }

    @SuppressLint("SetTextI18n")
    private fun initViews() {
        loader = Utils.progressDialog(requireActivity())
        binding.tvTitleSpecific.text="Food Items"
        binding.tvTitleSpecific.updatePadding(left = 50)
       addCartList = ArrayList()
        auth = Firebase.auth

    }

    private fun setFragment(fragment: Fragment) {
        if (fragment == null) return
        val fm = requireActivity().supportFragmentManager
        val tr = fm.beginTransaction()
        tr.replace(R.id.dashboard_FL, fragment).addToBackStack(null).commit()
    }

    private fun buildRecyclerView() {
        if(!loader.isShowing) loader.show()
        binding.foodMenuRecyclerview.layoutManager = LinearLayoutManager(requireActivity())
        binding.foodMenuRecyclerview.setHasFixedSize(true)

        binding.foodMenuRecyclerview.adapter =
            foodMenuAdapter(
                requireActivity(),
                allFoodList!!,
                this@foodMenuFragment
            )

        if (loader.isShowing) loader.dismiss()
        setVisibility()
    }

    override fun onItemClick(position: Int) {
        if(!checkIncrementValue)
        {
            incrementNumberValue=1
        }
          fooditemsClass= fooditems(
                allFoodList!!.get(position).foodTitle,
                allFoodList!!.get(position).foodDesc,
                allFoodList!!.get(position).foodPrice,
                allFoodList!!.get(position).foodUrl,
                allFoodList!!.get(position).restaurantID,
              incrementNumberValue.toString()
          )
        addCartList!!.add(fooditemsClass!!)
        checkIncrementValue=false

        addCartList!!.forEach {
            Log.d("listCheckAddcart","List-> ${it}")
        }
    }

    private fun onbackPressed() {
        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                setFragment(restaurantsFragment())
            }
        })
    }


    override fun onLongClick(position: Int) {

    }
}