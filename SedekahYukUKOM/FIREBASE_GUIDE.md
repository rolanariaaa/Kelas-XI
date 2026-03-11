# 🔥 Firebase Configuration & Usage Documentation

## Firebase Services yang Digunakan

Aplikasi **Sedekah Yuk** menggunakan 2 service utama dari Firebase:

1. **Firebase Authentication** - Untuk autentikasi user
2. **Cloud Firestore** - Untuk database

---

## 🔐 Firebase Authentication

### Setup
```kotlin
// Inisialisasi Firebase Auth
private lateinit var auth: FirebaseAuth
auth = FirebaseAuth.getInstance()
```

### Register User
```kotlin
auth.createUserWithEmailAndPassword(email, password)
    .addOnCompleteListener(this) { task ->
        if (task.isSuccessful) {
            val user = auth.currentUser
            // Success - save user data to Firestore
        } else {
            // Handle error
        }
    }
```

### Login User
```kotlin
auth.signInWithEmailAndPassword(email, password)
    .addOnCompleteListener(this) { task ->
        if (task.isSuccessful) {
            val user = auth.currentUser
            // Success - navigate to home
        } else {
            // Handle error
        }
    }
```

### Get Current User
```kotlin
val currentUser = auth.currentUser
val userId = currentUser?.uid
val email = currentUser?.email
```

### Logout
```kotlin
auth.signOut()
```

### Check Login Status
```kotlin
val isLoggedIn = auth.currentUser != null
```

---

## 💾 Cloud Firestore

### Setup
```kotlin
// Inisialisasi Firestore
private lateinit var firestore: FirebaseFirestore
firestore = FirebaseFirestore.getInstance()
```

### Database Structure

#### Collection: `users`
```kotlin
data class User(
    val uid: String = "",
    val name: String = "",
    val email: String = "",
    val totalSedekah: Long = 0,
    val saldoDummy: Long = 1000000
)
```

**Document ID**: User UID dari Firebase Auth

**Example Document**:
```json
{
  "uid": "ABC123XYZ",
  "name": "John Doe",
  "email": "john@example.com",
  "totalSedekah": 50000,
  "saldoDummy": 1000000
}
```

#### Collection: `riwayat`
```kotlin
data class Riwayat(
    val id: String = "",
    val userId: String = "",
    val kampanyeId: String = "",
    val kampanyeJudul: String = "",
    val nominal: Long = 0,
    val metodePembayaran: String = "",
    val tanggal: Timestamp = Timestamp.now(),
    val status: String = "Berhasil (Simulasi)"
)
```

**Document ID**: Auto-generated

**Example Document**:
```json
{
  "id": "riwayat_001",
  "userId": "ABC123XYZ",
  "kampanyeId": "kampanye_001",
  "kampanyeJudul": "Bantuan Bencana Alam",
  "nominal": 10000,
  "metodePembayaran": "GoPay (Simulasi)",
  "tanggal": "2024-01-07T10:30:00Z",
  "status": "Berhasil (Simulasi)"
}
```

---

## 📝 CRUD Operations

### CREATE - Add Document

#### Cara 1: Set dengan custom ID
```kotlin
val user = User(
    uid = userId,
    name = name,
    email = email
)

firestore.collection("users")
    .document(userId)
    .set(user)
    .addOnSuccessListener {
        // Success
    }
    .addOnFailureListener { e ->
        // Error
    }
```

#### Cara 2: Add dengan auto-generated ID
```kotlin
val riwayat = Riwayat(
    userId = userId,
    kampanyeJudul = "Sedekah Umum",
    nominal = 10000
)

firestore.collection("riwayat")
    .add(riwayat)
    .addOnSuccessListener { documentReference ->
        val docId = documentReference.id
        // Success
    }
    .addOnFailureListener { e ->
        // Error
    }
```

### READ - Get Document

#### Get Single Document
```kotlin
firestore.collection("users")
    .document(userId)
    .get()
    .addOnSuccessListener { document ->
        if (document.exists()) {
            val user = document.toObject(User::class.java)
            // Use user data
        }
    }
    .addOnFailureListener { e ->
        // Error
    }
```

