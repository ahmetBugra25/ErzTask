package com.example.erztask.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.erztask.R
import com.example.erztask.databinding.FragmentAdminPaneliBinding
import com.example.erztask.databinding.FragmentMainPageBinding
import com.example.erztask.helper.Gecis


class AdminPaneliFragment : Fragment() {

    private var _binding: FragmentAdminPaneliBinding? = null
    private val binding get() = _binding!!
    private lateinit var gecis:Gecis

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAdminPaneliBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        gecis=Gecis()
        binding.adminProfil.setOnClickListener { gecis.AdminPaneliToProfil(view) }
        binding.adminCKSYap.setOnClickListener { gecis.AdminAppQuit(view) }
        binding.adminKisiler.setOnClickListener { gecis.AdminPaneliToKisiler(view) }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}