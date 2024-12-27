package com.example.erztask.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.erztask.adapter.KisilerAdapter.KisilerViewHolder
import com.example.erztask.databinding.KisilerRowBinding
import com.example.erztask.databinding.YorumRowBinding
import com.example.erztask.helper.Gecis
import com.example.erztask.model.Mesaj
import com.example.erztask.model.Uye

class MesajAdapter(private var mesajList:ArrayList<Mesaj>): RecyclerView.Adapter<MesajAdapter.MesajlarViewHolder>(){

    private lateinit var  gecis: Gecis
    class MesajlarViewHolder(val binding: YorumRowBinding):RecyclerView.ViewHolder(binding.root){

    }

    init {
        // Tarih bilgisine göre listeyi sıralıyoruz.
        mesajList.sortBy { it.MesajDate} // eventDate, sıralama için kullanılacak özellik
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MesajlarViewHolder {
        val binding = YorumRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MesajlarViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return mesajList.size
    }

    override fun onBindViewHolder(holder: MesajlarViewHolder, position: Int) {
        gecis=Gecis()
        holder.binding.YorumYapan.setText(mesajList[position].MesajYazanEmail)
        holder.binding.Yorum.setText(mesajList[position].Mesaj.toString())
    }
}