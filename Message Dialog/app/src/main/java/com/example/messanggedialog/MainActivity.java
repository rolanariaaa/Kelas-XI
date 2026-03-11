package com.example.messanggedialog;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showToast(View view) {
        showToast("Ini adalah pesan Toast!");
    }

    public void showAlert(View view) {
        showAlert("Anda telah melihat Alert Dialog!");
    }

    public void showAlertButton(View view) {
        showAlertButton("Apakah Anda yakin ingin melanjutkan?");
    }

    // Method untuk menampilkan Toast
    private void showToast(String pesan) {
        Toast.makeText(this, pesan, Toast.LENGTH_SHORT).show();
    }

    // Method untuk menampilkan Alert Dialog sederhana
    private void showAlert(String pesan) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Informasi Penting")
               .setMessage(pesan)
               .setPositiveButton("OK", null)
               .show();
    }

    // Method untuk menampilkan Alert Dialog dengan tombol
    private void showAlertButton(String pesan) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Konfirmasi Tindakan")
               .setMessage(pesan)
               .setPositiveButton("SETUJU", (dialog, which) -> {
                   showToast("Anda menyetujui tindakan ini.");
               })
               .setNegativeButton("BATAL", (dialog, which) -> {
                   showToast("Anda membatalkan tindakan ini.");
               })
               .show();
    }
}