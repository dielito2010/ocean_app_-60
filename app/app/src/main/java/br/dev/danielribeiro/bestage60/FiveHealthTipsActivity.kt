package br.dev.danielribeiro.bestage60

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import com.bumptech.glide.Glide

class FiveHealthTipsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_five_health_tips)

        var imgFiveHealthTips = findViewById<ImageView>(R.id.imgFiveHealthTips)
        Glide.with(this)
            .load("https://www.oestesaude.com.br/img/blog/53b58f360365c0e41928ac87705d7deb365302b5.jpeg?auto=compress&cs=tinysrgb&w=300&h=200&dpr=1")
            .into(imgFiveHealthTips)

        var btnFiveHealthTipsWeb = findViewById<Button>(R.id.btnFiveHealthTipsWeb)
        btnFiveHealthTipsWeb.setOnClickListener {
            val url = "https://www.oestesaude.com.br/oestemaissaude/saude/cinco-dicas-para-manter-a-saude-na-terceira-idade.html"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        }
    }
}