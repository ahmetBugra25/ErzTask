package com.example.erztask.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.erztask.R
import com.example.erztask.adapter.GorevAdapter
import com.example.erztask.databinding.FragmentBitenGorevlerBinding
import com.example.erztask.databinding.FragmentYapilacakGorevlerBinding
import com.example.erztask.dbQuery.Query
import com.example.erztask.model.Gorev


class YapilacakGorevlerFragment : Fragment() {
    private var _binding: FragmentYapilacakGorevlerBinding? = null
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
        _binding = FragmentYapilacakGorevlerBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        GorevAdapter = GorevAdapter(gorevList)
        binding.recylerYapilacaklar.layoutManager = LinearLayoutManager(requireContext())
        binding.recylerYapilacaklar.adapter = GorevAdapter
        Gorevler(view,gorevList,GorevAdapter!!)
        binding.YapilacaklarGeriButton.setOnClickListener { findNavController().popBackStack() }

    }
    fun Gorevler(view: View,gorevList:ArrayList<Gorev>,adapter:GorevAdapter){
        query=Query()
        query.YapilacakGorevler(view,gorevList,adapter){isSucces->
            if (isSucces!=true){
                Toast.makeText(requireContext(),"Gorevler yüklenemiyor...", Toast.LENGTH_SHORT).show()
            }

        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}