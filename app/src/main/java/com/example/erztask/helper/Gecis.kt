package com.example.erztask.helper

import android.provider.ContactsContract.CommonDataKinds.Email
import android.view.View
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.erztask.dbQuery.Query
import com.example.erztask.view.AdminPaneliFragmentDirections
import com.example.erztask.view.KisilerFragmentDirections
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
            val action = MainPageFragmentDirections.actionMainPageFragmentToProfilim(auth.currentUser!!.email.toString())
            Navigation.findNavController(view).navigate(action)
        } catch (e: Exception) {
            query=Query()
            query.HataKontrol("Gecis Class - MainToProfilim","40",e.toString(),view)
        }
    }
    fun MainToKisiler(view: View){
        try {
            val action = MainPageFragmentDirections.actionMainPageFragmentToKisilerFragment()
            Navigation.findNavController(view).navigate(action)
        }catch (e:Exception){
            query=Query()
            query.HataKontrol("Gecis Class - MainToKisiler","52",e.localizedMessage,view)
        }
    }
    fun MainToBitenGorevler(view: View){
        try {
            val action = MainPageFragmentDirections.actionMainPageFragmentToBitenGorevlerFragment()
            Navigation.findNavController(view).navigate(action)
        }catch (e:Exception){
            query=Query()
            query.HataKontrol("Gecis Class - MainToBitenGorevler","61",e.localizedMessage,view)
        }
    }
    fun MainToTumGorevler(view: View){
    }
    fun MainToSohbetOdasi(view: View){
        try {
            val action = MainPageFragmentDirections.actionMainPageFragmentToSohbetFragment()
            Navigation.findNavController(view).navigate(action)
        }catch (e:Exception){
            query=Query()
            query.HataKontrol("Gecis Class - MainToSohbetOdasi","65",e.localizedMessage,view)
        }
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
    fun KisilerToProfil(view: View,email: String){
        try {
            val action = KisilerFragmentDirections.actionKisilerFragmentToProfilim(email)
            Navigation.findNavController(view).navigate(action)
        }catch (e:Exception){
            query=Query()
            query.HataKontrol("Gecis Class - KisilerToProfil","114",e.localizedMessage,view)
        }

    }
    fun AdminPaneliToKisiler(view: View){
        try {
            val action=AdminPaneliFragmentDirections.actionAdminPaneliFragmentToKisilerFragment()
            Navigation.findNavController(view).navigate(action)
        }catch (e:Exception){
            query=Query()
            query.HataKontrol("Gecis Class - AdminPaneliToKisiler","124",e.localizedMessage,view)
        }
    }
    fun AdminPaneliToSohbet(view: View){
        try {
            val action= AdminPaneliFragmentDirections.actionAdminPaneliFragmentToSohbetFragment()
            Navigation.findNavController(view).navigate(action)
        }catch (e:Exception){
            query=Query()
            query.HataKontrol("Gecis Class - AdminPaneliToSohbet","138",e.localizedMessage,view)
        }
    }
    fun MainToGorevOlustur(view: View,unvanBilgisi:String,adSoyad:String){
        try {
            val action =MainPageFragmentDirections.actionMainPageFragmentToGorevOlusturFragment(unvanBilgisi,adSoyad)
            Navigation.findNavController(view).navigate(action)
        }catch (e:Exception){
            query=Query()
            query.HataKontrol("Gecis Class - MainToGorevOlustur","142",e.localizedMessage,view)
        }

    }
    fun AdminToBitenGorevler(view: View){
        try {
            val action = AdminPaneliFragmentDirections.actionAdminPaneliFragmentToBitenGorevlerFragment()
            Navigation.findNavController(view).navigate(action)
        }catch (e:Exception){
            query=Query()
            query.HataKontrol("Gecis Class - AdminToBitenGorevler","159",e.localizedMessage,view)
        }
    }
}