package com.example.restoranapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.ComponentActivity

class TatlilarActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tatlilar)

        val btnAddBaklava = findViewById<Button>(R.id.btn_add_baklava)
        val btnAddKunefe = findViewById<Button>(R.id.btn_add_kunefe)
        val btnAddKazandibi = findViewById<Button>(R.id.btn_add_kazandibi)
        val btnAddSutlac = findViewById<Button>(R.id.btn_add_sutlac)
        val btnAddFrambuazliCheesecake = findViewById<Button>(R.id.btn_add_frambuazli_cheesecake)
        val btnAddLimonluCheesecake = findViewById<Button>(R.id.btn_add_limonlu_cheesecake)
        val btnAddMozaikPasta = findViewById<Button>(R.id.btn_add_mozaik_pasta)
        val btnAddTiramisu = findViewById<Button>(R.id.btn_add_tiramisu)
        val btnAddProfiterol = findViewById<Button>(R.id.btn_add_profiterol)
        val btnAddMagnolia = findViewById<Button>(R.id.btn_add_magnolia)
        val btnAddTrilece = findViewById<Button>(R.id.btn_add_trilece)
        val btnAddSupangle = findViewById<Button>(R.id.btn_add_supangle)
        val btnAddSanSebastian = findViewById<Button>(R.id.btn_add_san_sebastian)
        val btnSepetim = findViewById<Button>(R.id.btn_sepetim) // Sepetim butonunu buluyoruz


        btnAddBaklava.setOnClickListener {
            addToCart("Baklava", 225)
        }

        btnAddKunefe.setOnClickListener {
            addToCart("Künefe", 230)
        }

        btnAddKazandibi.setOnClickListener {
            addToCart("Kazandibi", 220)
        }

        btnAddSutlac.setOnClickListener {
            addToCart("Sütlaç", 215)
        }

        btnAddFrambuazliCheesecake.setOnClickListener {
            addToCart("Frambuazlı Cheesecake", 240)
        }

        btnAddLimonluCheesecake.setOnClickListener {
            addToCart("Limonlu Cheesecake", 250)
        }

        btnAddMozaikPasta.setOnClickListener {
            addToCart("Mozaik Pasta", 210)
        }

        btnAddTiramisu.setOnClickListener {
            addToCart("Tiramisu", 230)
        }

        btnAddProfiterol.setOnClickListener {
            addToCart("Profiterol", 220)
        }

        btnAddMagnolia.setOnClickListener {
            addToCart("Magnolia", 240)
        }

        btnAddTrilece.setOnClickListener {
            addToCart("Trileçe", 250)
        }

        btnAddSupangle.setOnClickListener {
            addToCart("Supangle", 210)
        }

        btnAddSanSebastian.setOnClickListener {
            addToCart("San Sebastian Cheesecake", 230)
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
        Toast.makeText(this, "$itemName Siparişiniz sepete eklendi!", Toast.LENGTH_SHORT).show()
    }
}
