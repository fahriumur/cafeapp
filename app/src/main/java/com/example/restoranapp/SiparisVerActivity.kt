package com.example.restoranapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity

class SiparisVerActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_siparis_ver)

        val btnTatlilar = findViewById<Button>(R.id.btn_tatlilar)
        btnTatlilar.setOnClickListener {
            val intent = Intent(this, TatlilarActivity::class.java)
            startActivity(intent)
        }

        val btnSalatalar = findViewById<Button>(R.id.btn_salatalar)
        btnSalatalar.setOnClickListener {
            val intent = Intent(this, SalatalarActivity::class.java)
            startActivity(intent)
        }

        val btnAperatifler = findViewById<Button>(R.id.btn_aperatifler)
        btnAperatifler.setOnClickListener {
            val intent = Intent(this, AperatiflerActivity::class.java)
            startActivity(intent)
        }

        val btnAnaYemekler = findViewById<Button>(R.id.btn_ana_yemekler)
        btnAnaYemekler.setOnClickListener {
            val intent = Intent(this, AnaYemeklerActivity::class.java)
            startActivity(intent)
        }

        val btnIcecekler = findViewById<Button>(R.id.btn_icecekler)
        btnIcecekler.setOnClickListener {
            val intent = Intent(this, IceceklerActivity::class.java)
            startActivity(intent)
        }
    }
}
