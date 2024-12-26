package com.example.erztask.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.erztask.databinding.KisilerRowBinding
import com.example.erztask.helper.Gecis
import com.example.erztask.model.Uye

class KisilerAdapter(private var uyeList:ArrayList<Uye>): RecyclerView.Adapter<KisilerAdapter.KisilerViewHolder>() {

    private lateinit var  gecis: Gecis
    class KisilerViewHolder(val binding: KisilerRowBinding):RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KisilerViewHolder {
        val binding = KisilerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return KisilerViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return uyeList.size
    }

    override fun onBindViewHolder(holder: KisilerViewHolder, position: Int) {
        gecis=Gecis()
        holder.binding.KisiName.text= uyeList[position].uyeName.toString()
        holder.binding.KisiTakim.text=uyeList[position].uyeTakim.toString()
        holder.itemView.setOnClickListener { gecis.KisilerToProfil(holder.itemView,uyeList[position].uyeEmail.toString())}
    }
}