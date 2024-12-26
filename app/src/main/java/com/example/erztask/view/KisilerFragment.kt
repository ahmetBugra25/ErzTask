package com.example.erztask.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.erztask.R
import com.example.erztask.adapter.KisilerAdapter
import com.example.erztask.databinding.FragmentKisilerBinding
import com.example.erztask.databinding.FragmentMainPageBinding
import com.example.erztask.dbQuery.Query
import com.example.erztask.model.Uye


class KisilerFragment : Fragment() {
    private var _binding: FragmentKisilerBinding? = null
    private val binding get() = _binding!!
    private var KisilerAdapter: KisilerAdapter?=null
    private val uyeList:ArrayList<Uye> = arrayListOf()
    private lateinit var query: Query

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentKisilerBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        KisileriGetir(view,uyeList,KisilerAdapter!!)
        KisilerAdapter = KisilerAdapter(uyeList)
        binding.recylerViewKisiler.layoutManager = LinearLayoutManager(requireContext())
        binding.recylerViewKisiler.adapter = KisilerAdapter
        binding.GeriButton.setOnClickListener { findNavController().popBackStack() }
    }
    fun KisileriGetir(view: View,uyeList:ArrayList<Uye>,adapter: KisilerAdapter){
       query=Query()
        query.UyeBilgileriniGetir(view,uyeList,adapter){isSucces->
            if (isSucces==true){
               Toast.makeText(requireContext(),"Kisiler Yükleniyor...",Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(requireContext(),"Kisiler Yüklenirken Hata Oluştu.Lütfen Daha sonra tekrar deneyiniz.",Toast.LENGTH_SHORT).show()
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}