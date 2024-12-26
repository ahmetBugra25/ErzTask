package com.example.erztask.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.example.erztask.R
import com.example.erztask.databinding.FragmentMainPageBinding
import com.example.erztask.databinding.FragmentProfilimBinding
import com.example.erztask.databinding.FragmentYeniProfilBinding
import com.example.erztask.dbQuery.Query
import com.example.erztask.helper.SpinnerHelper
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth


class Profilim : Fragment() {
    private var _binding: FragmentProfilimBinding? = null
    private val binding get() = _binding!!

    private  lateinit var auth: FirebaseAuth

    private lateinit var spinner:SpinnerHelper
    private lateinit var query: Query


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth=Firebase.auth
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfilimBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        spinner = SpinnerHelper()
        query = Query()
        val currentUserEmail = auth.currentUser!!.email.toString()
        BtnEnabledControl(currentUserEmail)
        SpinnerCagrisi(view)
        binding.BtnGeri.setOnClickListener { findNavController().popBackStack() }
        binding.UyeGuncelleBtn.setOnClickListener {UyeGuncelle(it)}
    }
    fun UyeGuncelle(view: View){
        val email = binding.PeditEmail.text.toString()
        val tamAd=binding.PeditName.text.toString()
        val gorevYeri = binding.PilSpinner.selectedItem.toString()
        val bulunduguTakim=binding.PBulunduguTakim.selectedItem.toString()
        val unvan=binding.PUnvani.selectedItem.toString()
        val calismaSekli=binding.PCalismaSekli.selectedItem.toString()
        query.UyeGuncelle(email,tamAd,gorevYeri,bulunduguTakim,unvan,calismaSekli,view){isSucces->
            if (isSucces==true){
                Toast.makeText(requireContext(),"Uye bilgileri güncellendi",Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(requireContext(),"Uye Bilgileri güncellenemedi - Sebebi sisteme kayıt edildi.",Toast.LENGTH_SHORT).show()
            }
        }
    }
    fun SpinnerCagrisi(view: View){
        spinner=SpinnerHelper()
        spinner.Spin(binding.PilSpinner,view,R.array.iller)
        spinner.Spin(binding.PUnvani,view,R.array.Unvanlar)
        spinner.Spin(binding.PCalismaSekli,view,R.array.CalismaSekli)
        spinner.Spin(binding.PBulunduguTakim,view,R.array.Takimlar)
    }
    fun BtnEnabledControl(currentUserEmail:String){
        if (currentUserEmail=="admin@erz.com"){
            binding.PeditName.isEnabled=true
            binding.PeditEmail.isEnabled=true
            binding.PilSpinner.isEnabled=true
            binding.PCalismaSekli.isEnabled=true
            binding.PBulunduguTakim.isEnabled=true
            binding.PUnvani.isEnabled=true
            binding.UyeGuncelleBtn.isEnabled=true
            binding.UyeGuncelleBtn.isVisible=true

        }else{
            binding.PeditName.isEnabled=false
            binding.PeditEmail.isEnabled=false
            binding.PilSpinner.isEnabled=false
            binding.PCalismaSekli.isEnabled=false
            binding.PBulunduguTakim.isEnabled=false
            binding.PUnvani.isEnabled=false
            binding.UyeGuncelleBtn.isEnabled=false
            binding.UyeGuncelleBtn.isVisible=false

        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}