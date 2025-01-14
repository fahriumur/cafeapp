package com.example.restoranapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.ComponentActivity

class SiparisDurumuActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_siparis_durumu)

        // Ana sayfaya dön butonu
        val btnAnaSayfayaDon = findViewById<Button>(R.id.btnAnaSayfayaDon)

        btnAnaSayfayaDon.setOnClickListener {
            // Ana sayfaya dönüş
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()  // Mevcut activity'yi kapat
        }


        val siparisLayout = findViewById<LinearLayout>(R.id.siparis_layout)
        val sharedPreferences = getSharedPreferences("Orders", Context.MODE_PRIVATE)
        val orders = sharedPreferences.all

        for ((key, value) in orders) {
            val orderDetails = value.toString().split("|")
            if (orderDetails.size < 6) continue // Eksik veri varsa atla

            val adSoyad = orderDetails[0]
            val telefon = orderDetails[1]
            val adres = orderDetails[2]
            val urunler = orderDetails[3] // Ürünler burada
            val toplamTutar = orderDetails[4]
            val currentStatus = orderDetails[5] // Durum en sonda

            // Ürünler kısmını ayıklamak
            val urunListesi = urunler.split("|").joinToString("\n") { it }

            // Sipariş içeriğini ve durumunu göstermek için TextView
            val siparisText = TextView(this)
            siparisText.text = "Ad Soyad: $adSoyad\nTelefon: $telefon\nAdres: $adres\nÜrünler:\n$urunListesi\nToplam Tutar: $toplamTutar\nDurum: $currentStatus"
            siparisText.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)

            // Durum güncelleme butonunu ekleyebilirsiniz (isteğe bağlı)
            val btnIptal = Button(this)
            btnIptal.text = "Siparişi İptal Et"
            btnIptal.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            btnIptal.setOnClickListener {
                val editor = sharedPreferences.edit()
                editor.remove(key)
                editor.apply()
                siparisLayout.removeView(siparisText)
                siparisLayout.removeView(btnIptal)
            }

            siparisLayout.addView(siparisText)
            siparisLayout.addView(btnIptal)
        }
    }
}
