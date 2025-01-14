package com.example.restoranapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.ComponentActivity

class SalatalarActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_salatalar)

        val btnAddCaesarSalata = findViewById<Button>(R.id.btn_add_caesar_salata)
        val btnAddCobanSalata = findViewById<Button>(R.id.btn_add_coban_salata)
        val btnAddGreekSalata = findViewById<Button>(R.id.btn_add_greek_salata)
        val btnAddRokaSalata = findViewById<Button>(R.id.btn_add_roka_salata)
        val btnAddYogurtluHavucSalatasi = findViewById<Button>(R.id.btn_add_yogurtlu_havuc_salatasi)
        val btnAddPatatesSalatasi = findViewById<Button>(R.id.btn_add_patates_salatasi)
        val btnAddRusSalatasi = findViewById<Button>(R.id.btn_add_rus_salatasi)
        val btnAddYumurtaliSalata = findViewById<Button>(R.id.btn_add_yumurtali_salata)
        val btnAddTonBalikliSalata = findViewById<Button>(R.id.btn_add_ton_balikli_salata)
        val btnAddMercimekliSalata = findViewById<Button>(R.id.btn_add_mercimekli_salata)
        val btnSepetim = findViewById<Button>(R.id.btn_sepetim) // Sepetim butonunu buluyoruz


        btnAddCaesarSalata.setOnClickListener {
            addToCart("Caesar Salata", 150)
        }

        btnAddCobanSalata.setOnClickListener {
            addToCart("Çoban Salata", 120)
        }

        btnAddGreekSalata.setOnClickListener {
            addToCart("Greek Salata", 140)
        }

        btnAddRokaSalata.setOnClickListener {
            addToCart("Roka Salata", 130)
        }

        btnAddYogurtluHavucSalatasi.setOnClickListener {
            addToCart("Yoğurtlu Havuç Salatası", 130)
        }

        btnAddPatatesSalatasi.setOnClickListener {
            addToCart("Patates Salatası", 120)
        }

        btnAddRusSalatasi.setOnClickListener {
            addToCart("Rus Salatası", 140)
        }

        btnAddYumurtaliSalata.setOnClickListener {
            addToCart("Yumurtalı Salata", 135)
        }

        btnAddTonBalikliSalata.setOnClickListener {
            addToCart("Ton Balıklı Salata", 160)
        }

        btnAddMercimekliSalata.setOnClickListener {
            addToCart("Mercimekli Salata", 140)
        }

        btnSepetim.setOnClickListener {
            val intent = Intent(this, SepetActivity::class.java) // SepetActivity'ye yönlendirme
            startActivity(intent)
        }
    }

    private fun addToCart(itemName: String, itemPrice: Int) {
        val sharedPreferences = getSharedPreferences("Cart", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val itemCount = sharedPreferences.getInt(itemName, 0)

        editor.putInt(itemName, itemCount + 1)
        editor.apply()

        // Kullanıcıya bir mesaj göster
        Toast.makeText(this, "$itemName sepete eklendi!", Toast.LENGTH_SHORT).show()
    }
}
