package br.dev.danielribeiro.bestage60

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import com.bumptech.glide.Glide

class ElderlyLawActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_elderly_law)

        val btnFooterEderlyLawActivity = findViewById<Button>(R.id.btnFooterEderlyLawActivity)
        btnFooterEderlyLawActivity.setOnClickListener {
            val url = "https://www.planalto.gov.br/ccivil_03/leis/2003/l10.741.htm"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        }
    }
}