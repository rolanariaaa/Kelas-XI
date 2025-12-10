package com.example.firebasetutorial2.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.firebasetutorial2.MainActivity
import com.example.firebasetutorial2.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() { // Mewarisi FirebaseMessagingService untuk menerima FCM

    override fun onMessageReceived(remoteMessage: RemoteMessage) { // Dipanggil saat pesan FCM masuk
        super.onMessageReceived(remoteMessage)

        // Cetak log sumber pesan
        Log.d(TAG, "FCM from: ${remoteMessage.from}")

        // Jika memiliki payload data kustom, cetak log datanya
        if (remoteMessage.data.isNotEmpty()) {
            Log.d(TAG, "FCM data payload: ${remoteMessage.data}")
        }

        // Ambil judul dan body dari notification payload atau dari data jika disediakan oleh server
        val title: String = remoteMessage.notification?.title
            ?: remoteMessage.data[KEY_TITLE]
            ?: DEFAULT_TITLE
        val body: String = remoteMessage.notification?.body
            ?: remoteMessage.data[KEY_BODY]
            ?: DEFAULT_BODY

        // Cetak log isi notifikasi
        Log.d(TAG, "FCM notification -> title: $title, body: $body")

        // Tampilkan notifikasi ke pengguna
        showNotification(title, body)
    }

    private fun showNotification(title: String, body: String) { // Membuat dan menampilkan notifikasi
        // Pastikan notification channel dibuat pada Android O+
        createNotificationChannelIfNeeded()

        // Intent ketika notifikasi diketuk akan membuka MainActivity
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or pendingIntentMutabilityFlag()
        )

        // Bangun notifikasi menggunakan NotificationCompat agar kompatibel ke belakang
        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.mipmap.ic_launcher) // Ikon kecil notifikasi
            .setContentTitle(title) // Judul notifikasi dari pesan
            .setContentText(body) // Isi notifikasi dari pesan
            .setAutoCancel(true) // Menutup notifikasi saat diketuk
            .setContentIntent(pendingIntent) // Intent saat diketuk
            .setPriority(NotificationCompat.PRIORITY_HIGH) // Prioritas tinggi agar segera tampil

        // Tampilkan notifikasi melalui NotificationManagerCompat
        with(NotificationManagerCompat.from(this)) {
            notify(NOTIFICATION_ID, builder.build())
        }
    }

    private fun createNotificationChannelIfNeeded() { // Membuat channel notifikasi untuk Android O+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelName = "General Notifications" // Nama channel ditampilkan ke pengguna
            val channel = NotificationChannel(
                CHANNEL_ID,
                channelName,
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Channel for FCM notifications" // Deskripsi channel
                enableLights(true) // Mengaktifkan lampu notifikasi
                lightColor = Color.BLUE // Warna lampu notifikasi
                enableVibration(true) // Mengaktifkan getar
            }
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel) // Daftarkan channel ke sistem
        }
    }

    private fun pendingIntentMutabilityFlag(): Int { // Flag kompatibilitas mutable/immutable untuk PendingIntent
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) PendingIntent.FLAG_IMMUTABLE else 0
    }

    companion object { // Konstanta yang digunakan di service
        private const val TAG = "MyFcmService" // Tag untuk logging
        private const val CHANNEL_ID = "fcm_default_channel" // ID channel notifikasi
        private const val NOTIFICATION_ID = 1001 // ID unik notifikasi
        private const val KEY_TITLE = "title" // Kunci judul di data payload
        private const val KEY_BODY = "body" // Kunci body di data payload
        private const val DEFAULT_TITLE = "New Message" // Judul default jika tidak ada
        private const val DEFAULT_BODY = "You have a new notification" // Body default jika tidak ada
    }
}