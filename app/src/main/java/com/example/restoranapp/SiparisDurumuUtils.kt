package com.example.restoranapp

import android.content.Context

fun updateSiparisDurumu(context: Context, siparisId: String, yeniDurum: SiparisDurumu) {
    val sharedPreferences = context.getSharedPreferences("Orders", Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()
    editor.putString(siparisId, yeniDurum.name)
    editor.apply()
}
