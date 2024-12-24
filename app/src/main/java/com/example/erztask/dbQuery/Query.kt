package com.example.erztask.dbQuery

import android.util.Log
import android.widget.Toast
import androidx.collection.arrayMapOf
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class Query {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    fun ProfilOlusturma(
        email: String,
        sifre: String,
        adsoyad: String,
        bulunduguTakim: String,
        uyeUnvani: String,
        uyuCalismaSekli: String,
        callback: (Boolean) -> Unit
    ) {
        auth.createUserWithEmailAndPassword(email, sifre)
            .addOnSuccessListener { authResult ->
                val profilHashMap = hashMapOf(
                    "UyeEmail" to email,
                    "UyeAdSoyad" to adsoyad,
                    "BulunduguTakim" to bulunduguTakim,
                    "UyeUnvani" to uyeUnvani,
                    "UyeCalismaSekli" to uyuCalismaSekli
                )
                db.collection("Uyeler")
                    .add(profilHashMap)
                    .addOnSuccessListener {
                        callback(true)
                    }
                    .addOnFailureListener { exception ->
                        Log.e("FirebaseError", "Kayıt ekleme hatası: ${exception.message}")
                        callback(false)
                    }
            }
            .addOnFailureListener { exception ->
                Log.e("FirebaseError", "Kullanıcı oluşturma hatası: ${exception.message}")
                callback(false)
            }
    }

}