package com.example.restoranapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Sipariş Ver Butonu
        val btnOrder = findViewById<Button>(R.id.btn_order)
        btnOrder.setOnClickListener {
            val intent = Intent(this, SiparisVerActivity::class.java)
            startActivity(intent)
        }

        // Sepetim Butonu
        val btnSepetim = findViewById<Button>(R.id.btn_sepetim)
        btnSepetim.setOnClickListener {
            val intent = Intent(this, SepetActivity::class.java)
            startActivity(intent)
        }

        // Sipariş Durumları Butonu
        val btnSiparisDurumu = findViewById<Button>(R.id.btn_siparis_durumu)
        btnSiparisDurumu.setOnClickListener {
            val intent = Intent(this, SiparisDurumuActivity::class.java)
            startActivity(intent)
        }

        // Yönetici Paneli Butonu
        val btnYoneticiPaneli = findViewById<Button>(R.id.btn_yonetici_paneli)
        btnYoneticiPaneli.setOnClickListener {
            val intent = Intent(this, AdminLoginActivity::class.java) // Yönetici Giriş Aktivitesine yönlendiriyor
            startActivity(intent)
        }
    }
}
