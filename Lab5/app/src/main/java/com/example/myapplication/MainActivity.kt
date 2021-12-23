package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(),MyAdapter.OnListItemClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var addButton: FloatingActionButton
    private lateinit var adapter: MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.resView)
        addButton = findViewById(R.id.floatingActionButton)

        adapter= MyAdapter()
        adapter.setData(getIems())

        private fun getItems(): List<User>{
            return listOf(
                User("")
            )
        }

    }
}