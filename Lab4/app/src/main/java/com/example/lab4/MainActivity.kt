package com.example.lab4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import kotlinx.coroutines.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private lateinit var button: Button
    private lateinit var button2: Button
    private lateinit var progressBar: ProgressBar
    private lateinit var textView: TextView
    private var isProgress = true
    private var isProgressWithCoroutine = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressBar = findViewById(R.id.progressBar)
        textView = findViewById(R.id.textView2)
        button = findViewById(R.id.button)
        button2 = findViewById(R.id.button2)
        button2.setOnClickListener{
            CoroutineScope(Dispatchers.Default).launch {
                progressWithCoroutine()
            }
        }
        button.setOnClickListener{
            Thread { progress() }.start()
        }

    }
    private fun progress() {
        var progressStatus = 0
        while (isProgress) {
            TimeUnit.MILLISECONDS.sleep(500)
            progressStatus+=1
            runOnUiThread {
                progressBar.progress = progressStatus
                textView.text= progressStatus.toString()
            }
        }
    }
    private suspend fun progressWithCoroutine(){
        var progress:Double = 0.0
        while (isProgressWithCoroutine) {
            delay(500)
            progress+=0.5
            withContext(Dispatchers.Main){
                button2.text = progress.toString()
            }
        }
    }
}