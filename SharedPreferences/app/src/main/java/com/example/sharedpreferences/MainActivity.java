package com.example.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // Ubah TextView menjadi EditText karena ini adalah kolom input
    EditText etBarang, etStok;
    
    // Tambahan EditText untuk data siswa
    EditText etNama, etTelepon, etAlamat;

    // Deklarasikan SharedPreferences sebagai variabel di tingkat kelas
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inisialisasi view dan SharedPreferences di sini
        etBarang = findViewById(R.id.etBarang);
        etStok = findViewById(R.id.etStok);
        
        // Inisialisasi view untuk data siswa
        etNama = findViewById(R.id.etNama);
        etTelepon = findViewById(R.id.etTelepon);
        etAlamat = findViewById(R.id.etAlamat);

        sharedPreferences = getSharedPreferences("barang", MODE_PRIVATE);
    }

    public void simpan(View view) {
        String barang = etBarang.getText().toString();

        // Tambahkan try-catch untuk menangani jika input stok bukan angka
        float stok = 0.0f;
        try {
            stok = Float.parseFloat(etStok.getText().toString());
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Stok harus berupa angka", Toast.LENGTH_SHORT).show();
            return;
        }

        if (barang.isEmpty() || stok == 0.0f) {
            Toast.makeText(this, "Nama barang atau stok tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("barang", barang);
            editor.putFloat("stok", stok);
            editor.apply();

            Toast.makeText(this, "Data sudah disimpan", Toast.LENGTH_SHORT).show();
            etBarang.setText("");
            etStok.setText("");
        }
    }

    // Metode tampil() untuk mengambil dan menampilkan data dari SharedPreferences
    public void tampil(View view) {
        String barang = sharedPreferences.getString("barang", "");
        float stok = sharedPreferences.getFloat("stok", 0);

        if (barang.isEmpty()) {
            Toast.makeText(this, "Tidak ada data yang tersimpan", Toast.LENGTH_SHORT).show();
        } else {
            etBarang.setText(barang);
            etStok.setText(String.valueOf(stok));
            Toast.makeText(this, "Data berhasil ditampilkan", Toast.LENGTH_SHORT).show();
        }
    }
    
    // Fungsi SharedPreferences untuk data siswa - SIMPAN
    public void simpanSiswa(View view) {
        String nama = etNama.getText().toString();
        String telepon = etTelepon.getText().toString();
        String alamat = etAlamat.getText().toString();

        if (nama.isEmpty() || telepon.isEmpty() || alamat.isEmpty()) {
            Toast.makeText(this, "Semua field data siswa harus diisi", Toast.LENGTH_SHORT).show();
        } else {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("nama_siswa", nama);
            editor.putString("telepon_siswa", telepon);
            editor.putString("alamat_siswa", alamat);
            editor.apply();

            Toast.makeText(this, "Data siswa berhasil disimpan", Toast.LENGTH_SHORT).show();
            etNama.setText("");
            etTelepon.setText("");
            etAlamat.setText("");
        }
    }
    
    // Fungsi SharedPreferences untuk data siswa - TAMPIL
    public void tampilSiswa(View view) {
        String nama = sharedPreferences.getString("nama_siswa", "");
        String telepon = sharedPreferences.getString("telepon_siswa", "");
        String alamat = sharedPreferences.getString("alamat_siswa", "");

        if (nama.isEmpty()) {
            Toast.makeText(this, "Tidak ada data siswa yang tersimpan", Toast.LENGTH_SHORT).show();
        } else {
            etNama.setText(nama);
            etTelepon.setText(telepon);
            etAlamat.setText(alamat);
            Toast.makeText(this, "Data siswa berhasil ditampilkan", Toast.LENGTH_SHORT).show();
        }
    }
}