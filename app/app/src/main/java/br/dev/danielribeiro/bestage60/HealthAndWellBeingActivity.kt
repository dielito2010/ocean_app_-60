package br.dev.danielribeiro.bestage60

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class HealthAndWellBeingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_health_and_well_being)

        val btnFiveHealthTips = findViewById<Button>(R.id.btnFiveHealthTips)
        btnFiveHealthTips.setOnClickListener {
            val intent = Intent(this, FiveHealthTipsActivity::class.java)
            startActivity(intent)
        }
    }
}