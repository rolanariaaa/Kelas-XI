# Sedekah Yuk - Aplikasi Edukasi dan Simulasi Sedekah Digital

![Version](https://img.shields.io/badge/version-1.0-blue)
![Platform](https://img.shields.io/badge/platform-Android-green)
![Min SDK](https://img.shields.io/badge/Min%20SDK-26-orange)

## 📱 Deskripsi Aplikasi

**Sedekah Yuk** adalah aplikasi Android berbasis edukasi dan simulasi sedekah digital yang bertujuan menumbuhkan kepedulian sosial. Aplikasi ini menggunakan transaksi dummy/simulasi untuk keperluan pembelajaran dan tugas.

⚠️ **PENTING**: Seluruh transaksi dalam aplikasi ini bersifat SIMULASI. Tidak ada uang asli yang terlibat.

## 🎯 Fitur Utama

### 1. Splash Screen
- Logo aplikasi "Sedekah Yuk"
- Kutipan motivasi tentang sedekah secara acak
- Otomatis redirect ke halaman Login/Home

### 2. Authentication
- ✅ Login dengan Email & Password
- ✅ Register akun baru
- ✅ Validasi input
- ✅ Logout
- 🔥 Integrasi Firebase Authentication

### 3. Halaman Home
- Menampilkan saldo sedekah dummy
- Total sedekah yang telah dilakukan
- Menu grid dengan 5 menu utama:
  - Sedekah
  - Kampanye
  - Riwayat
  - Artikel
  - Profil

### 4. Fitur Sedekah (Simulasi)
- Pilihan nominal: Rp 5.000, Rp 10.000, Rp 20.000, Rp 50.000
- Input nominal custom
- Metode pembayaran dummy:
  - E-Wallet (GoPay, OVO, DANA, ShopeePay)
  - Transfer Bank (BCA, Mandiri, BRI)
- Konfirmasi dan notifikasi sukses

### 5. Kampanye Sedekah
- Daftar kampanye dengan kategori:
  - Bencana Alam
  - Anak Yatim
  - Pembangunan Masjid
  - Pendidikan
  - Fakir Miskin
- Detail kampanye dengan:
  - Gambar
  - Deskripsi lengkap
  - Target dana
  - Progress bar donasi
- Fitur donasi ke kampanye

### 6. Riwayat Sedekah
- Daftar lengkap riwayat transaksi
- Informasi detail:
  - Tanggal dan waktu
  - Nominal
  - Jenis kampanye
  - Metode pembayaran
  - Status: Berhasil (Simulasi)

### 7. Artikel & Edukasi
- Artikel statis tentang sedekah:
  - Keutamaan Sedekah dalam Islam
  - Hadis tentang Sedekah
  - Jenis-Jenis Sedekah
  - Adab dalam Bersedekah
  - Kisah Teladan tentang Sedekah
- Tampilan clean dan mudah dibaca
- Konten islami yang mendidik

### 8. Profil Pengguna
- Menampilkan informasi user:
  - Nama dan email
  - Total sedekah
  - Saldo dummy
- Edit profil (nama)
- Logout dengan konfirmasi

## 🛠️ Teknologi yang Digunakan

### Platform & Language
- **Platform**: Android
- **Language**: Kotlin
- **UI**: XML + Material Design 3
- **Min SDK**: API 26 (Android 8.0 Oreo)
- **Target SDK**: API 36

### Backend & Database
- **Firebase Authentication**: Untuk login dan register
- **Firebase Firestore**: Database untuk menyimpan data user dan transaksi
- **Firebase BOM**: v32.7.0

### Libraries
- **AndroidX Core**: v1.17.0
- **Material Design**: v1.11.0
- **ConstraintLayout**: v2.1.4
- **RecyclerView**: v1.3.2
- **CardView**: v1.0.0
- **Lifecycle & ViewModel**: v2.7.0
- **Navigation**: v2.7.6
- **Coroutines**: v1.7.3

### Architecture & Patterns
- **MVVM-like** structure
- **ViewBinding** untuk akses view yang type-safe
- **SharedPreferences** untuk session management
- **RecyclerView** dengan custom adapters

## 📂 Struktur Project

```
com.example.sedekahyukukom
│
├── adapter/
│   ├── ArtikelAdapter.kt
│   ├── KampanyeAdapter.kt
│   └── RiwayatAdapter.kt
│
├── model/
│   ├── Artikel.kt
│   ├── Kampanye.kt
│   ├── Riwayat.kt
│   └── User.kt
│
├── ui/
│   ├── splash/
│   │   └── SplashActivity.kt
│   ├── login/
│   │   └── LoginActivity.kt
│   ├── register/
│   │   └── RegisterActivity.kt
│   ├── home/
│   │   └── HomeActivity.kt
│   ├── sedekah/
│   │   └── SedekahActivity.kt
│   ├── kampanye/
│   │   ├── KampanyeActivity.kt
│   │   └── DetailKampanyeActivity.kt
│   ├── riwayat/
│   │   └── RiwayatActivity.kt
│   ├── artikel/
│   │   ├── ArtikelActivity.kt
│   │   └── DetailArtikelActivity.kt
│   └── profil/
│       └── ProfilActivity.kt
│
└── utils/
    ├── Constants.kt
    ├── FormatHelper.kt
    └── PreferenceManager.kt
```

## 🎨 Desain UI/UX

### Tema Warna
- **Primary**: Hijau Islami (#2E7D32)
- **Primary Dark**: #1B5E20
- **Primary Light**: #4CAF50
- **Accent**: #66BB6A
- **Background**: #F5F5F5
- **Text**: #212121 / #757575

### Design Principles
- ✅ Material Design 3
- ✅ Responsive untuk berbagai ukuran layar
- ✅ Clean dan minimalis
- ✅ User-friendly navigation
- ✅ Consistent color scheme

## 🚀 Cara Setup & Instalasi

### Prerequisite
1. Android Studio (Latest Version)
2. JDK 11 atau lebih tinggi
3. Android SDK API 26+
4. Akun Firebase (untuk setup backend)

### Langkah-langkah Setup

#### 1. Clone Project
```bash
git clone <repository-url>
cd SedekahYukUKOM
```

#### 2. Setup Firebase

a. **Buat Project di Firebase Console**
   - Kunjungi [Firebase Console](https://console.firebase.google.com/)
   - Buat project baru dengan nama "SedekahYuk"

b. **Tambahkan Android App**
   - Package name: `com.example.sedekahyukukom`
   - Download file `google-services.json`
   - Letakkan di folder `app/`

c. **Enable Firebase Authentication**
   - Di Firebase Console, aktifkan Authentication
   - Enable metode "Email/Password"

d. **Setup Firestore Database**
   - Di Firebase Console, buat Firestore Database
   - Pilih mode "Start in test mode" (untuk development)
   - Struktur database akan otomatis terbuat saat aplikasi dijalankan

#### 3. Sync Gradle
```bash
./gradlew build
```

#### 4. Run Aplikasi
- Buka di Android Studio
- Pilih emulator atau device fisik
- Klik "Run" (Shift + F10)

## 📊 Struktur Database Firestore

### Collection: `users`
```javascript
{
  uid: String,
  name: String,
  email: String,
  totalSedekah: Number,
  saldoDummy: Number
}
```

### Collection: `riwayat`
```javascript
{
  id: String,
  userId: String,
  kampanyeId: String,
  kampanyeJudul: String,
  nominal: Number,
  metodePembayaran: String,
  tanggal: Timestamp,
  status: String
}
```

## 🔒 Security & Privacy

- ⚠️ Ini adalah aplikasi SIMULASI untuk keperluan edukasi
- 🔐 Password dienkripsi oleh Firebase Authentication
- 📱 Data lokal disimpan dengan SharedPreferences
- 🌐 Semua komunikasi dengan Firebase menggunakan HTTPS
- ❌ TIDAK ADA transaksi uang asli

## ⚙️ Konfigurasi Tambahan

### Firestore Rules (Development)
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

### Firebase Authentication Rules
- Email/Password authentication enabled
- Tidak ada verifikasi email (untuk kemudahan demo)

## 🧪 Testing

### Manual Testing
1. Register dengan email dan password baru
2. Login dengan kredensial yang dibuat
3. Test semua fitur menu:
   - Lakukan sedekah
   - Lihat kampanye dan donasi
   - Check riwayat transaksi
   - Baca artikel
   - Edit profil
   - Logout

### Test Account (Optional)
```
Email: test@sedekahyuk.com
Password: test123
```

## 📝 Catatan Penting

### Untuk Pengembangan Lebih Lanjut
1. ✅ Ganti `google-services.json` dengan file dari project Firebase Anda sendiri
2. ✅ Update Firebase Firestore Rules untuk production
3. ✅ Tambahkan email verification untuk security
4. ✅ Implementasi proper error handling
5. ✅ Tambahkan loading states di semua network calls
6. ✅ Implementasi offline mode dengan Room Database
7. ✅ Tambahkan unit tests dan UI tests

### Known Limitations
- Ini adalah aplikasi SIMULASI, bukan payment gateway real
- Data kampanye adalah data dummy/statis
- Tidak ada integrasi dengan payment gateway asli
- Artikel bersifat statis (tidak dari backend)

## 📜 License

Project ini dibuat untuk keperluan edukasi dan tugas sekolah/kuliah.

## 👨‍💻 Developer

Aplikasi ini dikembangkan sebagai tugas pembelajaran Android Development dengan teknologi:
- Kotlin
- Firebase
- Material Design
- XML Layouts

## 🤝 Kontribusi

Project ini adalah project pembelajaran. Silakan fork dan modifikasi sesuai kebutuhan Anda.

## 📞 Support

Jika ada pertanyaan atau issues, silakan buat issue di repository ini.

---

**Disclaimer**: Aplikasi ini adalah SIMULASI untuk keperluan edukasi. TIDAK ADA transaksi uang asli yang terjadi. Semua data dan transaksi bersifat dummy/simulasi.

---

### 📱 Screenshots

(Tambahkan screenshots aplikasi Anda di sini setelah aplikasi jadi)

---

**Dibuat dengan ❤️ untuk pembelajaran Android Development**
