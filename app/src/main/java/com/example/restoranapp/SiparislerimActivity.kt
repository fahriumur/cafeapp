package com.example.restoranapp

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity

class SiparislerimActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_siparislerim)

        val siparislerimLayout = findViewById<LinearLayout>(R.id.siparislerim_layout)
        val btnSil = findViewById<Button>(R.id.btn_sil)
        val sharedPreferences = getSharedPreferences("Cart", Context.MODE_PRIVATE)

        val orders = sharedPreferences.getStringSet("Orders", mutableSetOf()) ?: mutableSetOf()
        for (order in orders) {
            val orderText = TextView(this)
            orderText.text = order
            orderText.textSize = 18f
            siparislerimLayout.addView(orderText)
        }

        btnSil.setOnClickListener {
            val editor = sharedPreferences.edit()
            editor.remove("Orders")
            editor.apply()
            siparislerimLayout.removeAllViews()
            Toast.makeText(this, "Geçmiş siparişler silindi", Toast.LENGTH_SHORT).show()
        }
    }
}
