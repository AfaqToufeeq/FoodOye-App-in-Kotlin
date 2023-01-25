package com.app.foodoyeadmin.firebasestorage

import android.app.Dialog
import android.app.ProgressDialog
import android.content.Context
import android.net.Uri
import android.widget.Toast
import com.app.foodoyeadmin.extensions.Utils
import com.app.foodoyeadmin.utils.PickerManager.downloadStorageUri
import com.app.foodoyeadmin.utils.PickerManager.imageFileName
import com.google.firebase.storage.FirebaseStorage

class firebaseStorageManager {

    private val mStorageRef = FirebaseStorage.getInstance().reference
    lateinit var loader: Dialog

    fun uploadImage(mContext: Context, imageUri: Uri)
    {
        loader = Utils.progressDialog(mContext)
        if(!loader.isShowing) loader.show()

        val uploadTask = mStorageRef.child(imageFileName!!).putFile(imageUri)
        uploadTask.addOnSuccessListener {

            val downloadURL=mStorageRef.child(imageFileName!!).downloadUrl

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