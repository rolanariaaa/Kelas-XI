package com.example.sedekahyukukom.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sedekahyukukom.databinding.ItemArtikelBinding
import com.example.sedekahyukukom.model.Artikel

class ArtikelAdapter(
    private val artikelList: List<Artikel>,
    private val onItemClick: (Artikel) -> Unit
) : RecyclerView.Adapter<ArtikelAdapter.ArtikelViewHolder>() {
    
    inner class ArtikelViewHolder(private val binding: ItemArtikelBinding) :
        RecyclerView.ViewHolder(binding.root) {
        
        fun bind(artikel: Artikel) {
            binding.tvJudul.text = artikel.judul
            binding.tvKategori.text = artikel.kategori
            binding.tvTanggal.text = artikel.tanggal
            
            // Ambil preview konten (100 karakter pertama)
            val preview = if (artikel.konten.length > 100) {
                artikel.konten.substring(0, 100) + "..."
            } else {
                artikel.konten
            }
            binding.tvPreview.text = preview
            
            binding.root.setOnClickListener {
                onItemClick(artikel)
            }
        }
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtikelViewHolder {
        val binding = ItemArtikelBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ArtikelViewHolder(binding)
    }
    
    override fun onBindViewHolder(holder: ArtikelViewHolder, position: Int) {
        holder.bind(artikelList[position])
    }
    
    override fun getItemCount(): Int = artikelList.size
}
