package com.example.sedekahyukukom.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sedekahyukukom.databinding.ItemKampanyeBinding
import com.example.sedekahyukukom.model.Kampanye
import com.example.sedekahyukukom.utils.FormatHelper

class KampanyeAdapter(
    private val kampanyeList: List<Kampanye>,
    private val onItemClick: (Kampanye) -> Unit
) : RecyclerView.Adapter<KampanyeAdapter.KampanyeViewHolder>() {
    
    inner class KampanyeViewHolder(private val binding: ItemKampanyeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        
        fun bind(kampanye: Kampanye) {
            binding.tvJudul.text = kampanye.judul
            binding.tvKategori.text = kampanye.kategori
            binding.tvTarget.text = FormatHelper.formatRupiah(kampanye.targetDana)
            binding.tvTerkumpul.text = FormatHelper.formatRupiah(kampanye.terkumpul)
            binding.progressBar.progress = kampanye.getProgress()
            binding.tvProgress.text = "${kampanye.getProgress()}%"
            binding.ivKampanye.setImageResource(kampanye.gambar)
            binding.tvUrgency.text = kampanye.urgency
            
            binding.root.setOnClickListener {
                onItemClick(kampanye)
            }
            
            // Button donasi click
            binding.btnDonasi.setOnClickListener {
                onItemClick(kampanye)
            }
        }
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KampanyeViewHolder {
        val binding = ItemKampanyeBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return KampanyeViewHolder(binding)
    }
    
    override fun onBindViewHolder(holder: KampanyeViewHolder, position: Int) {
        holder.bind(kampanyeList[position])
    }
    
    override fun getItemCount(): Int = kampanyeList.size
}
