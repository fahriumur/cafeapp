package com.example.restoranapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity

class AdminLoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_login)

        val edtPassword = findViewById<EditText>(R.id.edt_password)
        val btnLogin = findViewById<Button>(R.id.btn_login)

        btnLogin.setOnClickListener {
            val password = edtPassword.text.toString()
            if (password == "Umur") {  // Şifreyi buraya girin
                val intent = Intent(this, YoneticiPaneliActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Yanlış şifre, tekrar deneyin!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
