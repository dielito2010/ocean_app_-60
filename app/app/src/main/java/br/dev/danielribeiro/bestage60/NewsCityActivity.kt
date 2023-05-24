package br.dev.danielribeiro.bestage60

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class NewsCityActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_city)

        val btnZumbaManaus = findViewById<Button>(R.id.btnZumbaManaus)
        btnZumbaManaus.setOnClickListener {
            val intent = Intent(this, ZumbaManausActivity::class.java)
            startActivity(intent)
        }

        val btn2 = findViewById<Button>(R.id.btn2)
        btn2.setOnClickListener {
            val intent = Intent(this, FunatiNewsActivity::class.java)
            startActivity(intent)
        }

        val btn3 = findViewById<Button>(R.id.btn3)
        btn3.setOnClickListener {
            val intent = Intent(this, SeniorCitizenCouncilActivity::class.java)
            startActivity(intent)
        }
    }
}