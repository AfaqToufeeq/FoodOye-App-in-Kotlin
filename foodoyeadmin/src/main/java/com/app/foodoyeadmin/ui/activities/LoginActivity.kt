package com.app.foodoyeadmin.ui.activities

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.app.foodoyeadmin.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth
    var dialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
        buttonClicks()
    }

    private fun initViews()
    {
        auth = Firebase.auth
        dialog = ProgressDialog(this@LoginActivity)
        dialog!!.setTitle("Logging In")
        dialog!!.setMessage("Logging into your account")
    }

    private fun buttonClicks() {
        binding.loginBtn.setOnClickListener {
            loginUser()
        }

        binding.signup.setOnClickListener {
            startActivity(Intent(this@LoginActivity,registerActivity::class.java))
            finish()
        }
    }

    private fun loginUser() {
        val email = binding.emailad.text.toString()
        val pass = binding.ivPass.text.toString()

        if (email.isBlank() || pass.isBlank()) {
            Toast.makeText(this, "Please Enter the login Fields", Toast.LENGTH_SHORT).show()
            return
        }

        dialog!!.show()
        auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(this) {
            if (it.isSuccessful) {
                dialog!!.dismiss()
                Toast.makeText(this, "Successfully Logged In", Toast.LENGTH_SHORT).show()
                startActivity(Intent(applicationContext,DashboardActivity::class.java))
                finish()

            } else
            {
                dialog!!.dismiss()
                Toast.makeText(this, it.exception!!.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    public override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if(currentUser != null){
            startActivity(Intent(this@LoginActivity,DashboardActivity::class.java))
            finish()
        }
    }
}