package br.dev.danielribeiro.bestage60

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class DancaSaviorActivity : AppCompatActivity() {
    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_danca_savior)

        val btnWhatSavior = findViewById<Button>(R.id.btnWhatSavior)
        btnWhatSavior.setOnClickListener {
            val phoneNumber = "+5592988372153" // Insira o número de telefone do WhatsApp aqui
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://wa.me/$phoneNumber"))
            startActivity(intent)
        }

        webView = findViewById(R.id.webView)

        // Configurar o WebView
        webView.settings.javaScriptEnabled = true
        webView.webViewClient = WebViewClient()

        // Carregar o conteúdo da web
        webView.loadUrl("https://goo.gl/maps/NpBsKU3np3HAY1k27")
    }
}
