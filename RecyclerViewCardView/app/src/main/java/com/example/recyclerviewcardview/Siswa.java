package com.example.recyclerviewcardview;

public class Siswa {
    private String nama;
    private String alamat;

    // Constructor
    public Siswa(String nama, String alamat) {
        this.nama = nama;
        this.alamat = alamat;
    }

    // Getter untuk nama
    public String getNama() {
        return nama;
    }

    // Setter untuk nama
    public void setNama(String nama) {
        this.nama = nama;
    }

    // Getter untuk alamat
    public String getAlamat() {
        return alamat;
    }

    // Setter untuk alamat
    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
}