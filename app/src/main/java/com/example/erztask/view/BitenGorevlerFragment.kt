package com.example.erztask.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.erztask.R
import com.example.erztask.adapter.GorevAdapter
import com.example.erztask.adapter.KisilerAdapter
import com.example.erztask.databinding.FragmentBitenGorevlerBinding
import com.example.erztask.databinding.FragmentKisilerBinding
import com.example.erztask.dbQuery.Query
import com.example.erztask.model.Gorev
import com.example.erztask.model.Uye


class BitenGorevlerFragment : Fragment() {
    private var _binding: FragmentBitenGorevlerBinding? = null
    private val binding get() = _binding!!
    private var GorevAdapter: GorevAdapter?=null
    private val gorevList:ArrayList<Gorev> = arrayListOf()
    private lateinit var query: Query

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBitenGorevlerBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        GorevAdapter = GorevAdapter(gorevList)
        binding.recylerBitenGorevler.layoutManager = LinearLayoutManager(requireContext())
        binding.recylerBitenGorevler.adapter = GorevAdapter
        GorevleriGetir(view,gorevList,GorevAdapter!!)

    }
    fun GorevleriGetir(view: View,gorevList:ArrayList<Gorev>,adapter:GorevAdapter){
        query=Query()
        query.BitenGorevler(view,gorevList,adapter){isSucces->
            if (isSucces!=true){
                Toast.makeText(requireContext(),"Gorevler yüklenemiyor...",Toast.LENGTH_SHORT).show()
            }

        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}