package com.example.restoranapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.ComponentActivity

class IceceklerActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_icecekler2)

        val btnAddCola = findViewById<Button>(R.id.btn_add_cola)
        val btnAddFanta = findViewById<Button>(R.id.btn_add_fanta)
        val btnAddSprite = findViewById<Button>(R.id.btn_add_sprite)
        val btnAddSoda = findViewById<Button>(R.id.btn_add_soda)
        val btnAddAyran = findViewById<Button>(R.id.btn_add_ayran)
        val btnAddCay = findViewById<Button>(R.id.btn_add_cay)
        val btnAddSogukCay = findViewById<Button>(R.id.btn_add_soguk_cay)
        val btnAddPortakalSuyu = findViewById<Button>(R.id.btn_add_portakal_suyu)
        val btnAddLimonata = findViewById<Button>(R.id.btn_add_limonata)
        val btnAddTurkKahvesi = findViewById<Button>(R.id.btn_add_turk_kahvesi)
        val btnAddLatte = findViewById<Button>(R.id.btn_add_latte)
        val btnAddEspresso = findViewById<Button>(R.id.btn_add_espresso)
        val btnAddCappuccino = findViewById<Button>(R.id.btn_add_cappuccino)
        val btnAddElmaSuyu = findViewById<Button>(R.id.btn_add_elma_suyu)
        val btnSepetim = findViewById<Button>(R.id.btn_sepetim) // Sepetim butonunu buluyoruz


        btnAddCola.setOnClickListener {
            addToCart("Cola", 60)
        }

        btnAddFanta.setOnClickListener {
            addToCart("Fanta", 60)
        }

        btnAddSprite.setOnClickListener {
            addToCart("Sprite", 50)
        }

        btnAddSoda.setOnClickListener {
            addToCart("Soda", 25)
        }

        btnAddAyran.setOnClickListener {
            addToCart("Ayran", 20)
        }

        btnAddCay.setOnClickListener {
            addToCart("Çay", 25)
        }

        btnAddSogukCay.setOnClickListener {
            addToCart("Soğuk Çay", 45)
        }

        btnAddPortakalSuyu.setOnClickListener {
            addToCart("Portakal Suyu", 80)
        }

        btnAddLimonata.setOnClickListener {
            addToCart("Limonata", 85)
        }

        btnAddTurkKahvesi.setOnClickListener {
            addToCart("Türk Kahvesi", 80)
        }

        btnAddLatte.setOnClickListener {
            addToCart("Latte", 100)
        }

        btnAddEspresso.setOnClickListener {
            addToCart("Espresso", 90)
        }

        btnAddCappuccino.setOnClickListener {
            addToCart("Cappuccino", 120)
        }

        btnAddElmaSuyu.setOnClickListener {
            addToCart("Elma Suyu", 120)
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
