package com.example.erztask.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.erztask.R
import com.example.erztask.databinding.FragmentSigInBinding
import com.example.erztask.databinding.FragmentYeniProfilBinding
import com.example.erztask.dbQuery.Query
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class YeniProfilFragment : Fragment() {
    private var _binding: FragmentYeniProfilBinding? = null
    private val binding get() = _binding!!

    private  lateinit var auth: FirebaseAuth
    private val query:Query?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth=Firebase.auth
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentYeniProfilBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        SpinnerIlIcerigi()
        SpinnerTakim()

    }
    public fun ProfilOlustur(view: View){

    }

    private fun SpinnerIlIcerigi(){
        val spinner = binding.Ilspinner
        val adapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.iller,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
    }
    private fun SpinnerTakim(){
        val spinner = binding.TakimSpinner
        val adapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.iller,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
    }
    private  fun
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}