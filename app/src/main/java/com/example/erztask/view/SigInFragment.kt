package com.example.erztask.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.erztask.R
import com.example.erztask.databinding.FragmentSigInBinding
import com.example.erztask.helper.Gecis
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class SigInFragment : Fragment() {

    private var _binding: FragmentSigInBinding? = null
    private val binding get() = _binding!!

    private  lateinit var auth: FirebaseAuth

    private lateinit var gecis: Gecis


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth=Firebase.auth
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSigInBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        gecis=Gecis()
        binding.GirisYap.setOnClickListener { GirisYap(it) }

    }
    private fun GirisYap(view: View){
        val email= binding.editEmail.text.toString()
        val pass=binding.editPass.text.toString()
        if (email.isNotEmpty() && pass.isNotEmpty()){
            auth.signInWithEmailAndPassword(email,pass).addOnSuccessListener { task ->
                if (binding.editEmail.text.toString()=="admin@erz.com"){
                    gecis.SignInToAdmin(view)
                }else{
                    gecis.SignInToMain(view)
                }

            }.addOnFailureListener { Exception ->
                Toast.makeText(requireContext(),"Giris Yapılamadı: "+Exception.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        }else{
            Toast.makeText(requireContext(),"Email ve password alanlarını doldurunuz...", Toast.LENGTH_SHORT).show()
        }

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}