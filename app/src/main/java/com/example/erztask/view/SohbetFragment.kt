package com.example.erztask.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.erztask.R
import com.example.erztask.adapter.KisilerAdapter
import com.example.erztask.adapter.MesajAdapter
import com.example.erztask.databinding.FragmentMainPageBinding
import com.example.erztask.databinding.FragmentSohbetBinding
import com.example.erztask.dbQuery.Query
import com.example.erztask.model.Mesaj
import com.example.erztask.model.Uye
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.auth.ktx.auth


class SohbetFragment : Fragment() {
    private var _binding: FragmentSohbetBinding? = null
    private val binding get() = _binding!!

    private  lateinit var auth: FirebaseAuth
    private lateinit var query: Query

    private var SohbetAdapter: MesajAdapter?=null
    private val mesajList:ArrayList<Mesaj> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth=Firebase.auth
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSohbetBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        SohbetAdapter = MesajAdapter(mesajList)
        binding.recylerMesaj.layoutManager = LinearLayoutManager(requireContext())
        binding.recylerMesaj.adapter = SohbetAdapter
        MesajlariGetir(view,mesajList,SohbetAdapter!!)
        binding.mesajIlet.setOnClickListener { MesajIlet(view) }

    }
    fun MesajIlet(view: View){
        query=Query()
        val email= auth.currentUser!!.email.toString()
        val mesaj = binding.editMesajText.text.toString()
        if (email.isNotEmpty() && mesaj.isNotEmpty()){
            query.MesajOlustur(view,email,mesaj){isSucces->
                if (isSucces==true){
                    binding.editMesajText.text.clear()
                    MesajlariGetir(view,mesajList,SohbetAdapter!!)
                    Toast.makeText(requireContext(),"Mesaj iletildi",Toast.LENGTH_SHORT).show()


                }else{
                    Toast.makeText(requireContext(),"Mesajlarınız iletilemiyor Lütfen daha sonra tekrar deneyiniiz.",Toast.LENGTH_SHORT).show()
                }
            }
        }else{
            Toast.makeText(requireContext(),"Mesaj Kutunuz boş lütfen bir mesaj",Toast.LENGTH_SHORT).show()
        }
    }
    fun MesajlariGetir(view: View,mesajList:ArrayList<Mesaj>,adapter: MesajAdapter){
        query=Query()
        query.MesajlariGetir(view,mesajList,adapter){isSucces->
           if (isSucces == true) {
               println("MesajlarGeliyor")
           }else{
               Toast.makeText(requireContext(),"Mesajlar Yüklenemedi. Lütfen Daha sonra tekrar deneyiniz.",Toast.LENGTH_SHORT).show()
           }

        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}