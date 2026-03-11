package com.example.sedekahyukukom.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sedekahyukukom.databinding.ItemRiwayatBinding
import com.example.sedekahyukukom.model.Riwayat
import com.example.sedekahyukukom.utils.FormatHelper

class RiwayatAdapter(
    private val riwayatList: List<Riwayat>
) : RecyclerView.Adapter<RiwayatAdapter.RiwayatViewHolder>() {
    
    inner class RiwayatViewHolder(private val binding: ItemRiwayatBinding) :
        RecyclerView.ViewHolder(binding.root) {
        
        fun bind(riwayat: Riwayat) {
            binding.tvJudul.text = riwayat.kampanyeJudul
            binding.tvNominal.text = FormatHelper.formatRupiah(riwayat.nominal)
            binding.tvTanggal.text = FormatHelper.formatDate(riwayat.tanggal)
            binding.tvMetode.text = riwayat.metodePembayaran
            binding.tvStatus.text = riwayat.status
        }
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RiwayatViewHolder {
        val binding = ItemRiwayatBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return RiwayatViewHolder(binding)
    }
    
    override fun onBindViewHolder(holder: RiwayatViewHolder, position: Int) {
        holder.bind(riwayatList[position])
    }
    
    override fun getItemCount(): Int = riwayatList.size
}
