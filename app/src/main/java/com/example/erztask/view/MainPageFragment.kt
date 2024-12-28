package com.example.erztask.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.erztask.R
import com.example.erztask.databinding.FragmentMainPageBinding
import com.example.erztask.dbQuery.Query
import com.example.erztask.helper.Gecis
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class MainPageFragment : Fragment() {
    private var _binding: FragmentMainPageBinding? = null
    private val binding get() = _binding!!

    private  lateinit var auth: FirebaseAuth
    private lateinit var GecisHelper:Gecis
    private lateinit var query: Query

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
        KullaniciVerisi(view)
        binding.ProfilimBtn.setOnClickListener {GecisHelper.MainToProfilim(view)}
        binding.KisilerBtn.setOnClickListener { GecisHelper.MainToKisiler(view) }
        binding.SohbetOdasiBtn.setOnClickListener { GecisHelper.MainToSohbetOdasi(view) }
        binding.BitenGorevlerBtn.setOnClickListener { GecisHelper.MainToBitenGorevler(view) }
        binding.TumGorevlerBtn.setOnClickListener { GecisHelper.MainToTumGorevler(view) }
        binding.CikisYapBtn.setOnClickListener {  GecisHelper.MainappQuit(view)}

    }
    fun KullaniciVerisi(view: View){
        query=Query()
        query.KulaniciIsimVeUnvan(view){list->
            if (list[1]!="Senior Mobil Developer Takım Kaptanı" &&
                list[1]!="Senior Web Developer Takım Kaptanı"&&
                list[1]!="Senior IsAnalisti Takım Kaptanı"&&
                list[1]!="Senior DevOps Developer Takım Kaptanı"&&
                list[1]!="Senior Veritabanı Developer Takım Kaptanı"
                ){
                Toast.makeText(requireContext(),"Sadece Takım Kaptanları Gorev Olusturabilir",Toast.LENGTH_SHORT).show()
            }else{
                binding.GorevOlusturBtn.setOnClickListener { GecisHelper.MainToGorevOlustur(view,list[1],list[0]) }
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}