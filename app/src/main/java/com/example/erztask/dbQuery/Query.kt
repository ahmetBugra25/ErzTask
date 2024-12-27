package com.example.erztask.dbQuery

import android.util.Log
import android.view.View
import android.widget.Adapter
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.collection.arrayMapOf
import com.example.erztask.adapter.KisilerAdapter
import com.example.erztask.adapter.MesajAdapter
import com.example.erztask.databinding.FragmentProfilimBinding
import com.example.erztask.model.Mesaj
import com.example.erztask.model.Uye
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.sql.Timestamp


class Query {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    fun ProfilOlusturma(
        email: String,
        sifre: String,
        adsoyad: String,
        gorevYeri: String,
        bulunduguTakim: String,
        uyeUnvani: String,
        uyuCalismaSekli: String,
        view: View,
        callback: (Boolean) -> Unit
    ) {
        auth.createUserWithEmailAndPassword(email, sifre)
            .addOnSuccessListener { authResult ->
                val profilHashMap = hashMapOf(
                    "UyeEmail" to email,
                    "UyeAdSoyad" to adsoyad,
                    "GorevYeri" to gorevYeri,
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
    /////////////////
    fun HataKontrol(
        HataninYeri:String,
        HataSatiri:String,
        HataninAciklamasi:String,
        view: View,
    ){
        val hataHashMap = arrayMapOf<String,Any>()
        hataHashMap.put("HatanınYeri",HataninYeri)
        hataHashMap.put("HataSatiri",HataSatiri)
        hataHashMap.put("HatanınAcıklaması",HataninAciklamasi)
        db.collection("Hatalar").add(hataHashMap).addOnSuccessListener { documentReferance->
            Toast.makeText(view.context,"Bir Hata Oluştu ve Hatanız sistemize kayıt edildi.",Toast.LENGTH_SHORT).show()
        }.addOnFailureListener { exception->
            Toast.makeText(view.context,"Hata Kontrolü ve Kaydı yapılamadı. Hata Mesajı: "+exception.localizedMessage,Toast.LENGTH_SHORT).show()
        }

    }
    ///////////////
    fun UyeGuncelle(
        email: String,
        adsoyad: String,
        gorevYeri:String,
        bulunduguTakim: String,
        uyeUnvani: String,
        uyuCalismaSekli: String,
        view: View,
        callback: (Boolean) -> Unit
    ){

        val profilMap = hashMapOf<String,Any>()
        profilMap.put("UyeEmail",email)
        profilMap.put("UyeAdSoyad",adsoyad)
        profilMap.put("GorevYeri",gorevYeri)
        profilMap.put("BulunduguTakim",bulunduguTakim)
        profilMap.put("UyeUnvani",uyeUnvani)
        profilMap.put("UyeCalismaSekli",uyuCalismaSekli)
        db.collection("Uyeler").whereEqualTo("UyeEmail",email).get().addOnSuccessListener { querySnapshot->
            if(querySnapshot!=null){
                val documents = querySnapshot.documents
                for (document in documents){
                    db.collection("Uyeler").document(document.id).update(profilMap).addOnSuccessListener { querySnapshot->
                        callback(true)
                    }.addOnFailureListener { exception:Exception->
                        callback(false)
                        HataKontrol("Query Class - Uye Guncelle ","90",exception.localizedMessage.toString(),view)
                    }

                }
            }else{
                callback(false)
                HataKontrol("Query Class - Uye Guncelle ","90","Kullanıcı boş veriyi güncellemeye çalıştı.",view)
            }
        }

    }
    ////////////////////
    fun UyeBilgileriniGetir(
        view: View,
        uyeList:ArrayList<Uye>,
        adapter: KisilerAdapter,
        callback: (Boolean) -> Unit
    ){
        db.collection("Uyeler").get().addOnSuccessListener { documentReferance->
             if (documentReferance!=null){
                 val documents= documentReferance.documents
                 uyeList.clear()
                 for(document in documents){
                     val uyeEmail = document.getString("UyeEmail")
                     val uyeName = document.getString("UyeAdSoyad")
                     val uyeBulunduguTakim = document.getString("BulunduguTakim")
                     val newUye = Uye(uyeEmail!!,uyeName!!,uyeBulunduguTakim!!)
                     uyeList.add(newUye)
                     callback(true)
                 }
                 adapter?.notifyDataSetChanged()
             }else{
                 callback(false)
                 HataKontrol("Query Class - UyeBilgileriGetir","133","Uye bilgileri bulunamadı",view)
             }
        }.addOnFailureListener { exception->
            callback(false)
            HataKontrol("Query Class - UyeBilgileriGetir","137",exception.localizedMessage.toString(),view)
        }

    }
    //////////////////////////////////
    fun UyeBilgisi(
        binding:FragmentProfilimBinding,
        email: String,
        view: View,
        callback: (Boolean) -> Unit
    ){
        db.collection("Uyeler").whereEqualTo("UyeEmail",email).get().addOnSuccessListener { querySanapshot->
            if (querySanapshot!=null){
                val documents = querySanapshot.documents
                for (document in documents){
                    binding.PeditName.setText(document.getString("UyeAdSoyad"))
                    binding.PeditEmail.setText(document.getString("UyeEmail"))
                    val ilBilgisi =document.getString("GorevYeri")
                    val adapterIl = binding.PilSpinner.adapter as ArrayAdapter<String>
                    val position = adapterIl.getPosition(ilBilgisi)
                    binding.PilSpinner.setSelection(position)
                    val takimBilgisi=document.getString("BulunduguTakim")
                    val adapterTakim=binding.PBulunduguTakim.adapter as ArrayAdapter<String>
                    val takimSonBilgi= adapterTakim.getPosition(takimBilgisi)
                    binding.PBulunduguTakim.setSelection(takimSonBilgi)
                    val unvanBilgisi = document.getString("UyeUnvani")
                    val adapterUnvan = binding.PUnvani.adapter as ArrayAdapter<String>
                    val unvanSonBilgi = adapterUnvan.getPosition(unvanBilgisi)
                    binding.PUnvani.setSelection(unvanSonBilgi)
                    val calismaSekliBilgisi =document.getString("CalismaSekli")
                    val adapterCalismaSekli = binding.PCalismaSekli.adapter as ArrayAdapter<String>
                    val sonCalismaBilgisi = adapterCalismaSekli.getPosition(calismaSekliBilgisi)
                    binding.PCalismaSekli.setSelection(sonCalismaBilgisi)
                    callback(true)
                }
            }else{
                callback(false)
                HataKontrol("Query Class-UyeBilgisi","175","Uye bilgileri bulunamadı.",view)
            }

        }.addOnFailureListener { exception->
            callback(false)
            HataKontrol("Query Class - Uye Bilgisi","180",exception.localizedMessage,view)
        }

    }
    ////////////////////////////
    fun MesajOlustur(
        view: View,
        mesajYazanEmail:String,
        mesajText:String,
        callback: (Boolean) -> Unit
    ){
        val mesajHashMap= hashMapOf<String,Any>()
        mesajHashMap.put("MesajYazanEmail",mesajYazanEmail)
        mesajHashMap.put("Mesaj",mesajText)
        mesajHashMap.put("MesajTarihi",com.google.firebase.Timestamp.now())
        db.collection("Mesajlar").add(mesajHashMap).addOnSuccessListener { documentReferance->
            callback(true)
        }.addOnFailureListener { exception->
            callback(false)
            HataKontrol("Query Class - Mesaj Olustur","199","Mesaj Oluşturulamıyor: "+exception.localizedMessage,view)
        }
    }
    fun MesajlariGetir(view: View,MesajList:ArrayList<Mesaj>,adapter: MesajAdapter,callback: (Boolean) -> Unit){
        db.collection("Mesajlar").get().addOnSuccessListener { documentReferance->
            if (documentReferance!=null){
                val documents = documentReferance.documents
                MesajList.clear()
                for (document in documents){
                 val mesajYazanEmail=document.getString("MesajYazanEmail")
                 val yazilanMesaj = document.getString("Mesaj")
                 val mesajTarihi = document.getTimestamp("MesajTarihi")
                 val newMesaj = Mesaj(mesajYazanEmail!!,yazilanMesaj!!,mesajTarihi!!)
                 MesajList.add(newMesaj)
                 callback(true)
                }
                adapter?.notifyDataSetChanged()
            }else{
                callback(false)
            }
        }.addOnFailureListener { exception->
            callback(false)

        }

    }
}