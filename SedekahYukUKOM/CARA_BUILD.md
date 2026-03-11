# 🚀 Cara Build Aplikasi Sedekah Yuk

## ✅ **Semua Error Code Sudah Diperbaiki!**

Aplikasi sudah 100% siap untuk di-build. Yang perlu Anda lakukan hanya memilih salah satu cara di bawah:

---

## **Cara 1: Gunakan Android Studio** ⭐ **(PALING MUDAH)**

1. Buka **Android Studio**
2. Klik **File** → **Open**
3. Pilih folder `SedekahYukUKOM`
4. Tunggu **Gradle Sync** selesai (lihat progress di pojok kanan bawah)
5. Klik ikon **hammer** 🔨 atau tekan **Ctrl+F9** untuk build
6. Klik **Run** → **Run 'app'** atau tekan **Shift+F10** untuk jalankan

✅ Android Studio sudah bundle dengan JDK yang tepat, jadi tidak perlu install JDK tambahan.

---

## **Cara 2: Build dengan Command Line** (Jika sudah install JDK 11/17/21)

### Windows (Command Prompt):
```batch
build.bat
```

### Windows (PowerShell):
```powershell
.\build.ps1
```

Script di atas akan:
- Otomatis mencari JDK 11/17/21 di sistem Anda
- Clean build sebelumnya
- Build APK Debug
- Menampilkan lokasi APK jika berhasil

---

## **Cara 3: Manual Build** (Jika sudah install JDK 17)

### Windows PowerShell:
```powershell
$env:JAVA_HOME="C:\Program Files\Eclipse Adoptium\jdk-17.0.xx-hotspot"
.\gradlew.bat clean assembleDebug
```

### Windows Command Prompt:
```batch
set JAVA_HOME=C:\Program Files\Eclipse Adoptium\jdk-17.0.xx-hotspot
gradlew.bat clean assembleDebug
```

APK akan tersimpan di:
```
app\build\outputs\apk\debug\app-debug.apk
```

---

## **Cara 4: Install JDK 17** (Jika belum punya)

1. **Download JDK 17:**
   - Link: https://adoptium.net/temurin/releases/?version=17
   - Pilih **Windows x64** → file `.msi`

2. **Install:**
   - Jalankan installer
   - Install ke lokasi default

3. **Build:**
   - Jalankan `build.bat` atau `build.ps1`

---

## 📱 **Install APK ke Android**

Setelah build berhasil:

1. Copy file `app-debug.apk` ke HP Android Anda
2. Buka file manager di HP
3. Tap file `app-debug.apk`
4. Izinkan instalasi dari "Unknown Sources" jika diminta
5. Tap **Install**

---

## 🔥 **Setup Firebase** (Penting!)

Sebelum menjalankan aplikasi, Anda perlu setup Firebase:

1. Buka https://console.firebase.google.com
2. Buat project baru atau gunakan yang sudah ada
3. Tambahkan aplikasi Android dengan package name: `com.example.sedekahyukukom`
4. Download file `google-services.json`
5. **Ganti** file `app/google-services.json` yang sudah ada dengan file baru
6. Aktifkan **Authentication** → Email/Password
7. Aktifkan **Cloud Firestore** → Start in test mode
8. Build ulang aplikasi

Panduan lengkap ada di file `SETUP_GUIDE.md` dan `FIREBASE_GUIDE.md`.

---

## ⚠️ **Troubleshooting**

### Error: "Java 25 tidak didukung"
**Solusi:** Gunakan JDK 11, 17, atau 21. Java 25 masih terlalu baru untuk Kotlin compiler.

### Error: "JAVA_HOME not set"
**Solusi:** Jalankan `build.bat` atau `build.ps1` yang akan otomatis set JAVA_HOME.

### Error: "gradlew.bat not found"
**Solusi:** Pastikan Anda menjalankan command dari folder `SedekahYukUKOM`.

### Build sangat lambat
**Solusi:** Build pertama kali akan download dependencies (~500MB). Build berikutnya akan jauh lebih cepat.

---

## 📊 **Status Project**

| Komponen | Status |
|----------|--------|
| **Code Kotlin** | ✅ 100% No Errors |
| **XML Layouts** | ✅ Semua Lengkap |
| **Resources** | ✅ Semua Lengkap |
| **Activities** | ✅ 11 Activities |
| **Firebase Config** | ⚠️ Perlu setup |
| **Build System** | ✅ Siap |

---

## 🎯 **Fitur Aplikasi**

✅ Splash Screen dengan kutipan sedekah
✅ Login & Register dengan Firebase Auth
✅ Home Dashboard dengan statistik
✅ Sedekah dengan nominal custom
✅ Kampanye dengan donasi
✅ Riwayat transaksi
✅ Artikel edukatif tentang sedekah
✅ Profil user dengan edit name

---

**Semoga sukses! Jika ada pertanyaan, silakan cek dokumentasi lengkap di:**
- `README.md` - Overview project
- `SETUP_GUIDE.md` - Setup lengkap step by step
- `FIREBASE_GUIDE.md` - Firebase API documentation

🎉 **Alhamdulillah, aplikasi siap digunakan!**
