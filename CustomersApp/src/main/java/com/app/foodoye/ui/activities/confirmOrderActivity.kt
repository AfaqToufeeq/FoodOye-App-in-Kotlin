package com.app.foodoye.ui.activities

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.foodoye.adapters.confirmOrderAdapter
import com.app.foodoye.databinding.ActivityConfirmOrderBinding
import com.app.foodoye.dataclass.fooditems
import com.app.foodoye.dataclass.foodorder
import com.app.foodoye.extensions.Utils
import com.app.foodoye.utils.PickerManager.addCartList
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.util.*

class confirmOrderActivity : AppCompatActivity() {
    lateinit var binding: ActivityConfirmOrderBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference
    var user: FirebaseUser? = null
    var fieldValid = true
    lateinit var loader: Dialog
    var fullName: String? = null
    var phone: String? = null
    var address: String? = null
    var commentsTV: String? = null
    var finalPriceTV: Int = 0
    var orderNo:String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfirmOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
        buttonClicks()
        buildRecyclerView()
    }

        private fun initViews() {
            auth = Firebase.auth
            database = FirebaseDatabase.getInstance()
                .getReferenceFromUrl("https://fooddelivery-foodoye-default-rtdb.firebaseio.com/")
            loader = Utils.progressDialog(this@confirmOrderActivity)

        }

        private fun buttonClicks() {
            binding.backButton.setOnClickListener {
                finish()
            }
            binding.cart.setOnClickListener {
                finish()
            }
            binding.placeOrderBtn.setOnClickListener {
                setData()
                uploadDataFirebase()
            }
        }

        private fun buildRecyclerView() {

            binding.confirmOrderRecyclerview.layoutManager = LinearLayoutManager(this@confirmOrderActivity)
            binding.confirmOrderRecyclerview.setHasFixedSize(true)
            binding.confirmOrderRecyclerview.addItemDecoration(
                DividerItemDecoration(
                    this@confirmOrderActivity,
                    (binding.confirmOrderRecyclerview.layoutManager as LinearLayoutManager).orientation
                )
            )
            binding.confirmOrderRecyclerview.adapter =
                confirmOrderAdapter(this@confirmOrderActivity, addCartList!!)

            addCartList!!.forEach {
                finalPriceTV += (it.quantity)!!.toInt()*(it.foodPrice)!!.toInt()
            }
            binding.finalPriceTV.text= "$finalPriceTV Rs Only"
        }

        private fun setData() {
            fullName = binding.fullName.text.toString()
            phone = binding.phone.text.toString()
            address = binding.address.text.toString()
            commentsTV = binding.commentsTV.text.toString()

            /**
             * Generating [Random] OrderNo
             * @param r as object to generate randomNo.
             * @param codes list to store random upto 9
             * @param orderNo store four numbers
             */
            val r = Random()
            val codes: MutableList<Int> = ArrayList()
            for (i in 0..9) {
                var x = r.nextInt(9999)
                while (codes.contains(x)) x = r.nextInt(9999)
                codes.add(x)
            }
            orderNo= String.format(
                "%04d", codes[0]
            )

        }

        private fun uploadDataFirebase() {

            /** validateField
             * to check if editTexts are not empty
             */
            validateField(binding.fullName)
            validateField(binding.phone)
            validateField(binding.address)
            if (fieldValid) {

                if (!loader.isShowing)  loader.show()

                /**Getting date and time
                 * @param uniqueKey to get the unique id
                 * @param date to store date
                 * @param time to store time
                 */
                val uniqueKey: String? = database.push().getKey()
                user = FirebaseAuth.getInstance().currentUser
                val calForDate = Calendar.getInstance()
                val currentDate = SimpleDateFormat("dd-MM-yy")
                val date = currentDate.format(calForDate.time)

                val calForTime = Calendar.getInstance()
                val currentTime = SimpleDateFormat("hh:mm a")
                val time = currentTime.format(calForTime.time)
                val status = "pending"

                proceedingUploading(
                    uniqueKey,
                    auth.uid,
                    date,
                    time,
                    fullName,
                    phone,
                    address,
                    commentsTV,
                    status,
                    finalPriceTV.toString(),
                    orderNo.toString(),
                    addCartList
                )
            }

        }

        private fun proceedingUploading(
            uniqueKey: String?,
            uid: String?,
            date: String,
            time: String,
            fullName: String?,
            phone: String?,
            address: String?,
            commentsTV: String?,
            status: String,
            finalPriceTV: String?,
            OrderNo: String?,
            addCartList: MutableList<fooditems>?
        ) {
            val foodOrderItems = foodorder(
                uniqueKey,
                uid,
                date,
                time,
                fullName,
                phone,
                address,
                commentsTV,
                status,
                finalPriceTV,
                OrderNo,
                addCartList
            )
            database.child("Orders").child(uniqueKey!!).setValue(foodOrderItems)
                .addOnSuccessListener {
                    if (loader!!.isShowing) {
                        loader!!.dismiss()
                    }
                    Toast.makeText(this@confirmOrderActivity, "Order is placed", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@confirmOrderActivity, DashboardActivity::class.java))
                    finish()

                }.addOnFailureListener {
                    if (loader!!.isShowing) {
                        loader!!.dismiss()
                    }
                    Toast.makeText(
                        this@confirmOrderActivity,
                        "Failed to continue the order " + it.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
        }

        fun validateField(field: EditText) {
            if (field.text.toString().isEmpty()) {
                field.error = "Required"
                fieldValid = false
            } else {
                fieldValid = true
            }
        }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}