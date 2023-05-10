package br.dev.danielribeiro.bestage60

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn1 = findViewById<Button>(R.id.btn1)
        btn1.setOnClickListener {
            val intent = Intent(this, NewsCityActivity::class.java)
            startActivity(intent)
        }

        val btn7 = findViewById<Button>(R.id.btn7)
        btn7.setOnClickListener {
            val intent = Intent(this, ElderlyLawActivity::class.java)
            startActivity(intent)
        }
    }
}