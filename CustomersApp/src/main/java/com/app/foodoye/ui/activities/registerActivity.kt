package com.app.foodoye.ui.activities

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.app.foodoye.databinding.ActivityRegisterBinding
import com.app.foodoye.dataclass.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import java.util.regex.Pattern

class registerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference
    var dialog: ProgressDialog? = null
    private val PASSWORD_PATTERN = Pattern.compile(
        "^" +  //"(?=.*[0-9])" +         //at least 1 digit
                //"(?=.*[a-z])" +         //at least 1 lower case letter
                //"(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +  //any letter
                "(?=.*[@#$%^&+=])" +  //at least 1 special character
                "(?=\\S+$)" +  //no white spaces
                ".{6,20}" +  //at least 6 characters
                "$"
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
        buttonClicks()

    }
    private fun initViews()
    {
        auth = Firebase.auth
        database = FirebaseDatabase.getInstance()
            .getReferenceFromUrl("https://fooddelivery-foodoye-default-rtdb.firebaseio.com/")
        dialog = ProgressDialog(this@registerActivity)
        dialog!!.setTitle("Creating Account")
        dialog!!.setMessage("Creating your new account")
    }

    private fun buttonClicks() {
        binding.registerBtn.setOnClickListener {
            registration()
        }
        binding.alreadAccountBtn.setOnClickListener {
            startActivity(Intent(this@registerActivity,LoginActivity::class.java))
            finish()
        }
    }


    private fun registration() {

        val fullName = binding.userName.text.toString()
        val email = binding.email.text.toString()
        val pass = binding.pass.text.toString()
        val phoneNumber = binding.phone.text.toString()

        if (fullName.isBlank() || pass.isBlank() || email.isBlank() || phoneNumber.isBlank()) {
            Toast.makeText(this, "Please fill the form completely", Toast.LENGTH_SHORT).show()
            return
        }

        if(pass.length<6)
        {
            Toast.makeText(this, "Password should be atleast 6 characters", Toast.LENGTH_SHORT).show()
            return
        }

        dialog!!.show()
        auth.createUserWithEmailAndPassword(email, pass)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    registerNewUser(
                        auth.uid!!,
                        fullName,
                        email,
                        pass,
                        phoneNumber)
                }
                else {
                   if (dialog!!.isShowing)
                   {
                       dialog!!.dismiss()
                   }
                    Toast.makeText(baseContext, task.exception!!.message, Toast.LENGTH_SHORT).show()
                }
            }

    }

    fun registerNewUser(userId: String, name: String, email: String, pass: String, phone: String) {

        val user = User(name, email, pass, phone)

        database.child("Customers").child(userId).setValue(user)
            .addOnSuccessListener {
                if (dialog!!.isShowing)
                {
                    dialog!!.dismiss()
                }
                Toast.makeText(this@registerActivity, "Account Created Successfully", Toast.LENGTH_SHORT).show()
                FirebaseAuth.getInstance().signOut()
                startActivity(Intent(this@registerActivity, LoginActivity::class.java))
                finish()

            }.addOnFailureListener {
                if (dialog!!.isShowing)
                {
                    dialog!!.dismiss()
                }
                Toast.makeText(this@registerActivity, "Failed to create the account "+ it.message, Toast.LENGTH_SHORT).show()
            }
    }
}