package com.example.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.login.databinding.FragmentRegisterBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.auth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater)

        binding.registerbutton.setOnClickListener {
            register()
        }

        return binding.root
    }

    fun register() {
        val firebaseAuth = Firebase.auth
        firebaseAuth.createUserWithEmailAndPassword(
            binding.registeremail.text.toString(),
            binding.registerpassword.text.toString())
            .addOnSuccessListener {
                findNavController().popBackStack()
            }.addOnFailureListener {
                (it as? FirebaseAuthException)?.errorCode?.let { errorCode->
                    showMessage("hata", MessageShow.getLocalizedMessage(errorCode))
                }

            }
    }


}