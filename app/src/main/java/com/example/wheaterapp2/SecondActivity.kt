package com.example.wheaterapp2

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
       // Toast.makeText(this, "Next Activity", Toast.LENGTH_SHORT).show()
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = layoutManager;
        var adapter = TemperatureAdapter(this, Supplier.temperature)
        recyclerView.adapter = adapter

        add.setOnClickListener {

            val detail = secondzipcode.text
            val detail1 = secondcountryCode.text
            Supplier.temperature.add(Temperature(detail,detail1))
            adapter.notifyDataSetChanged()
            Toast.makeText(this,"msg was click",Toast.LENGTH_SHORT).show()
        }
        back.setOnClickListener {
            val intent  = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }
}