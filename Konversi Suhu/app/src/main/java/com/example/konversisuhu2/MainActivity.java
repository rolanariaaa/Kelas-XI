package com.example.konversisuhu2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText etNilai;
    TextView tvHasil;
    Spinner spinnerKonversi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        load();

        // Tambahkan ini
        Button btnKonversi = findViewById(R.id.btnKonversi);
        btnKonversi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickKonversi(v);
            }
        });
    }

    // Inisialisasi komponen UI
    private void load() {
        etNilai = findViewById(R.id.etNilai);
        tvHasil = findViewById(R.id.tvHasil);
        spinnerKonversi = findViewById(R.id.spinnerKonversi);
    }

    // Dipanggil saat tombol konversi diklik
    public void onClickKonversi(View view) {
        String nilaiInput = etNilai.getText().toString().trim();
        if (nilaiInput.isEmpty()) {
            Toast.makeText(this, "Nilai tidak boleh kosong", Toast.LENGTH_SHORT).show();
            return;
        }

        double nilai;
        try {
            nilai = Double.parseDouble(nilaiInput);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Input harus berupa angka yang valid", Toast.LENGTH_SHORT).show();
            return;
        }

        String pilihan = spinnerKonversi.getSelectedItem().toString();
        double hasil = 0;

        // Menggunakan switch case untuk penanganan pilihan konversi yang lebih rapi
        switch (pilihan) {
            case "Celsius ke Fahrenheit":
                hasil = cToF(nilai);
                break;
            case "Fahrenheit ke Celsius":
                hasil = fToC(nilai);
                break;
            case "Celsius ke Kelvin":
                hasil = cToK(nilai);
                break;
            case "Kelvin ke Celsius":
                hasil = kToC(nilai);
                break;
            case "Fahrenheit ke Kelvin":
                hasil = fToK(nilai);
                break;
            case "Kelvin ke Fahrenheit":
                hasil = kToF(nilai);
                break;
            default:
                Toast.makeText(this, "Pilihan konversi tidak dikenali", Toast.LENGTH_SHORT).show();
                return;
        }

        tvHasil.setText(String.format("%.2f", hasil)); // Format hasil menjadi 2 angka di belakang koma
    }

    // --- Fungsi Konversi ---

    // Celsius
    private double cToR(double c) {
        return 0.8 * c; // R = 4/5 * C
    }

    private double cToF(double c) {
        return (1.8 * c) + 32; // F = (9/5 * C) + 32
    }

    private double cToK(double c) {
        return c + 273.15; // K = C + 273.15
    }

    // Reamur
    private double rToC(double r) {
        return r / 0.8; // C = R / (4/5)
    }

    private double rToF(double r) {
        return (r / 0.8) * 1.8 + 32; // F = (C * 9/5) + 32
    }

    private double rToK(double r) {
        return (r / 0.8) + 273.15; // K = C + 273.15
    }

    // Fahrenheit
    private double fToC(double f) {
        return (f - 32) / 1.8; // C = (F - 32) * 5/9
    }

    private double fToR(double f) {
        return (f - 32) / 2.25; // R = (F - 32) * 4/9
    }

    private double fToK(double f) {
        return ((f - 32) / 1.8) + 273.15; // K = C + 273.15
    }

    // Kelvin
    private double kToC(double k) {
        return k - 273.15; // C = K - 273.15
    }

    private double kToR(double k) {
        return (k - 273.15) * 0.8; // R = C * 4/5
    }

    private double kToF(double k) {
        return ((k - 273.15) * 1.8) + 32; // F = (C * 9/5) + 32
    }
}