package br.dev.danielribeiro.bestage60

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class NewsSaoPauloActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_sao_paulo)

        val btnSPAmigoDoIdoso = findViewById<Button>(R.id.btnSPAmigoDoIdoso)
        btnSPAmigoDoIdoso.setOnClickListener {
            val intent = Intent(this, SaoPauloFriendOfTheElderlyActivity::class.java)
            startActivity(intent)
        }
    }
}