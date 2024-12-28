package com.example.erztask.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.erztask.R
import com.example.erztask.databinding.FragmentAdminPaneliBinding
import com.example.erztask.databinding.FragmentGorevOlusturBinding
import com.example.erztask.dbQuery.Query
import com.example.erztask.helper.Gecis
import com.example.erztask.helper.SpinnerHelper


class GorevOlusturFragment : Fragment() {

    private var _binding: FragmentGorevOlusturBinding? = null
    private val binding get() = _binding!!
    private lateinit var query: Query
    private lateinit var spinnerHelper: SpinnerHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGorevOlusturBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        SpinnerVerisi(view)
        arguments?.let {
            val unvan = GorevOlusturFragmentArgs.fromBundle(it).UnvanBilgisi.toString()
            val adSoyad=GorevOlusturFragmentArgs.fromBundle(it).adSoyadBilgisi.toString()
            binding.GrvOlusturBtn.setOnClickListener { GorevOlustur(view,unvan,adSoyad) }
        }

    }
    fun GorevOlustur(view: View,unvan:String,adSoyad:String){
        query=Query()
        query.GorevOlustur(view,binding,unvan,adSoyad){isSucces->
            if (isSucces==true){
                Toast.makeText(requireContext(),"Gorev Başarılı bir şekilde oluşturuldu.",Toast.LENGTH_SHORT).show()
                findNavController().popBackStack()
            }else{
                Toast.makeText(requireContext(),"Gorev Olusturulurken Hata oldu. Lütfen sonra tekrar deneyiniz.",Toast.LENGTH_SHORT).show()
            }

        }
    }
    fun SpinnerVerisi(view: View){
        spinnerHelper=SpinnerHelper()
        spinnerHelper.Spin(binding.TSpinner,view,R.array.Takimlar)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}