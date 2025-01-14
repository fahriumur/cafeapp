package com.example.restoranapp

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.activity.ComponentActivity
import android.widget.Toast

class YoneticiPaneliActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_yonetici_paneli)

        val siparisLayout = findViewById<LinearLayout>(R.id.siparis_layout)
        val sharedPreferences = getSharedPreferences("Orders", Context.MODE_PRIVATE)

        // Siparişleri alıyoruz
        val orders = sharedPreferences.all
        for (order in orders) {
            val orderDetails = order.value.toString().split("|")
            if (orderDetails.size < 6) continue // Eksik veri varsa atla

            val orderKey = order.key
            val currentStatus = orderDetails[5] // Durum en sonda

            // Siparişin görünümünü oluşturuyoruz
            val orderLayout = LinearLayout(this)
            orderLayout.orientation = LinearLayout.HORIZONTAL

            // Sipariş bilgilerini ekranda göstermek için TextView
            val orderText = TextView(this)
            orderText.text = "Sipariş ID: $orderKey - Durum: $currentStatus"
            orderText.layoutParams = LinearLayout.LayoutParams(
                0,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                1.0f
            )

            // Durum Spinner'ı (Dropdown) oluşturuyoruz
            val durumSpinner = Spinner(this)
            val durumlar = SiparisDurumu.values().map { it.name }
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, durumlar)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            durumSpinner.adapter = adapter

            // Mevcut durumu Spinner'da seçili yapıyoruz
            val currentIndex = durumlar.indexOf(currentStatus)
            if (currentIndex >= 0) {
                durumSpinner.setSelection(currentIndex)
            }

            // Güncelle butonunu oluşturuyoruz
            val btnGuncelle = Button(this)
            btnGuncelle.text = "Güncelle"
            btnGuncelle.setOnClickListener {
                // Yeni durumu spinner'dan alıyoruz
                val yeniDurum = SiparisDurumu.valueOf(durumSpinner.selectedItem.toString())

                // Sipariş detaylarını güncelliyoruz
                val updatedOrderDetails = orderDetails.dropLast(1) + yeniDurum.name
                updateSiparisDurumu(this, orderKey, updatedOrderDetails.joinToString("|"))

                // Eğer durum "İptal Edildi" veya "Teslim Edildi" ise, siparişi geçmiş siparişler listesine kaydediyoruz
                if (yeniDurum == SiparisDurumu.IPTAL_EDILDI || yeniDurum == SiparisDurumu.TESLIM_EDILDI) {
                    saveToGecmisSiparisler(this, orderKey, updatedOrderDetails.joinToString("|"))
                }

                // Güncel sipariş bilgisini ekranda gösteriyoruz
                orderText.text = "Sipariş ID: $orderKey - Durum: ${yeniDurum.name}"

                // Kullanıcıya mesaj gösteriyoruz
                Toast.makeText(
                    this,
                    "Sipariş durumu güncellendi: $orderKey -> ${yeniDurum.name}",
                    Toast.LENGTH_SHORT
                ).show()
            }

            // Sipariş bilgilerini ve butonları layout'a ekliyoruz
            orderLayout.addView(orderText)
            orderLayout.addView(durumSpinner)
            orderLayout.addView(btnGuncelle)

            // Sipariş layout'unu ana layout'a ekliyoruz
            siparisLayout.addView(orderLayout)
        }
    }

    // Sipariş durumunu SharedPreferences'da güncelleyen fonksiyon
    private fun updateSiparisDurumu(context: Context, orderKey: String, updatedOrderDetails: String) {
        val sharedPreferences = context.getSharedPreferences("Orders", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(orderKey, updatedOrderDetails) // Güncellenmiş sipariş bilgisini kaydediyoruz
        editor.apply()
    }

    // Siparişi geçmiş siparişler listesine kaydeden fonksiyon
    private fun saveToGecmisSiparisler(context: Context, orderKey: String, updatedOrderDetails: String) {
        val gecmisSiparisPreferences = context.getSharedPreferences("GecmisSiparisler", Context.MODE_PRIVATE)
        val editor = gecmisSiparisPreferences.edit()
        editor.putString(orderKey, updatedOrderDetails) // Geçmiş siparişi kaydediyoruz
        editor.apply()

        // Loglama
        Log.d("GecmisSiparisler", "Siparis eklendi - Key: $orderKey, Details: $updatedOrderDetails")

        // Debug amaçlı olarak SharedPreferences'ı okuyup loglayabiliriz
        val allOrders = gecmisSiparisPreferences.all
        allOrders.forEach { (key, value) ->
            Log.d("GecmisSiparisler", "Key: $key, Value: $value")
        }
    }
}
