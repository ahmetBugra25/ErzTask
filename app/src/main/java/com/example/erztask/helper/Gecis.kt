package com.example.erztask.helper

import android.view.View
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.erztask.dbQuery.Query
import com.example.erztask.view.AdminPaneliFragmentDirections
import com.example.erztask.view.MainPageFragmentDirections
import com.example.erztask.view.SigInFragmentDirections
import com.example.erztask.view.YeniProfilFragmentDirections
import com.google.firebase.auth.FirebaseAuth

class Gecis {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private lateinit var query: Query

    fun SignInToAdmin(view: View){
        try {
            val action = SigInFragmentDirections.actionSigInFragmentToAdminPaneliFragment()
            Navigation.findNavController(view).navigate(action)
        } catch (e: Exception) {
            query=Query()
            query.HataKontrol("Gecis Class - SignInToAdmin","22",e.toString(),view)
        }
    }
    fun SignInToMain(view: View){
        try {
            val action = SigInFragmentDirections.actionSigInFragmentToMainPageFragment()
            Navigation.findNavController(view).navigate(action)
        } catch (e: Exception) {
            query=Query()
            query.HataKontrol("Gecis Class - SigInToMain","31",e.toString(),view)
        }
    }
    fun MainToProfilim(view:View){
        try {
            val action = MainPageFragmentDirections.actionMainPageFragmentToProfilim()
            Navigation.findNavController(view).navigate(action)
        } catch (e: Exception) {
            query=Query()
            query.HataKontrol("Gecis Class - MainToProfilim","40",e.toString(),view)
        }
    }
    fun MainToKisiler(view: View){
    }
    fun MainToBitenGorevler(view: View){
    }
    fun MainToTumGorevler(view: View){
    }
    fun MainToSohbetOdasi(view: View){

    }
    fun MainappQuit(view: View){
        try {
            auth.signOut()
            val action = MainPageFragmentDirections.actionMainPageFragmentToSigInFragment()
            Navigation.findNavController(view).navigate(action)
        }catch (e:Exception){
            query=Query()
            query.HataKontrol("Gecis Class - MainAppQuit","48",e.toString(),view)
        }

    }
    fun AdminAppQuit(view: View){
        try {
            auth.signOut()
            val action = AdminPaneliFragmentDirections.actionAdminPaneliFragmentToSigInFragment()
            Navigation.findNavController(view).navigate(action)
        }catch (e:Exception){
            query=Query()
            query.HataKontrol("Gecis Clası","53",e.toString(),view)
        }

    }
    fun YeniProfilToAdminPaneli(view: View){
        try {
            val action = YeniProfilFragmentDirections.actionYeniProfilFragmentToAdminPaneliFragment()
            Navigation.findNavController(view).navigate(action)
        }catch (e:Exception){
            query=Query()
            query.HataKontrol("Gecis Class - Yeni ProfilToAdmin","81",e.toString(),view)
        }
    }
    fun AdminPaneliToProfil(view: View){
        try {
            val action=AdminPaneliFragmentDirections.actionAdminPaneliFragmentToYeniProfilFragment()
            Navigation.findNavController(view).navigate(action)
        }catch (e:Exception){
            query=Query()
            query.HataKontrol("Gecis Class - AdminPaneliToProfil","90",e.toString(),view)
        }
    }

}