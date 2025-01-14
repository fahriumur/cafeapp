package com.example.restoranapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity

class SiparisOzetiActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_siparis_ozeti)

        val sepetLayout = findViewById<LinearLayout>(R.id.sepet_layout)
        val txtToplamTutar = findViewById<TextView>(R.id.txt_toplam_tutar)
        val edtAdSoyad = findViewById<EditText>(R.id.edt_ad_soyad)
        val edtTelefon = findViewById<EditText>(R.id.edt_telefon)
        val edtAdres = findViewById<EditText>(R.id.edt_adres)
        val btnSiparisiOnayla = findViewById<Button>(R.id.btn_siparisi_onayla)

        val sharedPreferences = getSharedPreferences("Cart", Context.MODE_PRIVATE)
        val cart = sharedPreferences.all

        // Toplam fiyatı almak için getDoubleExtra kullanmalıyız
        val toplamTutar = intent.getDoubleExtra("totalPrice", 0.0)
        txtToplamTutar.text = "Toplam Tutar: $toplamTutar TL"

        val urunListesi = mutableListOf<String>()

        // Sepet verilerini işlemek
        for ((key, value) in cart) {
            val itemCount = value.toString().toIntOrNull() ?: 0
            if (itemCount > 0) {
                // Ürün adı ve fiyat bilgisi için örnek: "Dolma - Fiyat: 180"
                val productDetails = key.split(" - Fiyat: ")
                if (productDetails.size < 2) continue

                val productName = productDetails[0]
                val productPrice = productDetails[1].toDoubleOrNull() ?: 0.0
                urunListesi.add("$productName - Miktar: $itemCount - Fiyat: ${productPrice * itemCount} TL")

                // Ürünleri sepette göstermek
                val productText = TextView(this)
                productText.text = urunListesi.last()
                sepetLayout.addView(productText)
            }
        }

        btnSiparisiOnayla.setOnClickListener {
            val adSoyad = edtAdSoyad.text.toString()
            val telefon = edtTelefon.text.toString()
            val adres = edtAdres.text.toString()

            if (adSoyad.isNotEmpty() && telefon.isNotEmpty() && adres.isNotEmpty()) {
                val ordersPreferences = getSharedPreferences("Orders", Context.MODE_PRIVATE)
                val editor = ordersPreferences.edit()
                val siparisKey = "siparis_${System.currentTimeMillis()}"

                // Sepetteki ürünleri birleştiriyoruz
                val urunListesiStr = urunListesi.joinToString("|")

                // Sipariş bilgilerini kaydediyoruz
                val siparisDurumu = "Onaylandı"  // Başlangıç durumu
                val siparisBilgisi = "$adSoyad|$telefon|$adres|$urunListesiStr|$toplamTutar|$siparisDurumu"
                editor.putString(siparisKey, siparisBilgisi)
                editor.apply()

                // Sepeti temizliyoruz
                val cartEditor = sharedPreferences.edit()
                cartEditor.clear()
                cartEditor.apply()

                Toast.makeText(this, "Siparişiniz Onaylandı!", Toast.LENGTH_SHORT).show()

                // Sipariş durumu sayfasına yönlendirme
                val intent = Intent(this, SiparisDurumuActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Lütfen tüm bilgileri doldurun!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