#### Get All Documents
```kotlin
firestore.collection("riwayat")
    .get()
    .addOnSuccessListener { documents ->
        for (document in documents) {
            val riwayat = document.toObject(Riwayat::class.java)
            // Process each riwayat
        }
    }
    .addOnFailureListener { e ->
        // Error
    }
```

#### Query with Filter
```kotlin
firestore.collection("riwayat")
    .whereEqualTo("userId", currentUserId)
    .get()
    .addOnSuccessListener { documents ->
        // Process results
    }
```

#### Query with Order
```kotlin
firestore.collection("riwayat")
    .whereEqualTo("userId", currentUserId)
    .orderBy("tanggal", Query.Direction.DESCENDING)
    .get()
    .addOnSuccessListener { documents ->
        // Results sorted by date (newest first)
    }
```

#### Query with Limit
```kotlin
firestore.collection("riwayat")
    .orderBy("tanggal", Query.Direction.DESCENDING)
    .limit(10)
    .get()
    .addOnSuccessListener { documents ->
        // Get only 10 latest documents
    }
```

### UPDATE - Update Document

#### Update Specific Field
```kotlin
firestore.collection("users")
    .document(userId)
    .update("name", newName)
    .addOnSuccessListener {
        // Success
    }
    .addOnFailureListener { e ->
        // Error
    }
```

#### Update Multiple Fields
```kotlin
val updates = hashMapOf<String, Any>(
    "name" to newName,
    "totalSedekah" to newTotal
)

firestore.collection("users")
    .document(userId)
    .update(updates)
    .addOnSuccessListener {
        // Success
    }
```

#### Increment Value
```kotlin
firestore.collection("users")
    .document(userId)
    .update("totalSedekah", FieldValue.increment(10000))
    .addOnSuccessListener {
        // totalSedekah increased by 10000
    }
```

### DELETE - Delete Document

```kotlin
firestore.collection("riwayat")
    .document(riwayatId)
    .delete()
    .addOnSuccessListener {
        // Deleted successfully
    }
    .addOnFailureListener { e ->
        // Error
    }
```

---

## 🔍 Advanced Queries

### Complex Query
```kotlin
firestore.collection("riwayat")
    .whereEqualTo("userId", currentUserId)
    .whereGreaterThan("nominal", 10000)
    .orderBy("nominal", Query.Direction.DESCENDING)
    .limit(5)
    .get()
    .addOnSuccessListener { documents ->
        // Get top 5 donations above 10000
    }
```

### Realtime Listener
```kotlin
firestore.collection("users")
    .document(userId)
    .addSnapshotListener { snapshot, error ->
        if (error != null) {
            return@addSnapshotListener
        }
        
        if (snapshot != null && snapshot.exists()) {
            val user = snapshot.toObject(User::class.java)
            // Update UI with realtime data
        }
    }
```

---

## 🛡️ Firestore Security Rules

### Development Mode (Test Mode)
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
- Semua user yang login bisa read & write semua data
- ⚠️ **HANYA untuk development/testing**

### Production Mode (Recommended)
```javascript
rules_version = '2';
service cloud.firestore {
  match /databases/{database}/documents {
    // Users collection
    match /users/{userId} {
      allow read: if request.auth != null;
      allow write: if request.auth != null && request.auth.uid == userId;
    }
    
    // Riwayat collection
    match /riwayat/{riwayatId} {
      allow read: if request.auth != null;
      allow create: if request.auth != null && 
                       request.resource.data.userId == request.auth.uid;
      allow update, delete: if request.auth != null && 
                               resource.data.userId == request.auth.uid;
    }
  }
}
```

**Penjelasan**:
- User hanya bisa edit data mereka sendiri
- User hanya bisa create/update/delete riwayat mereka sendiri
- Semua user bisa read semua data (optional, bisa dibatasi)

---

## 📊 Indexes

Untuk query yang complex, Firestore mungkin memerlukan index.

### Cara Buat Index:

1. **Otomatis**: Saat error muncul, Firestore akan kasih link untuk buat index
2. **Manual**: Di Firebase Console > Firestore > Indexes

### Contoh Index yang Diperlukan:

