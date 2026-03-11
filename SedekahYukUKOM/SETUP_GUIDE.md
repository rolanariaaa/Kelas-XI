# 📘 Panduan Setup Aplikasi Sedekah Yuk

## 🎯 Panduan Lengkap Setup dari Awal

Dokumen ini berisi panduan step-by-step untuk setup aplikasi "Sedekah Yuk" dari awal hingga siap digunakan.

---

## 📋 Prerequisites

Pastikan Anda sudah memiliki:

1. **Android Studio** (versi terbaru - Arctic Fox atau lebih baru)
   - Download: https://developer.android.com/studio
   
2. **JDK 11** atau lebih tinggi
   - Biasanya sudah include dalam Android Studio
   
3. **Android SDK API Level 26+**
   - Akan di-download otomatis oleh Android Studio
   
4. **Koneksi Internet** (untuk download dependencies dan setup Firebase)

5. **Akun Gmail** (untuk membuat project Firebase)

---

## 🔥 LANGKAH 1: Setup Firebase

### 1.1. Buat Project Firebase

1. Buka browser dan kunjungi: https://console.firebase.google.com/
2. Klik tombol **"Add project"** atau **"Tambahkan project"**
3. Masukkan nama project: **SedekahYuk**
4. (Optional) Disable Google Analytics jika tidak diperlukan
5. Klik **"Create project"**
6. Tunggu hingga project selesai dibuat

### 1.2. Tambahkan Android App ke Firebase

1. Di Firebase Console, klik icon **Android** (robot hijau)
2. **Android package name**: `com.example.sedekahyukukom`
   - ⚠️ **PENTING**: Package name harus sama persis!
3. **App nickname**: Sedekah Yuk (optional)
4. **Debug signing certificate SHA-1**: Kosongkan dulu (optional)
5. Klik **"Register app"**

### 1.3. Download google-services.json

1. Download file **google-services.json**
2. Copy file tersebut ke folder project:
   ```
   SedekahYukUKOM/app/google-services.json
   ```
3. ⚠️ **PENTING**: File ini HARUS ada di folder `app/`, bukan di root project!

### 1.4. Enable Firebase Authentication

1. Di Firebase Console, pilih menu **"Authentication"** di sidebar kiri
2. Klik tab **"Sign-in method"**
3. Klik **"Email/Password"**
4. **Enable** toggle switch
5. Klik **"Save"**

### 1.5. Setup Firestore Database

1. Di Firebase Console, pilih menu **"Firestore Database"**
2. Klik **"Create database"**
3. Pilih **"Start in test mode"** (untuk development)
4. Pilih lokasi server (pilih yang terdekat, misalnya: asia-southeast1)
5. Klik **"Enable"**

### 1.6. Konfigurasi Firestore Rules (Test Mode)

Di tab **"Rules"**, pastikan rules-nya seperti ini:

```javascript
rules_version = '2';
service cloud.firestore {
  match /databases/{database}/documents {
    match /{document=**} {
      allow read, write: if request.auth != null;
    }
  }
}
```

Klik **"Publish"**

---

## 💻 LANGKAH 2: Setup Project di Android Studio

### 2.1. Open Project

1. Buka **Android Studio**
2. Pilih **"Open"** atau **"Open an Existing Project"**
3. Navigate ke folder **SedekahYukUKOM**
4. Klik **"OK"**

### 2.2. Sync Gradle

1. Android Studio akan otomatis sync Gradle
2. Jika tidak, klik icon **"Sync Project with Gradle Files"** (icon gajah di toolbar)
3. Tunggu hingga sync selesai (bisa 5-15 menit tergantung internet)
4. Pastikan tidak ada error di build output

### 2.3. Resolve Dependencies Issues (jika ada)

Jika ada error dependencies:

1. **File > Project Structure**
2. Pastikan:
   - **Gradle Version**: 8.11 atau terbaru
   - **Android Gradle Plugin**: 8.11.2 atau compatible
   - **Compile SDK**: 36 atau sesuai yang ada
   - **Build Tools Version**: Latest

3. Klik **"Apply"** dan **"OK"**

---

## 📱 LANGKAH 3: Running Aplikasi

### 3.1. Menggunakan Emulator

#### Buat Emulator Baru (jika belum ada):

1. Klik icon **"Device Manager"** di toolbar (icon HP)
2. Klik **"Create Device"**
3. Pilih device: **Pixel 5** atau **Pixel 6** (recommended)
4. Klik **"Next"**
5. Pilih System Image:
   - **Recommended**: API 34 (Android 14) atau API 33 (Android 13)
   - Download jika belum ada
6. Klik **"Next"**
7. Beri nama: "Sedekah Yuk Emulator"
8. Klik **"Finish"**

#### Run di Emulator:

1. Pilih emulator dari dropdown device
2. Klik tombol **"Run"** (icon play hijau) atau tekan **Shift + F10**
3. Tunggu emulator booting (first time bisa lama)
4. Aplikasi akan otomatis terinstall dan terbuka

### 3.2. Menggunakan Real Device

1. Enable **Developer Options** di HP:
   - Settings > About phone
   - Tap "Build number" 7 kali
   
2. Enable **USB Debugging**:
   - Settings > Developer options
   - Enable "USB debugging"
   
3. Hubungkan HP ke komputer via USB

4. Di HP, pilih **"Allow"** saat muncul popup USB debugging

5. Di Android Studio:
   - Pilih device Anda dari dropdown
   - Klik **"Run"**

---

## ✅ LANGKAH 4: Testing Aplikasi

### 4.1. First Time Run

