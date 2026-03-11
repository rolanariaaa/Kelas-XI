package com.example.intentactivity;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class TujuanActivity extends AppCompatActivity {
    private TextView tvPesanDiterima;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tujuan);

        tvPesanDiterima = findViewById(R.id.tvPesanDiterima);

        // Ambil data dari Intent
        String pesanDiterima = getIntent().getStringExtra(MainActivity.KEY_PESAN);
        
        // Tampilkan pesan yang diterima
        tvPesanDiterima.setText("Pesan Diterima: " + pesanDiterima);
    }
}
