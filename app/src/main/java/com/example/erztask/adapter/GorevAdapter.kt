package com.example.erztask.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.erztask.adapter.MesajAdapter.MesajlarViewHolder
import com.example.erztask.databinding.GorevRowBinding
import com.example.erztask.databinding.YorumRowBinding
import com.example.erztask.helper.Gecis
import com.example.erztask.model.Gorev

class GorevAdapter(private var gorevList:ArrayList<Gorev>): RecyclerView.Adapter<GorevAdapter.GorevViewHolder>() {
    private lateinit var gecis: Gecis
    class GorevViewHolder(val binding: GorevRowBinding) : RecyclerView.ViewHolder(binding.root) {

    }
    init {
        // Tarih bilgisine göre listeyi sıralıyoruz.
        gorevList.sortByDescending { it.gorevTamamlanmaTarihi} // eventDate, sıralama için kullanılacak özellik
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GorevViewHolder {
        val binding = GorevRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return GorevViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return gorevList.size
    }

    override fun onBindViewHolder(holder: GorevViewHolder, position: Int) {
        gecis=Gecis()
        holder.binding.GorevKonu.setText(gorevList[position].gorevKonu.toString())
        holder.binding.GorevBaslik.setText(gorevList[position].gorevBaslik.toString())
        holder.binding.Tarih.setText(gorevList[position].gorevTamamlanmaTarihi.toString())
        holder.itemView.setOnClickListener {
            if (gorevList[position].goreveUlasilanYer =="BitenGorevler"){
                gecis.BitenGorevlerToGorevDetay(holder.itemView,gorevList[position].documentID)
            }else{
                gecis.YapilacaklarToGorevDetay(holder.itemView,gorevList[position].documentID)
            }
        }
    }
}