Saat pertama kali membuka aplikasi:

1. **Splash Screen** akan muncul dengan kutipan random
2. Setelah 3 detik, akan redirect ke **Login Screen**

### 4.2. Register Akun Baru

1. Di Login Screen, klik **"Daftar di sini"**
2. Isi form:
   - **Nama**: Nama lengkap Anda
   - **Email**: Email valid (contoh: test@gmail.com)
   - **Password**: Minimal 6 karakter
   - **Konfirmasi Password**: Sama dengan password
3. Klik **"Daftar"**
4. Tunggu proses registrasi (akan otomatis redirect ke Login)

### 4.3. Login

1. Masukkan email dan password yang tadi didaftarkan
2. Klik **"Masuk"**
3. Anda akan masuk ke **Home Screen**

### 4.4. Test Semua Fitur

✅ **Home Screen**:
- Lihat saldo dummy (Rp 1.000.000)
- Lihat total sedekah (awalnya Rp 0)
- Cek apakah nama user muncul

✅ **Sedekah**:
- Klik menu "Sedekah"
- Pilih nominal (Rp 5.000, 10.000, 20.000, 50.000)
- Atau input nominal custom
- Pilih metode pembayaran
- Klik "Sedekah Sekarang"
- Konfirmasi
- Cek notifikasi sukses

✅ **Kampanye**:
- Klik menu "Kampanye"
- Lihat daftar kampanye
- Klik salah satu kampanye untuk lihat detail
- Coba donasi ke kampanye

✅ **Riwayat**:
- Klik menu "Riwayat"
- Lihat daftar transaksi yang sudah dilakukan
- Cek detail tanggal, nominal, dan status

✅ **Artikel**:
- Klik menu "Artikel"
- Lihat daftar artikel
- Klik artikel untuk baca selengkapnya

✅ **Profil**:
- Klik menu "Profil"
- Lihat data user
- Coba edit nama
- Test tombol logout

---

## 🐛 Troubleshooting

### Error: "google-services.json not found"

**Solusi**:
1. Pastikan file `google-services.json` ada di folder `app/`
2. Sync Gradle lagi
3. Clean Project: **Build > Clean Project**
4. Rebuild: **Build > Rebuild Project**

### Error: Firebase Authentication failed

**Solusi**:
1. Cek koneksi internet
2. Pastikan Email/Password sudah di-enable di Firebase Console
3. Pastikan package name di Firebase sama dengan di app: `com.example.sedekahyukukom`

### Error: Firestore permission denied

**Solusi**:
1. Buka Firebase Console > Firestore Database > Rules
2. Pastikan rules sudah benar (lihat section 1.6)
3. Publish rules
4. Tunggu 1-2 menit untuk propagasi

### Error: Build failed / Gradle sync issues

**Solusi**:
1. **File > Invalidate Caches > Invalidate and Restart**
2. Delete folder `.gradle` dan `build` di project
3. Sync Gradle lagi
4. Pastikan koneksi internet stabil

### App crash saat dibuka

**Solusi**:
1. Cek Logcat di Android Studio untuk error message
2. Pastikan `google-services.json` sudah benar
3. Pastikan Firebase project sudah complete setup
4. Clean & Rebuild project

---

## 📝 Catatan Penting

### Untuk Production

Jika ingin deploy ke production (Google Play Store):

1. **Ganti Firestore Rules** ke production mode:
```javascript
rules_version = '2';
service cloud.firestore {
  match /databases/{database}/documents {
    match /users/{userId} {
      allow read, write: if request.auth != null && request.auth.uid == userId;
    }
    match /riwayat/{riwayatId} {
      allow read, write: if request.auth != null;
    }
  }
}
```

2. **Enable Email Verification**:
   - Tambah kode untuk send verification email
   - User harus verify email sebelum bisa login

3. **Generate Signed APK**:
   - **Build > Generate Signed Bundle / APK**
   - Buat keystore baru atau gunakan existing
   - Simpan keystore dengan aman!

### Security Best Practices

- ❌ **JANGAN** commit `google-services.json` ke Git public
- ✅ Tambahkan ke `.gitignore`
- ✅ Gunakan environment variables untuk sensitive data
- ✅ Update Firestore rules untuk production
- ✅ Enable email verification

---

## 🎓 Tips Development

### Android Studio Shortcuts

- **Run App**: `Shift + F10`
- **Debug**: `Shift + F9`
- **Find**: `Ctrl + F` (Windows) / `Cmd + F` (Mac)
- **Search Everywhere**: `Double Shift`
- **Reformat Code**: `Ctrl + Alt + L` (Windows) / `Cmd + Option + L` (Mac)

### Debugging

1. Set **Breakpoint**: Klik di line number
2. **Run Debug**: `Shift + F9`
3. Lihat **Logcat** untuk logs dan errors
4. Filter Logcat by tag atau package name

### Git Workflow

```bash
# Clone project
git clone <repository-url>

# Create new branch
git checkout -b feature/new-feature

# Stage changes
git add .

# Commit
git commit -m "Add new feature"

# Push
git push origin feature/new-feature
```

---

## 📞 Support

Jika masih ada masalah atau pertanyaan:

1. Cek kembali semua langkah di panduan ini
2. Google error message yang muncul
3. Cek Stack Overflow
4. Buka issue di repository GitHub project

---

## ✨ Selamat!

Jika semua langkah sudah diikuti, aplikasi **Sedekah Yuk** sudah siap digunakan! 🎉

Happy Coding! 💻

---

**Last Updated**: 7 Januari 2026
