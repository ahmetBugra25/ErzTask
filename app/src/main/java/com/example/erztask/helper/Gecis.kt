package com.example.erztask.helper

import android.text.Layout.Directions
import android.view.View
import androidx.navigation.Navigation
import com.example.erztask.view.MainPageFragmentDirections

class Gecis {

    fun MaintoProfilim(view:View){
        val action = MainPageFragmentDirections.actionMainPageFragmentToProfilim()
        Navigation.findNavController(view).navigate(action)
    }

}