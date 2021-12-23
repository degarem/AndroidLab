package com.example.lab1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast


class MainActivity : AppCompatActivity() {

    private lateinit var enterLong: EditText
    private lateinit var enterString: EditText
    private lateinit var textViewLong: TextView
    private lateinit var textViewString: TextView
    private lateinit var saveLong: Button
    private lateinit var saveString: Button
    private lateinit var restoreLong: Button
    private lateinit var restoreString: Button
    private lateinit var clearStorage: Button

    private lateinit var appSettings: AppSettings

    private val saveLongOnClick = View.OnClickListener {
        val textLong = enterLong.text.toString()
        textViewLong.text = textLong
        var long:Long? = null
        try {
            long = textLong.toLong()
        } catch (e: Exception) {
            Toast.makeText(this,"Error! Use Number instead of String",Toast.LENGTH_SHORT).show()
        }
        appSettings.saveLong(long)
    }

    private val saveStringOnClick = View.OnClickListener {
        val textString = enterString.text.toString()
        textViewString.text = textString
        appSettings.saveString(textString)
    }

    private val restoreLongOnClick = View.OnClickListener {
        textViewLong.text = appSettings.restoreLong().toString()
    }

    private val restoreStringOnClick = View.OnClickListener {
        textViewString.text = appSettings.restoreString()
    }

    private val clearStorageOnClick = View.OnClickListener {
        appSettings.clearStorage()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        enterLong = findViewById(R.id.enterLong)
        enterString = findViewById(R.id.enterString)
        textViewLong = findViewById(R.id.textViewLong)
        textViewString = findViewById(R.id.textViewString)
        saveLong = findViewById(R.id.saveLong)
        saveString = findViewById(R.id.saveString)
        restoreLong = findViewById(R.id.restoreLong)
        restoreString = findViewById(R.id.restoreString)
        clearStorage = findViewById(R.id.clearStorage)

        saveLong.setOnClickListener(saveLongOnClick)
        saveString.setOnClickListener(saveStringOnClick)
        restoreLong.setOnClickListener(restoreLongOnClick)
        restoreString.setOnClickListener(restoreStringOnClick)
        clearStorage.setOnClickListener(clearStorageOnClick)

        appSettings = AppSettings(this)
    }
}