package com.example.sedekahyukukom.ui.kampanye

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sedekahyukukom.R
import com.example.sedekahyukukom.adapter.KampanyeAdapter
import com.example.sedekahyukukom.databinding.ActivityKampanyeBinding
import com.example.sedekahyukukom.model.Kampanye
import com.example.sedekahyukukom.utils.PreferenceManager

class KampanyeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityKampanyeBinding
    private lateinit var adapter: KampanyeAdapter
    private lateinit var preferenceManager: PreferenceManager
    private val kampanyeList = mutableListOf<Kampanye>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKampanyeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        preferenceManager = PreferenceManager(this)

        setupToolbar()
        setupRecyclerView()
        loadDummyData()
    }
    
    override fun onResume() {
        super.onResume()
        // Refresh data ketika kembali dari detail
        loadDummyData()
    }

    private fun setupToolbar() {
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    private fun setupRecyclerView() {
        adapter = KampanyeAdapter(kampanyeList) { kampanye ->
            val intent = Intent(this, DetailKampanyeActivity::class.java)
            intent.putExtra("KAMPANYE_ID", kampanye.id)
            intent.putExtra("KAMPANYE_JUDUL", kampanye.judul)
            intent.putExtra("KAMPANYE_DESKRIPSI", kampanye.deskripsi)
            intent.putExtra("KAMPANYE_KATEGORI", kampanye.kategori)
            intent.putExtra("KAMPANYE_TARGET", kampanye.targetDana)
            intent.putExtra("KAMPANYE_TERKUMPUL", kampanye.terkumpul)
            intent.putExtra("KAMPANYE_GAMBAR", kampanye.gambar)
            startActivity(intent)
        }

        binding.rvKampanye.layoutManager = LinearLayoutManager(this)
        binding.rvKampanye.adapter = adapter
    }

    private fun loadDummyData() {
        kampanyeList.clear()
        
        // Data base kampanye
        val baseKampanyeData = listOf(
            Triple("1", 50000000L, 35000000L) to Kampanye(
                id = "1",
                judul = "Bantuan Korban Bencana Alam",
                deskripsi = "Ribuan keluarga kehilangan tempat tinggal akibat bencana alam. Mari kita bantu meringankan beban saudara-saudara kita yang tertimpa musibah.",
                kategori = "Bencana Alam",
                targetDana = 50000000L,
                terkumpul = 35000000L,
                gambar = R.drawable.ic_kampanye,
                urgency = "🚨 Mendesak! Ribuan keluarga membutuhkan bantuan"
            ),
            Triple("2", 20000000L, 12000000L) to Kampanye(
                id = "2",
                judul = "Santunan Anak Yatim",
                deskripsi = "Bantuan pendidikan dan kebutuhan sehari-hari untuk 100 anak yatim. Mari berbagi kebahagiaan bersama mereka.",
                kategori = "Anak Yatim",
                targetDana = 20000000L,
                terkumpul = 12000000L,
                gambar = R.drawable.ic_kampanye,
                urgency = "❤️ Berikan harapan untuk masa depan mereka"
            ),
            Triple("3", 100000000L, 45000000L) to Kampanye(
                id = "3",
                judul = "Pembangunan Masjid Pelosok Desa",
                deskripsi = "Desa Sukamaju membutuhkan bantuan untuk membangun masjid sebagai pusat kegiatan ibadah masyarakat.",
                kategori = "Pembangunan Masjid",
                targetDana = 100000000L,
                terkumpul = 45000000L,
                gambar = R.drawable.ic_kampanye,
                urgency = "🕌 Bangun rumah Allah, raih pahala jariyah"
            ),
            Triple("4", 30000000L, 18000000L) to Kampanye(
                id = "4",
                judul = "Beasiswa Pendidikan Dhuafa",
                deskripsi = "Program beasiswa untuk anak-anak kurang mampu agar dapat melanjutkan pendidikan hingga jenjang universitas.",
                kategori = "Pendidikan",
                targetDana = 30000000L,
                terkumpul = 18000000L,
                gambar = R.drawable.ic_kampanye,
                urgency = "📚 Pendidikan adalah kunci masa depan"
            ),
            Triple("5", 15000000L, 9000000L) to Kampanye(
                id = "5",
                judul = "Bantuan Fakir Miskin",
                deskripsi = "Bantuan sembako dan kebutuhan pokok untuk keluarga prasejahtera di berbagai daerah.",
                kategori = "Fakir Miskin",
                targetDana = 15000000L,
                terkumpul = 9000000L,
                gambar = R.drawable.ic_kampanye,
                urgency = "🍚 Bantu saudara kita yang kelaparan"
            )
        )
        
        // Tambahkan donasi user ke setiap kampanye
        baseKampanyeData.forEach { (idData, kampanye) ->
            val userDonation = preferenceManager.getKampanyeDonation(idData.first)
            kampanyeList.add(
                kampanye.copy(terkumpul = kampanye.terkumpul + userDonation)
            )
        }
        
        adapter.notifyDataSetChanged()
    }
}
