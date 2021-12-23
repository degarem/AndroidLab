package com.zeek1910.serviceexample

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.core.widget.ContentLoadingProgressBar
import android.os.Handler
import android.view.View

import android.widget.ProgressBar

import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity() {
    private lateinit var buttonDownload: Button
    private lateinit var adapter: Adapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter=Adapter()

        buttonDownload = findViewById(R.id.button)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager( this, RecyclerView.VERTICAL, false)

        val broadCastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                intent?.let {
                    val data = it.getIntExtra(DownloadService.KEY_DATA, 0)
                    Log.d("Zeek", "Received -> $data")
                    if (data>=0){
                        adapter.item.add(DataInfo("some name", "20.12.2021", "13:00:00"))
                        recyclerView.adapter = adapter
                    }
                }
            }
        }
        registerReceiver(broadCastReceiver, IntentFilter(ACTION))
    }

    override fun onStart() {
        super.onStart()
        buttonDownload.setOnClickListener {
            val urlString = "https://example.com/file.pdf"
            startService(Intent(this, DownloadService::class.java).apply {
                putExtra(
                    KEY_URL,
                    urlString
                )
            })
        }
    }

    companion object {
        const val ACTION = "action"
        const val KEY_URL = "KEY_URL"
    }

}