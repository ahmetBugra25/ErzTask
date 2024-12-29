package com.example.erztask.dbQuery

import android.util.Log
import android.view.View
import android.widget.Adapter
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.collection.arrayMapOf
import com.example.erztask.R
import com.example.erztask.adapter.GorevAdapter
import com.example.erztask.adapter.KisilerAdapter
import com.example.erztask.adapter.MesajAdapter
import com.example.erztask.databinding.FragmentGorevDetayBinding
import com.example.erztask.databinding.FragmentGorevOlusturBinding
import com.example.erztask.databinding.FragmentProfilimBinding
import com.example.erztask.helper.SpinnerHelper
import com.example.erztask.model.Gorev
import com.example.erztask.model.Mesaj
import com.example.erztask.model.Uye
import com.example.erztask.view.GorevOlusturFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import java.sql.Timestamp


class Query {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    private lateinit var spinnerHelper: SpinnerHelper


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
        try{
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
                            HataKontrol("Query Class - Profil Olustur","65",exception.localizedMessage,view)
                        }
                }
                .addOnFailureListener { exception ->
                    Log.e("FirebaseError", "Kullanıcı oluşturma hatası: ${exception.message}")
                    callback(false)
                    HataKontrol("Query Class - Profil Olustur","65",exception.localizedMessage,view)
                }
        }catch (e:Exception){
            callback(false)
            HataKontrol("Query Class - Profil Olustur","65",e.localizedMessage,view)
        }

    }
    /////////////////
    fun HataKontrol(
        HataninYeri:String,
        HataSatiri:String,
        HataninAciklamasi:String,
        view: View,
    ){
        try {
            val hataHashMap = arrayMapOf<String,Any>()
            hataHashMap.put("HatanınYeri",HataninYeri)
            hataHashMap.put("HataSatiri",HataSatiri)
            hataHashMap.put("HatanınAcıklaması",HataninAciklamasi)
            db.collection("Hatalar").add(hataHashMap).addOnSuccessListener { documentReferance->
                Toast.makeText(view.context,"Bir Hata Oluştu ve Hatanız sistemize kayıt edildi.",Toast.LENGTH_SHORT).show()
            }.addOnFailureListener { exception->
                Toast.makeText(view.context,"Hata Kontrolü ve Kaydı yapılamadı. Hata Mesajı: "+exception.localizedMessage,Toast.LENGTH_SHORT).show()
            }
        }catch (e:Exception){
            Toast.makeText(view.context,"Hata Kontrolü ve Kaydı yapılamadı. Hata Mesajı: "+e.localizedMessage,Toast.LENGTH_SHORT).show()
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
        try {
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
        }catch (e:Exception){
            HataKontrol("Query Class - Uye Guncelle ","134",e.localizedMessage.toString(),view)

        }
    }
    ////////////////////
    fun UyeBilgileriniGetir(
        view: View,
        uyeList:ArrayList<Uye>,
        adapter: KisilerAdapter,
        callback: (Boolean) -> Unit
    ){
        try {
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
                    HataKontrol("Query Class - UyeBilgileriGetir","161","Uye bilgileri bulunamadı",view)
                }
            }.addOnFailureListener { exception->
                callback(false)
                HataKontrol("Query Class - UyeBilgileriGetir","165",exception.localizedMessage.toString(),view)
            }
        }catch (e:Exception){
            HataKontrol("Query Class - UyeBilgileriGetir","168",e.localizedMessage.toString(),view)
        }


    }
    //////////////////////////////////
    fun UyeBilgisi(
        binding:FragmentProfilimBinding,
        email: String,
        view: View,
        callback: (Boolean) -> Unit
    ){
        try {
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
        }catch (e:Exception){
            HataKontrol("Query Class - Uye Bilgisi","180",e.localizedMessage,view)
        }
    }
    ////////////////////////////
    fun MesajOlustur(
        view: View,
        mesajYazanEmail:String,
        mesajText:String,
        callback: (Boolean) -> Unit
    ){
        try {
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
        }catch (e:Exception){
            HataKontrol("Query Class - Mesaj Olustur","199","Mesaj Oluşturulamıyor: "+e.localizedMessage,view)
        }

    }
    ////////////////////
    fun MesajlariGetir(
        view: View,
        MesajList:ArrayList<Mesaj>,
        adapter: MesajAdapter,
        callback: (Boolean) -> Unit
    ) {
        try {
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
                    HataKontrol("Query Class - MesajlariGetir","222","Mesaj Verisi bulunamıyor",view)
                }
            }.addOnFailureListener { exception->
                callback(false)
                HataKontrol("Query Class- MesajlariGetir","226",exception.localizedMessage,view)

            }
        }catch (e:Exception){
            HataKontrol("Query Class- MesajlariGetir","226",e.localizedMessage,view)
        }
    }
    ////////////////////////
    fun KulaniciIsimVeUnvan(view: View,callback: (ArrayList<String>) -> Unit){
        try {
            db.collection("Uyeler").whereEqualTo("UyeEmail",auth.currentUser!!.email.toString()).get().addOnSuccessListener { querySnapshot->
                if (querySnapshot!=null){
                    val documents=querySnapshot.documents
                    for (document in documents){
                        val kullaniciAd=document.getString("UyeAdSoyad")
                        val kullaniciUnvan = document.getString("UyeUnvani")
                        val list=ArrayList<String>()
                        list.add(kullaniciAd!!)
                        list.add(kullaniciUnvan!!)
                        callback(list)
                    }
                }
            }
        }catch (e:Exception){
            HataKontrol("Query Class - KullaniciIsımveUnvan","292",e.localizedMessage,view)
        }

    }
    /////////////////
    fun GorevOlustur(
        view: View,
        binding:FragmentGorevOlusturBinding,
        unvan:String,
        adsoyad: String,
        callback: (Boolean) -> Unit
    ){
        try {
            val gorevHashMap = hashMapOf<String,Any>()
            gorevHashMap.put("GorevBasligi",binding.editGorevBaslik.text.toString())
            gorevHashMap.put("GorevKonu",binding.editGorevKonu.text.toString())
            gorevHashMap.put("GorevOlusturmaTarihi",com.google.firebase.Timestamp.now())
            gorevHashMap.put("GorevTeslimTarihi",binding.editGorevDate.text.toString())
            gorevHashMap.put("GorevOlusturanMail",auth.currentUser!!.email.toString())
            gorevHashMap.put("GorevOlusturanIsim",adsoyad.toString())
            gorevHashMap.put("GorevOlusturanUnvan",unvan.toString())
            gorevHashMap.put("GorevYapacakTakim",binding.TSpinner.selectedItem.toString())
            gorevHashMap.put("GorevAciklama",binding.editGorevAciklama.text.toString())
            gorevHashMap.put("GorevTamamlanmaDurumu",false.toString())
            gorevHashMap.put("GorevTamamlanmaTarihi",binding.editGorevDate.text.toString())

            db.collection("Gorevler").add(gorevHashMap).addOnSuccessListener { documentReferance->
                println("true")
                callback(true)
            }.addOnFailureListener { exception->
                callback(false)
                println("false")
                HataKontrol("Query Class - Gorev Olustur","277",exception.localizedMessage,view)
            }
        }catch (e:Exception){
            HataKontrol("Query Class - Gorev Olustur","277",e.localizedMessage,view)
        }

    }
    /////////////////
    fun BitenGorevler(view: View, bitenGorevList:ArrayList<Gorev>, adapter: GorevAdapter, callback: (Boolean) -> Unit){
        try {
            db.collection("Gorevler").whereEqualTo("GorevTamamlanmaDurumu","true").get().addOnSuccessListener { querySnapshot->
                if (querySnapshot!=null){
                    bitenGorevList.clear()
                    val documents = querySnapshot.documents
                    for (document in documents){
                        val gorevTamamlanmaTarihi=document.getString("GorevTamamlanmaTarihi")
                        val gorevBaslik =document.getString("GorevBasligi")
                        val gorevKonu = document.getString("GorevKonu")
                        val gorevDocumentID = document.id.toString()
                        val newGorev = Gorev(gorevDocumentID,gorevBaslik!!,gorevKonu!!,gorevTamamlanmaTarihi!!,"BitenGorevler")
                        bitenGorevList.add(newGorev)
                        callback(true)
                    }
                    adapter?.notifyDataSetChanged()
                }else{
                    callback(false)
                    HataKontrol("Query Class - BitenGorevler","277","Biten görevler çekilemiyor.",view)
                }
            }.addOnFailureListener { exception ->
                callback(false)
                HataKontrol("Query Class - BitenGorevler","277",exception.localizedMessage,view)
            }
        }catch (e:Exception){
            callback(false)
            HataKontrol("Query Class - BitenGorevler","277",e.localizedMessage,view)

        }

    }
    ///////////
    fun YapilacakGorevler(view: View, bitenGorevList:ArrayList<Gorev>, adapter: GorevAdapter, callback: (Boolean) -> Unit){
        try {
            db.collection("Gorevler").whereEqualTo("GorevTamamlanmaDurumu","false").get().addOnSuccessListener { querySnapshot->
                if (querySnapshot!=null){
                    bitenGorevList.clear()
                    val documents = querySnapshot.documents
                    for (document in documents){
                        val gorevTeslimTarihi=document.getString("GorevTeslimTarihi")
                        val gorevBaslik =document.getString("GorevBasligi")
                        val gorevKonu = document.getString("GorevKonu")
                        val gorevDocumentID = document.id.toString()
                        val newGorev = Gorev(gorevDocumentID,gorevBaslik!!,gorevKonu!!,gorevTeslimTarihi!!,"YapilacakGorevler")
                        bitenGorevList.add(newGorev)
                        callback(true)
                    }
                    adapter?.notifyDataSetChanged()
                }else{
                    callback(false)
                    HataKontrol("Query Class - YapılacakGorevler","277","Yapılacaklar gelmiyor.",view)
                }
            }.addOnFailureListener { exception ->
                callback(false)
                HataKontrol("Query Class - BitenGorevler","277",exception.localizedMessage,view)
            }

        }catch (e:Exception){
            HataKontrol("Query Class - YapılacakGorevler","367",e.localizedMessage,view)
        }

    }
    /////////////////////////
    fun GorevDetayCek(view: View,binding: FragmentGorevDetayBinding,documentID:String,callback: (Boolean) -> Unit){
        try {
            db.collection("Gorevler").document(documentID).get().addOnSuccessListener { documentSnapshot->
                 if (documentSnapshot != null){
                     binding.GorevVerenIsim.setText(documentSnapshot.getString("GorevOlusturanIsim"))
                     spinnerHelper.Spin(binding.GorevDetayTakimSpinner,view,R.array.Takimlar)
                     val takimBilgisi=documentSnapshot.getString("GorevYapacakTakim")
                     val adapterTakim=binding.GorevDetayTakimSpinner.adapter as ArrayAdapter<String>
                     val takimSonBilgi= adapterTakim.getPosition(takimBilgisi)
                     binding.GorevDetayTakimSpinner.setSelection(takimSonBilgi)
                     binding.GorevTeslimTarihiText.setText(documentSnapshot.getString("GorevTeslimTarihi"))
                     binding.GorevBaslikText.setText(documentSnapshot.getString("GorevBasligi"))
                     binding.GorevKonuText.setText(documentSnapshot.getString("GorevKonu"))
                     binding.gorevAciklamaText.setText(documentSnapshot.getString("GorevAciklama"))
                     callback(true)
                 }else{
                     callback(false)
                     HataKontrol("Query Class - GorevDetayCek","417","Gorev Verileri bulunamadı",view)
                 }
            }.addOnFailureListener { exception->
                callback(false)
                HataKontrol("Query Class - GorevDetayCek","417",exception.localizedMessage,view)
            }
        }catch (e:Exception){
            HataKontrol("Query Class - GorevDetayCek","417",e.localizedMessage,view)

        }

    }

}