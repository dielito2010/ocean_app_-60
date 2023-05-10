package br.dev.danielribeiro.bestage60

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class SeniorCitizenCouncilActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_senior_citizen_council)

        val btnFooterSeniorCitizenCouncilActivity = findViewById<Button>(R.id.btnFooterSeniorCitizenCouncilActivity)

        btnFooterSeniorCitizenCouncilActivity.setOnClickListener {
            val url = "https://www.manaus.am.gov.br/fdt/a-fundacao-doutor-thomas/conselho-municipal-do-idoso/"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        }
    }
}