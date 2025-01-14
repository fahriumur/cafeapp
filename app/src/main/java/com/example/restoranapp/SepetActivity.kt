package com.example.restoranapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity

class SepetActivity : ComponentActivity() {
    private lateinit var totalText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sepet)

        val sepetLayout = findViewById<LinearLayout>(R.id.urun_listesi_layout)
        val btnSiparisiTamamla = findViewById<Button>(R.id.btn_siparisi_tamamla)
        val btnMenuyeGit = findViewById<Button>(R.id.btn_menuye_git)
        totalText = findViewById(R.id.total_tutar)

        val sharedPreferences = getSharedPreferences("Cart", Context.MODE_PRIVATE)
        var totalPrice = 0.0

        val items = listOf(
            Triple("Dolma", R.drawable.dolma, 180), Triple("Pilav", R.drawable.pilav, 150),
            Triple("Mantı", R.drawable.manti, 220), Triple("Musakka", R.drawable.musakka, 190),
            Triple("İmam Bayıldı", R.drawable.imam_bayildi, 210), Triple("Karışık Izgara", R.drawable.karisik_izgara, 300),
            Triple("Adana Kebabı", R.drawable.adana, 250), Triple("Urfa Kebabı", R.drawable.urfa, 240),
            Triple("Karışık Makarna", R.drawable.karisik_makarna, 160), Triple("Tavuklu Makarna", R.drawable.tavuklu_makarna, 170),
            Triple("Lahmacun", R.drawable.lahmacun, 100), Triple("Pide", R.drawable.pide, 120),
            Triple("Hamburger", R.drawable.hamburger, 150), Triple("Pizza", R.drawable.pizza, 200),
            Triple("Et Döner", R.drawable.et_doner, 180), Triple("Tavuk Döner", R.drawable.tavuk_doner, 150),
            Triple("İskender", R.drawable.iskender, 220), Triple("Hummus", R.drawable.hummus, 50),
            Triple("Haydari", R.drawable.haydari, 45), Triple("Acılı Ezme", R.drawable.acili_ezme, 40),
            Triple("Muhammara", R.drawable.muhammara, 55), Triple("Şakşuka", R.drawable.saksuka, 50),
            Triple("Cacık", R.drawable.cacik, 45), Triple("Sigara Böreği", R.drawable.sigara_boregi, 50),
            Triple("Patates Kızartması", R.drawable.patates_kizartmasi, 45), Triple("Soğan Halkası", R.drawable.sogan_halkasi, 40),
            Triple("Mozzarella Sticks", R.drawable.mozzarella_sticks, 55), Triple("Nuggets", R.drawable.nuggets, 60),
            Triple("Tavuk Kanat", R.drawable.tavuk_kanat, 70), Triple("Cola", R.drawable.cola, 60),
            Triple("Fanta", R.drawable.fanta, 60), Triple("Sprite", R.drawable.sprite, 50),
            Triple("Soda", R.drawable.soda, 25), Triple("Ayran", R.drawable.ayran, 20),
            Triple("Çay", R.drawable.cay, 25), Triple("Soğuk Çay", R.drawable.soguk_cay, 45),
            Triple("Portakal Suyu", R.drawable.portakal_suyu, 80), Triple("Limonata", R.drawable.limonata, 85),
            Triple("Türk Kahvesi", R.drawable.turk_kahvesi, 80), Triple("Latte", R.drawable.latte, 100),
            Triple("Espresso", R.drawable.espresso, 90), Triple("Cappuccino", R.drawable.cappuccino, 120),
            Triple("Elma Suyu", R.drawable.elma_suyu, 120), Triple("Caesar Salata", R.drawable.caesar_salata, 150),
            Triple("Çoban Salata", R.drawable.coban_salata, 120), Triple("Greek Salata", R.drawable.greek_salata, 140),
            Triple("Roka Salata", R.drawable.roka_salata, 130), Triple("Yoğurtlu Havuç Salatası", R.drawable.yogurtlu_havuc_salatasi, 130),
            Triple("Patates Salatası", R.drawable.patates_salatasi, 120), Triple("Rus Salatası", R.drawable.rus_salatasi, 140),
            Triple("Yumurtalı Salata", R.drawable.yumurtali_salata, 135), Triple("Ton Balıklı Salata", R.drawable.ton_balikli_salata, 160),
            Triple("Mercimekli Salata", R.drawable.mercimekli_salata, 140), Triple("Baklava", R.drawable.baklava, 225),
            Triple("Künefe", R.drawable.kunefe, 230), Triple("Kazandibi", R.drawable.kazandibi, 220),
            Triple("Sütlaç", R.drawable.sutlac, 215), Triple("Frambuazlı Cheesecake", R.drawable.cheescakeframbuazli, 240),
            Triple("Limonlu Cheesecake", R.drawable.cheescakelimonlu, 250), Triple("Mozaik Pasta", R.drawable.mozaikpasta, 210),
            Triple("Tiramisu", R.drawable.tiramisu, 230), Triple("Profiterol", R.drawable.profiterol, 220),
            Triple("Magnolia", R.drawable.magnolia, 240), Triple("Trileçe", R.drawable.trilece, 250),
            Triple("Supangle", R.drawable.supangle, 210), Triple("San Sebastian Cheesecake", R.drawable.sansebastian, 230)
        )

        for (item in items) {
            val itemCount = sharedPreferences.getInt(item.first, 0)
            if (itemCount > 0) {
                val itemLayout = LinearLayout(this)
                itemLayout.orientation = LinearLayout.HORIZONTAL
                itemLayout.gravity = Gravity.CENTER_VERTICAL
                itemLayout.layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )

                val imageView = ImageView(this)
                imageView.setImageResource(item.second)
                imageView.layoutParams = LinearLayout.LayoutParams(
                    200,
                    200
                )
                imageView.scaleType = ImageView.ScaleType.CENTER_CROP

                val itemText = TextView(this)
                val itemPrice = item.third * itemCount
                itemText.text = "${item.first} - Miktar: $itemCount - Fiyat: $itemPrice TL"
                itemText.textSize = 18f
                itemText.layoutParams = LinearLayout.LayoutParams(
                    0,
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    1f
                )

                val btnSil = Button(this)
                btnSil.text = "Sil"
                btnSil.setOnClickListener {
                    removeFromCart(item.first)
                    sepetLayout.removeView(itemLayout)
                    totalPrice -= itemPrice
                    totalText.text = "Toplam Fiyat: $totalPrice TL"
                    Toast.makeText(this, "${item.first} sepetten silindi!", Toast.LENGTH_SHORT).show()
                }

                totalPrice += itemPrice

                itemLayout.addView(imageView)
                itemLayout.addView(itemText)
                itemLayout.addView(btnSil)
                sepetLayout.addView(itemLayout)
            }
        }

        totalText.text = "Toplam Fiyat: $totalPrice TL"

        btnSiparisiTamamla.setOnClickListener {
            val intent = Intent(this, SiparisOzetiActivity::class.java)
            intent.putExtra("totalPrice", totalPrice)
            startActivity(intent)
        }

        btnMenuyeGit.setOnClickListener {
            val intent = Intent(this, SiparisVerActivity::class.java)
            startActivity(intent)
        }
    }

    private fun removeFromCart(itemName: String) {
        val sharedPreferences = getSharedPreferences("Cart", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.remove(itemName)
        editor.apply()
    }
}
