package com.example.login

import android.os.Bundle
import android.util.Patterns
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

        emailActivate()
        valid_password()

        binding.registerbutton.setOnClickListener {
            register()
        }
        binding.loginbackbutton.setOnClickListener {
            loginback()
        }

        return binding.root
    }

    private fun emailActivate(){
        binding.registeremail.setOnFocusChangeListener{_,focused->
            if(!focused){
                binding.registeremaillayout.helperText = valid_email()

            }
        }

        binding.registerpassword.setOnFocusChangeListener{_, focused->
            if(!focused){
                binding.registerpasswordlayout.helperText = valid_password()
            }
        }
    }

    private fun valid_password() : String? {

        val passwordtext = binding.registerpassword.text.toString()

        if (passwordtext.length < 8){
            return "Minimum character 8"
        }
        else if (!passwordtext.matches(".*[@#$%^&*!].*".toRegex()) ){
            return "Minimum (@#\$%^&*!.,?) one character "
        }
        return null

    }

    private fun valid_email() : String? {

        val emailText = binding.registeremail.text.toString()


        if (!Patterns.EMAIL_ADDRESS.matcher(emailText).matches()) {
            return "Invalid Email"
        }

        return null
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

    fun loginback(){
        findNavController().popBackStack()
    }


}