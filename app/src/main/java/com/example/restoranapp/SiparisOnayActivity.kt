package com.example.restoranapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity

class SiparisOnayActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_siparis_onay)

        val edtAdSoyad = findViewById<EditText>(R.id.edt_ad_soyad)
        val edtTelefon = findViewById<EditText>(R.id.edt_telefon)
        val edtAdres = findViewById<EditText>(R.id.edt_adres)
        val btnOnayla = findViewById<Button>(R.id.btn_onayla)

        btnOnayla.setOnClickListener {
            val adSoyad = edtAdSoyad.text.toString()
            val telefon = edtTelefon.text.toString()
            val adres = edtAdres.text.toString()

            if (adSoyad.isNotEmpty() && telefon.isNotEmpty() && adres.isNotEmpty()) {
                val sharedPreferences = getSharedPreferences("Orders", Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.putString("AdSoyad", adSoyad)
                editor.putString("Telefon", telefon)
                editor.putString("Adres", adres)
                editor.apply()

                // Sipariş onaylandı mesajı
                Toast.makeText(this, "Siparişiniz Onaylandı!", Toast.LENGTH_SHORT).show()

                // Sipariş durumu aktivitesine geçiş
                val intent = Intent(this, SiparisDurumuActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Lütfen tüm bilgileri doldurun!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
