package com.app.foodoye.applicationclass

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.google.firebase.database.FirebaseDatabase

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        //To Prevent Dark Mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        //Enabling Offline Capabilities on
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}