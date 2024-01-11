package com.example.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.login.databinding.FragmentLoginBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var binding : FragmentLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater)

        binding.loginbutton.setOnClickListener {
            login()
        }

        binding.registerbutton.setOnClickListener {
            openRegisterPage()
        }

        return binding.root
    }

    fun login() {
        val firebaseAuth = Firebase.auth
        firebaseAuth.signInWithEmailAndPassword(
            binding.loginemail.text.toString(),
            binding.loginpassword.text.toString())
            .addOnSuccessListener {
                openApp()
            }.addOnFailureListener {
                Toast.makeText(context,"Yalnisliq oldu!",Toast.LENGTH_SHORT).show()
            }
    }

    fun openApp() {
        val action = LoginFragmentDirections.actionLoginFragmentToMenuFragment()
        findNavController().navigate(action)
    }

    fun openRegisterPage() {
        val action = LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
        findNavController().navigate(action)
    }

}