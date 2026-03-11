package com.example.recyclerviewcardview;

import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View; // Import kelas View
import android.widget.Toast; // Import kelas Toast (untuk contoh)

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SiswaAdapter siswaAdapter;
    private List<Siswa> siswaList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inisialisasi RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true); // Opsional: untuk performa jika ukuran item tidak berubah

        // Mengatur LayoutManager (misalnya, LinearLayoutManager untuk daftar vertikal)
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Membuat data dummy (contoh)
        siswaList = new ArrayList<>();
        siswaList.add(new Siswa("Budi Joni", "JL Jawa Surabaya Jawa Timur"));
        siswaList.add(new Siswa("Siti Aminah", "JL Sumatera Jakarta Pusat"));
        siswaList.add(new Siswa("Agus Setiawan", "JL Kalimantan Bandung"));
        // Tambahkan lebih banyak data jika diperlukan

        // Membuat instance SiswaAdapter
        siswaAdapter = new SiswaAdapter(this, siswaList);

        // Mengatur adapter ke RecyclerView
        recyclerView.setAdapter(siswaAdapter);
    }

    /**
     * Metode ini akan dipanggil ketika tombol "TAMBAH" diklik.
     * Pastikan atribut android:onClick="btnTambah" ada di XML tombol Anda.
     */
    public void btnTambah(View view) {
        // Logika yang ingin Anda jalankan ketika tombol "TAMBAH" diklik
        Toast.makeText(this, "Tombol Tambah diklik!", Toast.LENGTH_SHORT).show();

        // --- Contoh Menambahkan Siswa Baru ke Daftar ---
        // Anda mungkin ingin mendapatkan nama dan alamat dari inputan pengguna
        // Untuk contoh ini, kita akan menambahkan siswa dummy baru setiap kali tombol diklik
        int newId = siswaList.size() + 1; // ID sederhana
        Siswa newSiswa = new Siswa("Siswa Baru " + newId, "Alamat Siswa Baru " + newId);

        // Tambahkan siswa baru ke daftar data Anda
        siswaList.add(newSiswa);

        // Beri tahu adapter bahwa ada data baru yang ditambahkan
        // Ini akan memicu RecyclerView untuk me-render ulang dan menampilkan item baru
        siswaAdapter.notifyItemInserted(siswaList.size() - 1); // Memberi tahu bahwa item ditambahkan di posisi terakhir

        // Opsional: Gulir ke item yang baru ditambahkan
        recyclerView.scrollToPosition(siswaList.size() - 1);
    }
}