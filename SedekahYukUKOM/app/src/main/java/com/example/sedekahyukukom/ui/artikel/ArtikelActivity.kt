package com.example.sedekahyukukom.ui.artikel

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sedekahyukukom.adapter.ArtikelAdapter
import com.example.sedekahyukukom.databinding.ActivityArtikelBinding
import com.example.sedekahyukukom.model.Artikel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ArtikelActivity : AppCompatActivity() {

    private lateinit var binding: ActivityArtikelBinding
    private lateinit var adapter: ArtikelAdapter
    private val artikelList = mutableListOf<Artikel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArtikelBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupToolbar()
        setupRecyclerView()
        loadDummyData()
    }

    private fun setupToolbar() {
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    private fun setupRecyclerView() {
        adapter = ArtikelAdapter(artikelList) { artikel ->
            val intent = Intent(this, DetailArtikelActivity::class.java)
            intent.putExtra("ARTIKEL_ID", artikel.id)
            intent.putExtra("ARTIKEL_JUDUL", artikel.judul)
            intent.putExtra("ARTIKEL_KONTEN", artikel.konten)
            intent.putExtra("ARTIKEL_KATEGORI", artikel.kategori)
            startActivity(intent)
        }

        binding.rvArtikel.layoutManager = LinearLayoutManager(this)
        binding.rvArtikel.adapter = adapter
    }

    private fun loadDummyData() {
        artikelList.clear()
        artikelList.addAll(
            listOf(
                Artikel(
                    id = "1",
                    judul = "Keutamaan Sedekah dalam Islam",
                    konten = """
                        Sedekah merupakan salah satu amalan mulia dalam Islam yang memiliki banyak keutamaan. 
                        
                        Allah SWT berfirman dalam Al-Qur'an Surah Al-Baqarah ayat 261:
                        "Perumpamaan (nafkah yang dikeluarkan oleh) orang-orang yang menafkahkan hartanya di jalan Allah adalah serupa dengan sebutir benih yang menumbuhkan tujuh bulir, pada tiap-tiap bulir seratus biji. Allah melipat gandakan (ganjaran) bagi siapa yang Dia kehendaki. Dan Allah Maha Luas (karunia-Nya) lagi Maha Mengetahui."
                        
                        Keutamaan sedekah antara lain:
                        
                        1. Menghapus Dosa
                        Rasulullah SAW bersabda: "Sedekah dapat menghapus dosa sebagaimana air memadamkan api." (HR. Tirmidzi)
                        
                        2. Mendatangkan Keberkahan
                        Harta yang disedekahkan tidak akan berkurang, malah akan mendatangkan keberkahan dari Allah SWT.
                        
                        3. Melapangkan Rezeki
                        Allah SWT berjanji akan mengganti setiap harta yang disedekahkan dengan yang lebih baik.
                        
                        4. Menjadi Naungan di Hari Kiamat
                        Sedekah akan menjadi naungan bagi pelakunya di hari yang sangat panas.
                        
                        5. Obat Penyakit
                        Rasulullah SAW bersabda: "Obatilah orang-orang sakit kalian dengan sedekah."
                        
                        Mari kita perbanyak sedekah untuk meraih keutamaan-keutamaan tersebut. Sedekah tidak harus dalam jumlah besar, yang terpenting adalah keikhlasan dalam memberikannya.
                    """.trimIndent(),
                    kategori = "Keutamaan",
                    tanggal = SimpleDateFormat("dd MMMM yyyy", Locale("id", "ID")).format(Date())
                ),
                Artikel(
                    id = "2",
                    judul = "Hadis-Hadis Tentang Sedekah",
                    konten = """
                        Rasulullah SAW banyak menerangkan tentang keutamaan sedekah dalam berbagai hadis. Berikut beberapa hadis shahih tentang sedekah:
                        
                        1. Sedekah Tidak Mengurangi Harta
                        "Tidaklah berkurang harta karena sedekah, dan tidaklah Allah menambah kepada seorang hamba yang pemaaf melainkan kemuliaan, dan tidaklah seseorang merendahkan diri karena Allah melainkan Allah akan mengangkatnya." (HR. Muslim)
                        
                        2. Lindungi Diri dengan Sedekah
                        "Lindungilah dirimu dari api neraka walaupun hanya dengan (sedekah) setengah buah kurma." (HR. Bukhari dan Muslim)
                        
                        3. Sedekah Terbaik
                        "Sedekah terbaik adalah yang diberikan ketika dalam keadaan sehat dan merasa membutuhkan harta tersebut, bukan ketika sudah tua dan takut miskin." (HR. Bukhari dan Muslim)
                        
                        4. Tujuh Golongan yang Mendapat Naungan
                        Rasulullah SAW menyebutkan salah satunya adalah: "Seseorang yang bersedekah dengan tangan kanannya sehingga tangan kirinya tidak mengetahui apa yang dikeluarkan tangan kanannya." (HR. Bukhari dan Muslim)
                        
                        5. Setiap Kebaikan adalah Sedekah
                        "Setiap kebaikan adalah sedekah." (HR. Bukhari dan Muslim)
                        
                        6. Senyum adalah Sedekah
                        "Senyummu di hadapan saudaramu adalah sedekah." (HR. Tirmidzi)
                        
                        7. Sedekah untuk Keluarga
                        "Jika seorang muslim menafkahkan kepada keluarganya dengan mengharap pahala, maka nafkah tersebut menjadi sedekah baginya." (HR. Bukhari dan Muslim)
                        
                        Dari hadis-hadis di atas, kita dapat memahami bahwa sedekah memiliki banyak bentuk dan keutamaan. Mari kita amalkan dengan ikhlas.
                    """.trimIndent(),
                    kategori = "Hadis",
                    tanggal = SimpleDateFormat("dd MMMM yyyy", Locale("id", "ID")).format(Date())
                ),
                Artikel(
                    id = "3",
                    judul = "Jenis-Jenis Sedekah dalam Islam",
                    konten = """
                        Sedekah dalam Islam memiliki berbagai jenis dan bentuk. Berikut penjelasannya:
                        
                        1. SEDEKAH WAJIB
                        
                        a. Zakat Mal (Harta)
                        Zakat yang wajib dikeluarkan dari harta yang telah mencapai nisab dan haul (satu tahun). Jenisnya meliputi:
                        - Zakat emas dan perak
                        - Zakat perdagangan
                        - Zakat pertanian
                        - Zakat peternakan
                        
                        b. Zakat Fitrah
                        Zakat yang wajib dikeluarkan menjelang Idul Fitri, sebesar 3,5 liter makanan pokok atau nilai uangnya.
                        
                        2. SEDEKAH SUNNAH
                        
                        a. Infaq
                        Mengeluarkan harta di jalan Allah tanpa batasan waktu dan jumlah tertentu.
                        
                        b. Wakaf
                        Menyerahkan harta untuk kepentingan umum seperti masjid, sekolah, atau rumah sakit.
                        
                        c. Hibah
                        Memberikan harta kepada orang lain secara cuma-cuma ketika masih hidup.
                        
                        d. Hadiah
                        Memberikan sesuatu kepada orang lain sebagai bentuk kasih sayang.
                        
                        3. SEDEKAH NON-MATERI
                        
                        - Senyum kepada saudara seiman
                        - Menyingkirkan gangguan dari jalan
                        - Berbuat baik kepada tetangga
                        - Mengajarkan ilmu yang bermanfaat
                        - Mendamaikan yang berselisih
                        - Memberi minum kepada yang haus
                        - Menunjukkan jalan kepada yang tersesat
                        
                        4. SEDEKAH JARIYAH
                        
                        Sedekah yang pahalanya terus mengalir meski pemberi sudah meninggal:
                        - Membangun masjid
                        - Mencetak Al-Qur'an
                        - Menggali sumur
                        - Mendirikan sekolah
                        - Menulis buku bermanfaat
                        - Menanam pohon
                        
                        Semua jenis sedekah di atas memiliki keutamaan masing-masing. Pilihlah sesuai kemampuan dan keikhlasan hati.
                    """.trimIndent(),
                    kategori = "Pengetahuan",
                    tanggal = SimpleDateFormat("dd MMMM yyyy", Locale("id", "ID")).format(Date())
                ),
                Artikel(
                    id = "4",
                    judul = "Adab dan Etika Bersedekah",
                    konten = """
                        Dalam bersedekah, Islam mengajarkan adab dan etika yang harus diperhatikan agar sedekah diterima Allah SWT:
                        
                        1. IKHLAS KARENA ALLAH
                        Niat bersedekah harus murni karena Allah, bukan untuk pujian atau pamrih duniawi.
                        
                        Allah berfirman: "Dan mereka tidak diperintahkan kecuali supaya menyembah Allah dengan memurnikan ketaatan kepada-Nya." (QS. Al-Bayyinah: 5)
                        
                        2. DARI HARTA YANG HALAL
                        Pastikan harta yang disedekahkan berasal dari sumber yang halal dan baik.
                        
                        Rasulullah SAW bersabda: "Sesungguhnya Allah itu baik dan tidak menerima kecuali yang baik." (HR. Muslim)
                        
                        3. JANGAN DISERTAI MENYAKITI
                        Hindari menyakiti perasaan penerima sedekah dengan perkataan atau sikap yang meremehkan.
                        
                        "Wahai orang-orang yang beriman, janganlah kamu menghilangkan (pahala) sedekahmu dengan menyebut-nyebutnya dan menyakiti (perasaan si penerima)." (QS. Al-Baqarah: 264)
                        
                        4. JAGA KERAHASIAAN
                        Sedekah yang diam-diam lebih utama kecuali jika terang-terangan memberikan manfaat.
                        
                        "Jika kamu menampakkan sedekah(mu), maka itu baik sekali. Dan jika kamu menyembunyikannya dan kamu berikan kepada orang-orang fakir, maka menyembunyikan itu lebih baik bagimu." (QS. Al-Baqarah: 271)
                        
                        5. PILIH YANG BAIK
                        Berikan yang terbaik dari harta kita, bukan sisa atau yang tidak layak.
                        
                        "Kamu sekali-kali tidak sampai kepada kebajikan (yang sempurna), sebelum kamu menafkahkan sebahagian harta yang kamu cintai." (QS. Ali Imran: 92)
                        
                        6. SEGERA REALISASIKAN
                        Jangan tunda-tunda ketika ada niat bersedekah karena tidak tahu apa yang akan terjadi besok.
                        
                        7. HORMATI PENERIMA
                        Perlakukan penerima sedekah dengan hormat dan tidak merendahkan.
                        
                        8. BERSYUKUR
                        Bersyukur kepada Allah yang telah memberikan kemampuan untuk bersedekah.
                        
                        Dengan memperhatikan adab-adab ini, insya Allah sedekah kita akan lebih berkah dan bermanfaat.
                    """.trimIndent(),
                    kategori = "Adab",
                    tanggal = SimpleDateFormat("dd MMMM yyyy", Locale("id", "ID")).format(Date())
                ),
                Artikel(
                    id = "5",
                    judul = "Kisah Inspiratif Tentang Sedekah",
                    konten = """
                        Berikut beberapa kisah inspiratif tentang sedekah dari para salafus salih:
                        
                        1. KISAH ABU BAKAR ASH-SHIDDIQ
                        
                        Ketika Rasulullah SAW mengajak para sahabat untuk bersedekah, Abu Bakar membawa seluruh hartanya. Ketika ditanya apa yang ditinggalkan untuk keluarganya, beliau menjawab: "Saya tinggalkan Allah dan Rasul-Nya."
                        
                        Ini menunjukkan keimanan dan ketawakalan luar biasa kepada Allah SWT.
                        
                        2. KISAH USMAN BIN AFFAN
                        
                        Pada masa paceklik di Madinah, datang kafilah dagang milik Usman dengan 1000 unta berisi makanan. Para pedagang menawar dengan harga tinggi, tapi Usman berkata: "Ada yang membeli dengan harga lebih tinggi." Mereka menambah penawaran, namun Usman tetap menolak.
                        
                        Ternyata Usman bersedekah seluruh isi kafilah tersebut untuk rakyat Madinah karena Allah yang membayar dengan 10 kali lipat.
                        
                        3. KISAH SEORANG PEDAGANG KURMA
                        
                        Ada seorang pedagang yang setiap hari menjual kurma. Suatu hari datang seorang pengemis meminta kurma. Pedagang tersebut memberikan kurma terbaik yang baru saja ia sisihkan untuk dijual dengan harga mahal.
                        
                        Sang pengemis berkata: "Kenapa kau berikan yang terbaik? Yang jelek pun aku terima."
                        
                        Pedagang menjawab: "Karena Allah berfirman: 'Kamu tidak akan mendapat kebajikan sebelum menafkahkan sebagian yang kamu cintai.'"
                        
                        4. KISAH WANITA MISKIN DAN DEW AKHIR MALAMNYA
                        
                        Ada seorang wanita miskin yang hanya memiliki dua keping dirham. Suatu hari ada pengemis datang, wanita itu memberikan satu keping dirham. Esoknya, pengemis datang lagi, ia memberikan keping terakhirnya.
                        
                        Malam harinya ia berdoa: "Ya Allah, aku telah memberikan semua yang aku miliki karena-Mu."
                        
                        Pagi harinya, ada utusan raja yang mencari wanita tersebut untuk menikahkan dengan pangeran. Ternyata Allah ganti dengan yang jauh lebih baik.
                        
                        5. KISAH PEMUDA YANG BERSEDEKAH SETIAP HARI
                        
                        Ada seorang pemuda yang setiap pagi bersedekah apa saja yang ia miliki. Suatu hari, orang tuanya meninggal dan meninggalkan hutang besar. Para penagih hutang datang mengambil semua harta.
                        
                        Tiba-tiba datang seseorang mengembalikan uang yang ternyata merupakan sedekah-sedekah yang ia berikan selama ini, yang ternyata cukup untuk melunasi semua hutang orang tuanya.
                        
                        HIKMAH:
                        Kisah-kisah di atas mengajarkan bahwa:
                        - Sedekah tidak pernah membuat miskin
                        - Allah selalu mengganti dengan lebih baik
                        - Keikhlasan adalah kunci utama
                        - Sedekah adalah investasi akhirat terbaik
                        
                        Mari kita teladani para salaf dalam bersedekah dengan ikhlas dan tawakkal kepada Allah SWT.
                    """.trimIndent(),
                    kategori = "Kisah",
                    tanggal = SimpleDateFormat("dd MMMM yyyy", Locale("id", "ID")).format(Date())
                )
            )
        )
        adapter.notifyDataSetChanged()
    }
}
