package com.app.foodoye.ui.fragments

import android.app.Dialog
import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.app.foodoye.R
import com.app.foodoye.databinding.FragmentProfileBinding
import com.app.foodoye.dataclass.User
import com.app.foodoye.extensions.Utils
import com.app.foodoye.ui.activities.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase


class profileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private lateinit var auth: FirebaseAuth
    lateinit var ref : DatabaseReference
    lateinit var loader: Dialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentProfileBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        showProfileData()
        buttonClicks()
    }
    private fun showProfileData() {
        loader.show()
        var user: User?
        try{
            val menuListener = object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    user = dataSnapshot.getValue(User::class.java)
                    loader.dismiss()
                    binding.UserNameTxt.text = user!!.username
                    binding.email.text = user!!.email
                    binding.phone.text = user!!.phone
                }
                override fun onCancelled(databaseError: DatabaseError) {
                    loader.dismiss()
                    Toast.makeText(requireActivity(),databaseError.message, Toast.LENGTH_SHORT).show()
                }
            }
            ref.addListenerForSingleValueEvent(menuListener)
        }catch (e:Exception)
        {
            Log.d("profileFragment","${e.message}")
        }
    }

    private fun initViews() {
        loader = Utils.progressDialog(requireActivity())
        auth = Firebase.auth
        ref= FirebaseDatabase.getInstance().getReference("Customers").child(FirebaseAuth.getInstance().currentUser!!.uid)
    }

    private fun buttonClicks() {
        binding.signOutButton.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            Toast.makeText(requireActivity(), "Logged Out", Toast.LENGTH_SHORT).show()
            startActivity(Intent(requireActivity(),LoginActivity::class.java))
            requireActivity().finish()
        }
        
        binding.back2.setOnClickListener { requireActivity().finish() }
    }
}