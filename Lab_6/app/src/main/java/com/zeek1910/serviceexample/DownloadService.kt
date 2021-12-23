package com.zeek1910.serviceexample

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import java.util.concurrent.TimeUnit

class DownloadService : Service() {

    override fun onBind(intent: Intent?): IBinder? {
        TODO("Not yet implemented")
    }


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.let {
            val url = it.getStringExtra(MainActivity.KEY_URL)
            Log.d("Zeek", "onStartCommand: url -> $url")
        }
        Thread {
            var counter = 0
            while (true) {
                try {
                    sendBroadcast(Intent(MainActivity.ACTION).putExtra(KEY_DATA, counter))
                    createNotification("File $counter", counter)
                    TimeUnit.SECONDS.sleep(10)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
                counter++
            }
        }.start()
        return super.onStartCommand(intent, flags, startId)
    }

    private fun createNotification(title: String, id: Int) {
        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle(title)
            .setSmallIcon(R.drawable.ic_android)
            .setAutoCancel(true)
            .build()

        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "My Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(channel)
        }
        notificationManager.notify(id, notification)
    }

    companion object {
        private const val CHANNEL_ID = "CHANNEL_ID"
        const val KEY_DATA = "KEY_DATA"
    }
}