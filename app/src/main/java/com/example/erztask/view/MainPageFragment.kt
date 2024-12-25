package com.example.erztask.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.erztask.R
import com.example.erztask.databinding.FragmentMainPageBinding
import com.example.erztask.helper.Gecis
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class MainPageFragment : Fragment() {
    private var _binding: FragmentMainPageBinding? = null
    private val binding get() = _binding!!

    private  lateinit var auth: FirebaseAuth
    private lateinit var GecisHelper:Gecis

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth=Firebase.auth
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainPageBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        println("MainPageAçıldı")
        GecisHelper=Gecis()
        binding.ProfilimBtn.setOnClickListener {GecisHelper.MainToProfilim(view)}
        binding.KisilerBtn.setOnClickListener { GecisHelper.MainToKisiler(view) }
        binding.SohbetOdasiBtn.setOnClickListener { GecisHelper.MainToSohbetOdasi(view) }
        binding.BitenGorevlerBtn.setOnClickListener { GecisHelper.MainToBitenGorevler(view) }
        binding.TumGorevlerBtn.setOnClickListener { GecisHelper.MainToTumGorevler(view) }
        binding.CikisYapBtn.setOnClickListener {  GecisHelper.MainappQuit(view)}

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}