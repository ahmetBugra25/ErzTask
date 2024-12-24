package com.example.erztask.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.core.content.ContentProviderCompat
import androidx.navigation.Navigation
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
    private lateinit var query:Query

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
        query=Query()
        Spinner(binding.Ilspinner,view,R.array.iller)
        Spinner(binding.UnvanSpinner,view,R.array.Unvanlar)
        Spinner(binding.CalismaSekliSpinner,view,R.array.CalismaSekli)
        Spinner(binding.TakimSpinner,view,R.array.Takimlar)
        binding.ProfilEkleBtn.setOnClickListener { UyeEkle(it)}
    }
    fun UyeEkle(view: View){
        val email=binding.editEmail.text.toString()
        val sifre = binding.editPassword.text.toString()
        val name = binding.editName.text.toString()
        val bulunduguTakim=binding.TakimSpinner.selectedItem.toString()
        val uyeUnvani=binding.UnvanSpinner.selectedItem.toString()
        val uyeCalismaSekli=binding.CalismaSekliSpinner.selectedItem.toString()
         query?.let {
             query.ProfilOlusturma(email,sifre,name,bulunduguTakim,uyeUnvani,uyeCalismaSekli){isSucces->
                 if (isSucces==true){
                     Toast.makeText(requireContext(),"KullanıcıKaydı Başarılı",Toast.LENGTH_SHORT).show()
                     val action = YeniProfilFragmentDirections.actionYeniProfilFragmentToMainPageFragment()
                     Navigation.findNavController(view).navigate(action)
                 }else{
                     Toast.makeText(requireContext(),"KullanıcıKaydı Başarısız Oldu",Toast.LENGTH_SHORT).show()

                 }
             }
         }
    }

    fun Spinner(spinner: Spinner, view: View,textViewResId:Int){
        val adapter = ArrayAdapter.createFromResource(
            requireContext(),
            textViewResId,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}