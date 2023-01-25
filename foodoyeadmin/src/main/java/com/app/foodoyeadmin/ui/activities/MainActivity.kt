package com.app.foodoyeadmin.ui.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.app.foodoyeadmin.R
import com.app.foodoyeadmin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var splashTimeOut = 2500
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        splashActive()

    }

    private fun splashActive() {
        Handler().postDelayed({
            val intent = Intent(this@MainActivity, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }, splashTimeOut.toLong())
        val myAnim = AnimationUtils.loadAnimation(this, R.anim.mysplashanimation)
        binding.splash.startAnimation(myAnim)
    }
}