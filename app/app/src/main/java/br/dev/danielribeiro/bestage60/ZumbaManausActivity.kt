package br.dev.danielribeiro.bestage60

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class ZumbaManausActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_zumba_manaus)

        val btnDancaSavior = findViewById<Button>(R.id.btnDancaSavior)
        btnDancaSavior.setOnClickListener {
            val intent = Intent(this, DancaSaviorActivity::class.java)
            startActivity(intent)
        }
    }
}