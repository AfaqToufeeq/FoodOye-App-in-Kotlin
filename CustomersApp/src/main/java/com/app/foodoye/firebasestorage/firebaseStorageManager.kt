package com.app.foodoye.firebasestorage

import android.app.Dialog
import android.content.Context
import android.net.Uri
import android.widget.Toast
import com.app.foodoye.extensions.Utils
import com.app.foodoye.utils.PickerManager.downloadStorageUri
import com.google.firebase.storage.FirebaseStorage

class firebaseStorageManager {

    private val mStorageRef = FirebaseStorage.getInstance().reference
    lateinit var loader: Dialog

    fun uploadImage(mContext: Context, imageUri: Uri)
    {
        loader = Utils.progressDialog(mContext)
        if(!loader.isShowing) loader.show()

        val imageFileName="food/foodItems.png"
        val uploadTask = mStorageRef.child(imageFileName).putFile(imageUri)
        uploadTask.addOnSuccessListener {

            val downloadURL=mStorageRef.child(imageFileName).downloadUrl

            downloadURL.addOnSuccessListener {
                downloadStorageUri=it

            }.addOnFailureListener {
                if(loader.isShowing) loader.dismiss()
            }

            if(loader.isShowing) loader.dismiss()
        }.addOnFailureListener {

            if(loader.isShowing) loader.dismiss()
            Toast.makeText(mContext,"${it.message}",Toast.LENGTH_SHORT).show()
        }
    }
}