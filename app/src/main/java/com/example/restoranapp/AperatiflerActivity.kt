package com.example.restoranapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.ComponentActivity

class AperatiflerActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aperatifler)

        val btnAddSigaraBoregi = findViewById<Button>(R.id.btn_add_sigara_boregi)
        val btnAddPatatesKizartmasi = findViewById<Button>(R.id.btn_add_patates_kizartmasi)
        val btnAddSoganHalkasi = findViewById<Button>(R.id.btn_add_sogan_halkasi)
        val btnAddMozzarellaSticks = findViewById<Button>(R.id.btn_add_mozzarella_sticks)
        val btnAddNuggets = findViewById<Button>(R.id.btn_add_nuggets)
        val btnAddTavukKanat = findViewById<Button>(R.id.btn_add_tavuk_kanat)
        val btnAddHummus = findViewById<Button>(R.id.btn_add_hummus)
        val btnAddHaydari = findViewById<Button>(R.id.btn_add_haydari)
        val btnAddAciliEzme = findViewById<Button>(R.id.btn_add_acili_ezme)
        val btnAddMuhammara = findViewById<Button>(R.id.btn_add_muhammara)
        val btnAddSaksuka = findViewById<Button>(R.id.btn_add_saksuka)
        val btnAddCacik = findViewById<Button>(R.id.btn_add_cacik)
        val btnSepetim = findViewById<Button>(R.id.btn_sepetim) // Sepetim butonunu buluyoruz


        btnAddHummus.setOnClickListener {
            addToCart("Hummus", 50)
        }

        btnAddHaydari.setOnClickListener {
            addToCart("Haydari", 45)
        }

        btnAddAciliEzme.setOnClickListener {
            addToCart("Acılı Ezme", 40)
        }

        btnAddMuhammara.setOnClickListener {
            addToCart("Muhammara", 55)
        }

        btnAddSaksuka.setOnClickListener {
            addToCart("Şakşuka", 50)
        }

        btnAddCacik.setOnClickListener {
            addToCart("Cacık", 45)
        }

        btnAddSigaraBoregi.setOnClickListener {
            addToCart("Sigara Böreği", 50)
        }

        btnAddPatatesKizartmasi.setOnClickListener {
            addToCart("Patates Kızartması", 45)
        }

        btnAddSoganHalkasi.setOnClickListener {
            addToCart("Soğan Halkası", 40)
        }

        btnAddMozzarellaSticks.setOnClickListener {
            addToCart("Mozzarella Sticks", 55)
        }

        btnAddNuggets.setOnClickListener {
            addToCart("Nuggets", 60)
        }

        btnAddTavukKanat.setOnClickListener {
            addToCart("Tavuk Kanat", 70)
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
