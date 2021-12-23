package com.example.lab2application

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.LinearLayout
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {

    private lateinit var linearLayout: LinearLayout
    private lateinit var tabLayout: TabLayout
    private lateinit var frameLayout: FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        linearLayout= findViewById(R.id.linearLayout)
        tabLayout= findViewById(R.id.tabLayout)
        frameLayout= findViewById(R.id.frameLayout)
        supportFragmentManager.beginTransaction().replace(R.id.frameLayout, ListFragment()).commit()
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab?.text==("List")){
                    supportFragmentManager.beginTransaction().replace(R.id.frameLayout, ListFragment()).commit()
                }
                if (tab?.text==("Mail")){
                    supportFragmentManager.beginTransaction().replace(R.id.frameLayout, MailFragment()).commit()
                }
                if (tab?.text==("Settings")){
                    supportFragmentManager.beginTransaction().replace(R.id.frameLayout, SettingsFragment()).commit()
                }

            }

        })
    }
}