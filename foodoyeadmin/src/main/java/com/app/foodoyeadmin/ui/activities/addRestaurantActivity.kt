package com.app.foodoyeadmin.ui.activities

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.app.foodoyeadmin.R
import com.app.foodoyeadmin.databinding.ActivityAddRestaurantBinding
import com.app.foodoyeadmin.dataclass.restaurantClass
import com.app.foodoyeadmin.extensions.Utils
import com.app.foodoyeadmin.firebasestorage.firebaseStorageManager
import com.app.foodoyeadmin.ui.fragments.addFoodItemsFragment
import com.app.foodoyeadmin.utils.PickerManager
import com.app.foodoyeadmin.utils.PickerManager.downloadStorageUri
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream
import java.util.*
import kotlin.collections.ArrayList

class addRestaurantActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddRestaurantBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference
    lateinit var loader: Dialog
    var restaurantName:String?=null
    var description:String?=null
    var restaurantID:String?=null
    val Camera_REQUEST_CODE = 200
    val Gallery_REQUEST_CODE = 100
    var bitmap: Bitmap?=null
    var uri: Uri?=null
    var imgURI: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddRestaurantBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
        buttonClicks()
    }

    private fun initViews() {

        loader = Utils.progressDialog(this@addRestaurantActivity)
        auth = Firebase.auth
        database = FirebaseDatabase.getInstance()
            .getReferenceFromUrl("https://fooddelivery-foodoye-default-rtdb.firebaseio.com/")

        /**
         * Generating [Random] OrderNo
         * @object [r] as object to generate randomNo.
         * @object [codes] list to store random upto 9
         * @object [orderNo] store four numbers
         */
        val r = Random()
        val codes: MutableList<Int> = java.util.ArrayList()
        for (i in 0..9) {
            var x = r.nextInt(9999)
            while (codes.contains(x)) x = r.nextInt(9999)
            codes.add(x)
        }
        restaurantID= String.format(
            "%04d", codes[0]
        )
    }

    private fun buttonClicks() {
        binding.addRestaurantBtn.setOnClickListener {
           if(binding.addRestaurantBtn.text=="Continue")
           {
               val intent= Intent(this@addRestaurantActivity,DashboardActivity::class.java)
               intent.putExtra("fragment","addFoodItemsFragment")
               startActivity(intent)
               finish()

           }else{
               uploadRestaurantData()
           }
        }

        binding.back2.setOnClickListener {
            finish()
        }
        /**
         * Registering FoodImage View for ContextMenu
         * @View [binding.FoodImage]
         */
        registerForContextMenu(binding.RestaurantImage)
    }


    override fun onCreateContextMenu(
        menu: ContextMenu,
        v: View,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menu.setHeaderTitle("Pick option")
        menuInflater.inflate(R.menu.selectimage_menu, menu)
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
            uri = getImageUri(this,bitmap)
            binding.RestaurantImage.setImageURI(uri)
        }

        if (resultCode == Activity.RESULT_OK && requestCode == Gallery_REQUEST_CODE){
            uri = data?.data
            binding.RestaurantImage.setImageURI(uri) // handle chosen image
            binding.RestaurantImage.setTag(bitmap)
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


    private fun uploadRestaurantData() {
        restaurantName = binding.restaurantNameET.text.toString()
        description = binding.restaurantDesET.text.toString()
        imgURI = uri

        if(restaurantName!!.isBlank() || description!!.isBlank())
        {
            if(imgURI == null){
                Toast.makeText(this@addRestaurantActivity,"Please select image first",Toast.LENGTH_SHORT).show()
            }

            Toast.makeText(this@addRestaurantActivity, "Please Complete the details", Toast.LENGTH_SHORT).show()
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
         * @param imageFileName storing the name of restaurant
         */

        PickerManager.imageFileName= "restaurants/${restaurantName}.png"
        firebaseStorageManager().uploadImage(this@addRestaurantActivity, imgURI!!)

        /**
         * Uploading data in realTime Database
         */

        Log.d("checkkuri","URI: ${PickerManager.downloadStorageUri}  : $imgURI")
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

    /**
     * concating restaurant name and orderNo.
     * @param restaurantName as Restaurant Name
     * @param restaurantID  as ID given to restaurant
     */

    private suspend fun uploadDetails()
    {
        delay(10000)
        restaurantID += restaurantName

        try{
            uploadRestaurantDetails(
                restaurantID!!,
                restaurantName!!,
                description!!,
                auth.uid!!,
                downloadStorageUri!!
            )
        }catch (e:Exception)
        {
            if(!loader.isShowing) loader.dismiss()
            Toast.makeText(this@addRestaurantActivity,"${e.message}",Toast.LENGTH_SHORT).show()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun uploadRestaurantDetails(
        restaurantID:String,
        restaurantName: String,
        description: String,
        uid: String,
        downloadStorageUri: Uri)
    {
        val restaurant = restaurantClass (
            restaurantID,
            restaurantName,
            description,
            auth.uid!!,
            downloadStorageUri.toString()
        )

        database.child("Restaurants").child(restaurantID).setValue(restaurant)
            .addOnSuccessListener {
                if (loader.isShowing)  loader.dismiss()
                Toast.makeText(this@addRestaurantActivity, "Restaurant's added successfully", Toast.LENGTH_SHORT).show()
                setVisibility()

            }.addOnFailureListener {
                if (loader.isShowing)  loader.dismiss()

                Toast.makeText(this@addRestaurantActivity, "${it.message}", Toast.LENGTH_SHORT).show()
            }

    }

    @SuppressLint("SetTextI18n")
    private fun setVisibility() {
        binding.addRestaurantBtn.text="Continue"
        binding.restaurantNameET.visibility=View.GONE
        binding.restaurantDesET.visibility=View.GONE
        binding.RestaurantImage.visibility=View.GONE
        binding.restaurantIDTV.visibility= View.VISIBLE
        binding.restaurantIDTV.text="Your Restaurant ID is $restaurantID"

    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

}