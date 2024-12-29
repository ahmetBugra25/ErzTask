package com.example.erztask.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.erztask.R
import com.example.erztask.databinding.FragmentBitenGorevlerBinding
import com.example.erztask.databinding.FragmentGorevDetayBinding
import com.example.erztask.dbQuery.Query


class GorevDetayFragment : Fragment() {
    private var _binding: FragmentGorevDetayBinding? = null
    private val binding get() = _binding!!
    private lateinit var query: Query

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGorevDetayBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            val documentID=GorevDetayFragmentArgs.fromBundle(it).documentID.toString()
            GorevVerileriniCek(view,documentID)
        }
    }
    fun GorevVerileriniCek(view: View,documentID:String){
        query=Query()
        query.GorevDetayCek(view,binding,documentID){isSucces->
            if (isSucces==true){
                Toast.makeText(requireContext(),"Gorev Detayları Yükleniyor",Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(requireContext(),"Gorev Detayları Yüklenirken Hata oldu.",Toast.LENGTH_SHORT).show()
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}