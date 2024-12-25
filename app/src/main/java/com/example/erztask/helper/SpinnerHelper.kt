package com.example.erztask.helper

import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner

class SpinnerHelper {
    fun Spin(spinner: Spinner, view: View, textViewResId:Int){
        val adapter = ArrayAdapter.createFromResource(
            view.context,
            textViewResId,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
    }
}