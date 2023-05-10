package br.dev.danielribeiro.bestage60

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class FunatiNewsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_funati_news)

        var btnWebFunati = findViewById<Button>(R.id.btnWebFunati)
        btnWebFunati.setOnClickListener {
            val url = "https://funati.am.gov.br/"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        }
    }
}