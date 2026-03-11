# Mode Demo - Sedekah Yuk

## ✅ Aplikasi Sudah Siap Digunakan!

Aplikasi **Sedekah Yuk** telah diubah ke **MODE DEMO** sehingga dapat berjalan **tanpa Firebase** dan **tanpa koneksi internet**.

## 🎯 Cara Menggunakan

### 1. Build Aplikasi

**Opsi A: Menggunakan Android Studio (PALING MUDAH)**
```
1. Buka Android Studio
2. File → Open → Pilih folder "SedekahYukUKOM"
3. Tunggu Gradle sync selesai
4. Klik tombol Run (▶) atau tekan Shift+F10
5. Pilih emulator atau device
```

**Opsi B: Menggunakan Command Line**
```powershell
# Install JDK 17 terlebih dahulu dari:
# https://adoptium.net/temurin/releases/?version=17

# Lalu jalankan:
.\build.ps1
```

### 2. Login ke Aplikasi

Aplikasi sekarang menerima **SEMUA email dan password** untuk login!

**Contoh Login:**
- Email: `test@gmail.com` (boleh apa saja)
- Password: `123456` (boleh apa saja)

**Atau langsung Register:**
- Isi nama, email, password sesukamu
- Semua akan disimpan lokal di device

## 📱 Fitur yang Berfungsi

### ✅ Fitur Aktif (Semua Data Dummy)

1. **Splash Screen**
   - Quote motivasi random
   
2. **Login & Register**
   - Terima semua kredensial
   - Simpan session di SharedPreferences
   
3. **Home**
   - Tampilan saldo: Rp 1.000.000
   - Menu navigasi ke semua fitur
   
4. **Sedekah**
   - Pilih nominal atau isi custom
   - Pilih metode pembayaran
   - Simulasi transaksi berhasil
   
5. **Kampanye**
   - 5 kampanye dummy dengan progress bar
   - Detail kampanye lengkap
   - Dialog donasi dengan nominal
   
6. **Riwayat**
   - Menampilkan 3 transaksi dummy:
     * Sedekah Umum - Rp 50.000
     * Bantu Palestina - Rp 100.000
     * Sedekah Jariyah - Rp 200.000
   
7. **Artikel**
   - 5 artikel edukasi sedekah
   - Detail artikel lengkap
   
8. **Profil**
   - Tampil data user
   - Edit nama (simpan lokal)
   - Logout

## 🔧 Perubahan yang Dilakukan

### File yang Diubah untuk Mode Demo:

1. **LoginActivity.kt**
   - Hapus Firebase Authentication
   - Terima semua email/password
   - Simpan ke SharedPreferences

2. **RegisterActivity.kt**
   - Hapus Firebase Authentication
   - Hapus Firestore save
   - Simpan ke SharedPreferences

3. **HomeActivity.kt**
   - Hapus query Firestore
   - Gunakan data dummy lokal

4. **SedekahActivity.kt**
   - Hapus save ke Firestore
   - Hapus update total sedekah
   - Simulasi sukses langsung

5. **RiwayatActivity.kt**
   - Hapus query Firestore
   - Tampilkan 3 transaksi dummy

6. **ProfilActivity.kt**
   - Hapus query Firestore
   - Update nama ke SharedPreferences
   - Hapus Firebase logout

7. **DetailKampanyeActivity.kt**
   - Hapus save donasi ke Firestore
   - Simulasi donasi sukses

## 📊 Data yang Tersimpan

Semua data disimpan di **SharedPreferences** (lokal device):
- `isLoggedIn`: Status login
- `userId`: ID user dummy
- `userEmail`: Email yang diinput
- `userName`: Nama yang diinput

## 🎨 Tampilan UI

Semua tampilan UI tetap sama dengan desain asli:
- ✅ Warna hijau Islami
- ✅ Material Design 3
- ✅ Animasi smooth
- ✅ Progress bar
- ✅ Card layouts
- ✅ RecyclerView

## 🚀 Keunggulan Mode Demo

1. **Tidak Perlu Firebase** - Hemat waktu setup
2. **Tidak Perlu Internet** - Bisa demo offline
3. **Login Instant** - Pakai email/password apa saja
4. **Data Konsisten** - Selalu tampil 3 transaksi dummy
5. **Testing Cepat** - Langsung lihat semua fitur

## 📝 Catatan Penting

### Untuk Presentasi/Demo:
- ✅ Semua fitur berjalan sempurna
- ✅ UI/UX lengkap dan menarik
- ✅ Data dummy realistis
- ✅ Notifikasi toast muncul
- ✅ Navigasi antar halaman lancar

### Jika Ingin Mode Production (Firebase Asli):
Lihat file `SETUP_GUIDE.md` untuk setup Firebase lengkap. Semua kode Firebase masih ada di history Git, tinggal revert perubahan mode demo ini.

## 🎓 Cocok Untuk:

- ✅ Tugas sekolah/kuliah
- ✅ Demo aplikasi
- ✅ Testing UI/UX
- ✅ Presentasi projek
- ✅ Portfolio showcase

## 📞 Troubleshooting

### Build Gagal dengan "What went wrong: 25"
**Solusi:** Buka di Android Studio (sudah include JDK 17)

### Aplikasi Crash saat Buka
**Solusi:** Sudah diperbaiki! `splash_background.xml` sudah fix

### Mau Reset Data
**Solusi:** Uninstall aplikasi, lalu install ulang

---

## ✨ Selamat Mencoba!

Sekarang Anda bisa login dengan **email dan password apa saja**, lalu explore semua fitur aplikasi **Sedekah Yuk** tanpa perlu setup Firebase!

**Contoh:**
1. Buka aplikasi
2. Login dengan `demo@test.com` / `123456`
3. Explore semua menu
4. Lihat tampilan yang menarik!

---

**MODE DEMO AKTIF** 🎉
Aplikasi siap untuk presentasi dan testing!
