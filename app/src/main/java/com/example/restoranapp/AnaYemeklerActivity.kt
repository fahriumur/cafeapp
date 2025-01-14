package com.example.restoranapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.ComponentActivity

class AnaYemeklerActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ana_yemekler)

        val btnAddDolma = findViewById<Button>(R.id.btn_add_dolma)
        val btnAddPilav = findViewById<Button>(R.id.btn_add_pilav)
        val btnAddManti = findViewById<Button>(R.id.btn_add_manti)
        val btnAddMusakka = findViewById<Button>(R.id.btn_add_musakka)
        val btnAddImamBayildi = findViewById<Button>(R.id.btn_add_imam_bayildi)
        val btnAddKarisikIzgara = findViewById<Button>(R.id.btn_add_karisik_izgara)
        val btnAddAdana = findViewById<Button>(R.id.btn_add_adana)
        val btnAddUrfa = findViewById<Button>(R.id.btn_add_urfa)
        val btnAddKarisikMakarna = findViewById<Button>(R.id.btn_add_karisik_makarna)
        val btnAddTavukluMakarna = findViewById<Button>(R.id.btn_add_tavuklu_makarna)
        val btnAddLahmacun = findViewById<Button>(R.id.btn_add_lahmacun)
        val btnAddPide = findViewById<Button>(R.id.btn_add_pide)
        val btnAddHamburger = findViewById<Button>(R.id.btn_add_hamburger)
        val btnAddPizza = findViewById<Button>(R.id.btn_add_pizza)
        val btnAddEtDoner = findViewById<Button>(R.id.btn_add_et_doner)
        val btnAddTavukDoner = findViewById<Button>(R.id.btn_add_tavuk_doner)
        val btnAddIskender = findViewById<Button>(R.id.btn_add_iskender)
        val btnSepetim = findViewById<Button>(R.id.btn_sepetim) // Sepetim butonunu buluyoruz

        btnAddDolma.setOnClickListener {
            addToCart("Dolma", 180)
        }

        btnAddPilav.setOnClickListener {
            addToCart("Pilav", 150)
        }

        btnAddManti.setOnClickListener {
            addToCart("Mantı", 220)
        }

        btnAddMusakka.setOnClickListener {
            addToCart("Musakka", 190)
        }

        btnAddImamBayildi.setOnClickListener {
            addToCart("İmam Bayıldı", 210)
        }

        btnAddKarisikIzgara.setOnClickListener {
            addToCart("Karışık Izgara", 300)
        }

        btnAddAdana.setOnClickListener {
            addToCart("Adana Kebabı", 350)
        }

        btnAddUrfa.setOnClickListener {
            addToCart("Urfa Kebabı", 240)
        }

        btnAddKarisikMakarna.setOnClickListener {
            addToCart("Karışık Makarna", 160)
        }

        btnAddTavukluMakarna.setOnClickListener {
            addToCart("Tavuklu Makarna", 170)
        }

        btnAddLahmacun.setOnClickListener {
            addToCart("Lahmacun", 100)
        }

        btnAddPide.setOnClickListener {
            addToCart("Pide", 120)
        }

        btnAddHamburger.setOnClickListener {
            addToCart("Hamburger", 150)
        }

        btnAddPizza.setOnClickListener {
            addToCart("Pizza", 200)
        }

        btnAddEtDoner.setOnClickListener {
            addToCart("Et Döner", 180)
        }

        btnAddTavukDoner.setOnClickListener {
            addToCart("Tavuk Döner", 150)
        }

        btnAddIskender.setOnClickListener {
            addToCart("İskender", 220)
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
