package com.app.foodoyeadmin.ui.fragments

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.app.foodoyeadmin.R
import com.app.foodoyeadmin.databinding.FragmentAddFoodItemsBinding
import com.app.foodoyeadmin.dataclass.fooditems
import com.app.foodoyeadmin.dataclass.restaurantClass
import com.app.foodoyeadmin.extensions.Utils
import com.app.foodoyeadmin.firebasestorage.firebaseStorageManager
import com.app.foodoyeadmin.utils.PickerManager
import com.app.foodoyeadmin.utils.PickerManager.downloadStorageUri
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream

class addFoodItemsFragment : Fragment() {
    private lateinit var binding: FragmentAddFoodItemsBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference
    private lateinit var ref: DatabaseReference
    lateinit var loader: Dialog
    var bitmap: Bitmap?=null
    var uri:Uri?=null
    var imgURI:Uri? = null
    var foodName:String?=null
    var description:String?=null
    var price:String?=null
    val Camera_REQUEST_CODE = 200
    val Gallery_REQUEST_CODE = 100
    var restaurantID:String?=null
    var checkrestaurantID = false


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentAddFoodItemsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        buttonClicks()
        onbackPressed()
    }

    private fun restaurantSearch() {
        restaurantID = binding.restaurantIDET.text.toString()

        if(restaurantID!!.isBlank() )
        {
            Toast.makeText(requireActivity(), "Please Complete the details", Toast.LENGTH_SHORT).show()
            return
        }

        /**
         * Calling [function] getRestaurantDatafromFirebase
         */
        getRestaurantDatafromFirebase()

    }

    private fun initViews() {
        loader = Utils.progressDialog(requireActivity())
        auth = Firebase.auth
        database = FirebaseDatabase.getInstance()
            .getReferenceFromUrl("https://fooddelivery-foodoye-default-rtdb.firebaseio.com/")

        ref = FirebaseDatabase.getInstance()
            .getReferenceFromUrl("https://fooddelivery-foodoye-default-rtdb.firebaseio.com/")

    }

    private fun setFragment(fragment: Fragment?){
        if (fragment == null) return
        val fm = requireActivity().supportFragmentManager
        val tr = fm.beginTransaction()
        tr.replace(R.id.dashboard_FL, fragment).addToBackStack(null).commit()
    }



    private fun buttonClicks() {

        binding.searchRestaurantBtn.setOnClickListener {
            restaurantSearch()
        }

        binding.addToCartBtn.setOnClickListener {
            uploadFoodItemData()
        }

        binding.back2.setOnClickListener {
            setFragment(ordersFragment())
        }

        /**
         * Registering FoodImage View for ContextMenu
         * @View [binding.FoodImage]
         */
        registerForContextMenu(binding.FoodImage)
    }

    private fun onbackPressed() {
        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                setFragment(ordersFragment())
            }
        })
    }


    private fun getRestaurantDatafromFirebase() {
        if(!loader.isShowing) loader.show()

        database= FirebaseDatabase.getInstance().getReference("Restaurants")
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists())
                {
                    for(itemsSnapshot in snapshot.children)
                    {
//                        val restaurantItems = itemsSnapshot.getValue(restaurantClass::class.java)
                        val getID= itemsSnapshot.getValue(restaurantClass::class.java)!!.restaurantID

                        if(getID.equals(restaurantID))
                        {
                            checkrestaurantID=true
                        }
                    }

                    if (loader.isShowing) loader.dismiss()
                    if(checkrestaurantID) {
                        binding.parentCardView.visibility = View.VISIBLE
                        binding.restaurantCardView.visibility = View.GONE
                    }
                    else{
                        Toast.makeText(requireActivity(),
                            "Couldn't find restaurant ID $restaurantID",
                            Toast.LENGTH_SHORT).show()
                    }
                }
                if (loader.isShowing) loader.dismiss()
            }
            override fun onCancelled(error: DatabaseError) {
                if (loader.isShowing) loader.dismiss()
                Toast.makeText(requireActivity(),error.message+"", Toast.LENGTH_SHORT).show()
            }

        })

    }


    override fun onCreateContextMenu(
        menu: ContextMenu,
        v: View,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menu.setHeaderTitle("Pick option")
        requireActivity().menuInflater.inflate(R.menu.selectimage_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.cameraIMG -> cameraImage()
            R.id.galleryIMG -> selectGalleryImage()
        }
        return super.onContextItemSelected(item)
    }

    /**
     * Handling Images from and Gallery
     */
    private fun selectGalleryImage() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, Gallery_REQUEST_CODE)
    }

    private fun cameraImage() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(cameraIntent, Camera_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == Camera_REQUEST_CODE && data != null){
            val bitmap: Bitmap = data.extras!!.get("data") as Bitmap
            uri = getImageUri(requireActivity(),bitmap)
            binding.FoodImage.setImageURI(uri)
        }

        if (resultCode == Activity.RESULT_OK && requestCode == Gallery_REQUEST_CODE){
            uri = data?.data
            binding.FoodImage.setImageURI(uri) // handle chosen image
            binding.FoodImage.setTag(bitmap)
        }
    }

    /** To Convert Bitmap into Uri
     * @param inContext as Context
     * @param inImage as Bitmap
     * */
    fun getImageUri(inContext: Context, inImage: Bitmap): Uri? {
        val bytes = ByteArrayOutputStream()
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
        val path = MediaStore.Images.Media.insertImage(
            inContext.getContentResolver(),
            inImage,
            "Title",
            null
        )
        return Uri.parse(path)
    }

    private fun uploadFoodItemData() {
        foodName = binding.FoodName.text.toString()
        description = binding.FoodDescription.text.toString()
        price = binding.FoodPrice.text.toString()
        imgURI = uri

        if(imgURI == null){
            Toast.makeText(requireActivity(),"Please select image first",Toast.LENGTH_SHORT).show()
            return
        }

        if(foodName!!.isBlank() || price!!.isBlank() || description!!.isBlank())
        {
            Toast.makeText(requireActivity(), "Please Complete the details", Toast.LENGTH_SHORT).show()
            return
        }

        /**
         * Using Coroutines to upload Data in background thread
         * @fun [function] uploadDetails
         */

        GlobalScope.launch {
            uploadDetails()
        }

        /** Storing Files/Images
         * in Firebase Storage
         * @param imageFileName storing the name of FoodItem
         */

        PickerManager.imageFileName= "food/${foodName}.png"
        firebaseStorageManager().uploadImage(requireActivity(), imgURI!!)

        /**
         * Uploading data in realTime Database
         */

        Log.d("checkkuri","URI: ${downloadStorageUri}  : $imgURI")
//        if(downloadStorageUri!=null)
//        {
//            uploadFoodDetails(
//                auth.uid!!,
//                foodName,
//                description,
//                price,
//                downloadStorageUri!!
//            )
//        }
    }

    private suspend fun uploadDetails()
    {
        delay(10000)
     try {
         uploadFoodDetails(
             auth.uid!!,
             foodName!!,
             description!!,
             price!!,
             restaurantID!!,
             downloadStorageUri!!
         )
     }catch (e:Exception)
     {
         if(!loader.isShowing) loader.dismiss()
         Log.d("addFoodItemsRestaurant","${e.message}")

     }
    }

    private fun uploadFoodDetails(
        userId: String,
        foodName: String,
        description: String,
        price: String,
        restaurantID: String,
        downloadStorageUri: Uri
    ) {

        val foodData = fooditems(foodName, description, price , downloadStorageUri.toString(),restaurantID)

        ref.child("foodList").child(foodName).setValue(foodData)
            .addOnSuccessListener {
                if(loader.isShowing) loader.dismiss()
                Toast.makeText(requireActivity(), "Data Uploaded Successfully", Toast.LENGTH_SHORT).show()
                binding.FoodName.text.clear()
                binding.FoodDescription.text.clear()
                binding.FoodPrice.text.clear()
                binding.FoodImage.setBackgroundResource(R.drawable.add_item_icon)

            }.addOnFailureListener {
                if(loader.isShowing) loader.dismiss()

                Toast.makeText(requireActivity(), "Failed to upload data "+ it.message, Toast.LENGTH_SHORT).show()
            }
    }

}