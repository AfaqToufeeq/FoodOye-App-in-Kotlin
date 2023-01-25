package com.app.foodoye.ui.fragments

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.foodoye.R
import com.app.foodoye.adapters.restaurantAdapter
import com.app.foodoye.databinding.FragmentRestaurantsBinding
import com.app.foodoye.dataclass.fooditems
import com.app.foodoye.dataclass.restaurantDetails
import com.app.foodoye.extensions.Utils
import com.app.foodoye.interfaces.AlertDialogListener
import com.app.foodoye.interfaces.onfoodMenuItemClick
import com.app.foodoye.utils.CommonUtils
import com.app.foodoye.utils.PickerManager.allFoodList
import com.app.foodoye.utils.PickerManager.chooseRestaurant
import com.app.foodoye.utils.PickerManager.restaurantFoodList
import com.app.foodoye.utils.PickerManager.restaurantList
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import kotlin.system.exitProcess

class restaurantsFragment : Fragment(), onfoodMenuItemClick {
    lateinit var binding: FragmentRestaurantsBinding
    private lateinit var auth: FirebaseAuth
    lateinit var ref : DatabaseReference
    lateinit var loader: Dialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentRestaurantsBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        setVisibility()
        buildRecyclerView()
        onbackPressed()
    }

    private fun initViews() {
        loader = Utils.progressDialog(requireActivity())
        auth = Firebase.auth
        restaurantList = ArrayList()
        allFoodList = ArrayList()
        restaurantFoodList = ArrayList()

    }

    private fun setVisibility() {

        if(!restaurantList.isNullOrEmpty())
        {
            binding.layoutEmptyList.visibility= View.GONE
            binding.restaurantRecyclerview.visibility=View.VISIBLE
        }
        else{
            binding.layoutEmptyList.visibility= View.VISIBLE
            binding.restaurantRecyclerview.visibility=View.GONE
        }
    }

    private fun buildRecyclerView() {
        binding.restaurantRecyclerview.layoutManager = LinearLayoutManager(requireActivity())
        binding.restaurantRecyclerview.setHasFixedSize(true)

        getRestaurantDatafromFirebase()
    }

    private fun getRestaurantDatafromFirebase() {
        if(!loader.isShowing) loader.show()

        ref= FirebaseDatabase.getInstance().getReference("Restaurants")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists())
                {
                    for(itemsSnapshot in snapshot.children)
                    {
                        val restaurantItems = itemsSnapshot.getValue(restaurantDetails::class.java)
                        restaurantList?.add(restaurantItems!!)
                        setVisibility()
                    }
                    if (loader.isShowing) loader.dismiss()

                    binding.restaurantRecyclerview.adapter = restaurantAdapter(
                        requireActivity(),
                        restaurantList!!,
                        this@restaurantsFragment
                    )
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


        /**
         * [function] fetchFoodMenuFirebase
         * */
        fetchFoodMenuFirebase()

    }

    /**
     * For the Food Menu fetching data from Firebase
     * [function] fetchFoodMenuFirebase
     * */
    private fun fetchFoodMenuFirebase() {
        ref= FirebaseDatabase.getInstance().getReference("foodList")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists())
                {
                    for(itemsSnapshot in snapshot.children)
                    {
                        val foodItems = itemsSnapshot.getValue(fooditems::class.java)
                        restaurantFoodList?.add(foodItems!!)

                    }
                    if (loader.isShowing) loader.dismiss()
                }
                else{
                    if (loader.isShowing) loader.dismiss()
                }
            }
            override fun onCancelled(error: DatabaseError) {
                if (loader.isShowing) loader.dismiss()
                Toast.makeText(requireActivity(),error.message+"", Toast.LENGTH_SHORT).show()
            }

        })
    }


    override fun onItemClick(position: Int) {
        if(position>=0 && chooseRestaurant!=null)
        {
               allFoodList = restaurantFoodList!!.filter { it.restaurantID== chooseRestaurant } as MutableList<fooditems>

            allFoodList!!.forEach {
                Log.d("checkingChoice","foodChoose:  $it")
            }
        }

        setFragment(foodMenuFragment())
    }

    private fun setFragment(fragment: Fragment) {
        if (fragment == null) return
        val fm = requireActivity().supportFragmentManager
        val tr = fm.beginTransaction()
        tr.replace(R.id.dashboard_FL, fragment).addToBackStack(null).commit()
    }

    override fun onLongClick(position: Int) {

    }

    private fun onbackPressed() {
        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                CommonUtils.showCustomDialog(requireActivity(),"Exit App?",
                    "Are you sure you want to exit the app?","Yes","Cancel",
                    false, object: AlertDialogListener {
                        override fun onPositiveClick() {
                            requireActivity().finish()
                            requireActivity().finishAffinity()
                            System.exit(0)
                            exitProcess(0)
                        }

                        override fun onNegativeClick() {
                        }

                    })
            }
        })
    }

}