**Collection**: `riwayat`
- **Fields**:
  - `userId` - Ascending
  - `tanggal` - Descending

---

## 🔄 Data Sync & Offline Mode

Firestore otomatis support offline mode:

```kotlin
// Enable offline persistence (default: enabled)
val settings = FirebaseFirestoreSettings.Builder()
    .setPersistenceEnabled(true)
    .build()

firestore.firestoreSettings = settings
```

**Keuntungan**:
- Data ter-cache secara lokal
- App tetap bisa baca data offline
- Write operations akan di-queue dan sync saat online

---

## ⚡ Best Practices

### 1. Batch Operations
Jika perlu write multiple documents:

```kotlin
val batch = firestore.batch()

val userRef = firestore.collection("users").document(userId)
batch.update(userRef, "totalSedekah", newTotal)

val riwayatRef = firestore.collection("riwayat").document()
batch.set(riwayatRef, riwayat)

batch.commit()
    .addOnSuccessListener {
        // All operations successful
    }
```

### 2. Transaction
Untuk operasi yang memerlukan atomic update:

```kotlin
firestore.runTransaction { transaction ->
    val userRef = firestore.collection("users").document(userId)
    val snapshot = transaction.get(userRef)
    
    val currentTotal = snapshot.getLong("totalSedekah") ?: 0
    val newTotal = currentTotal + nominal
    
    transaction.update(userRef, "totalSedekah", newTotal)
    
    newTotal
}.addOnSuccessListener { newTotal ->
    // Transaction success
}
```

### 3. Pagination
Untuk list yang panjang:

```kotlin
var lastVisible: DocumentSnapshot? = null

fun loadMore() {
    var query = firestore.collection("riwayat")
        .orderBy("tanggal", Query.Direction.DESCENDING)
        .limit(20)
    
    lastVisible?.let {
        query = query.startAfter(it)
    }
    
    query.get().addOnSuccessListener { documents ->
        if (!documents.isEmpty) {
            lastVisible = documents.documents[documents.size() - 1]
            // Process results
        }
    }
}
```

### 4. Error Handling
```kotlin
firestore.collection("users")
    .document(userId)
    .get()
    .addOnSuccessListener { document ->
        // Success
    }
    .addOnFailureListener { exception ->
        when (exception) {
            is FirebaseFirestoreException -> {
                when (exception.code) {
                    FirebaseFirestoreException.Code.PERMISSION_DENIED -> {
                        // Handle permission error
                    }
                    FirebaseFirestoreException.Code.UNAVAILABLE -> {
                        // Handle network error
                    }
                    else -> {
                        // Handle other errors
                    }
                }
            }
        }
    }
```

---

## 📈 Cost Optimization

### Tips untuk Hemat Biaya Firebase:

1. **Use Caching**: Enable persistence untuk reduce read operations
2. **Limit Results**: Gunakan `.limit()` untuk tidak load semua data
3. **Pagination**: Load data secara bertahap
4. **Avoid Reading Entire Documents**: Gunakan specific fields jika memungkinkan
5. **Delete Old Data**: Hapus data lama yang tidak diperlukan

### Free Tier Limits:
- **Reads**: 50,000/day
- **Writes**: 20,000/day
- **Deletes**: 20,000/day
- **Storage**: 1 GB

Cukup untuk development dan small-scale apps.

---

## 🧪 Testing Firebase Locally

### Using Firebase Emulator Suite:

```bash
# Install
npm install -g firebase-tools

# Login
firebase login

# Initialize
firebase init

# Start emulator
firebase emulators:start
```

Di app, connect ke emulator:
```kotlin
// Connect to emulator (only in debug build)
if (BuildConfig.DEBUG) {
    firestore.useEmulator("10.0.2.2", 8080) // For Android Emulator
    auth.useEmulator("10.0.2.2", 9099)
}
```

---

## 📞 Support & Documentation

- **Firebase Docs**: https://firebase.google.com/docs
- **Firestore Docs**: https://firebase.google.com/docs/firestore
- **Auth Docs**: https://firebase.google.com/docs/auth
- **Kotlin Extension**: https://firebase.google.com/docs/android/kotlin-extensions

---

**Last Updated**: 7 Januari 2026
