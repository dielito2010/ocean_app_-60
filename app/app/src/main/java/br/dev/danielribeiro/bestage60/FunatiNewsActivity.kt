package br.dev.danielribeiro.bestage60

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import com.bumptech.glide.Glide

class FunatiNewsActivity : AppCompatActivity() {
    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_funati_news)

        var imgFunati1 = findViewById<ImageView>(R.id.imgFunati1)
        Glide.with(this)
            .load("https://funati.am.gov.br/wp-content/uploads/2019/10/Atividades-2-1920x1610.jpg")
            .into(imgFunati1)

        var btnWebFunati = findViewById<Button>(R.id.btnWebFunati)
        btnWebFunati.setOnClickListener {
            val url = "https://funati.am.gov.br/"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        }
    }
}