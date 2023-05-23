@file:Suppress("DEPRECATION")

package br.dev.danielribeiro.bestage60

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashScreenActivity : AppCompatActivity() {

    private val SPLASH_DELAY = 5000L // Tempo de exibição do SplashScreen em milissegundos

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        if (!isTaskRoot && intent?.hasCategory(Intent.CATEGORY_LAUNCHER) == true) {
            finish()
            return
        }

        // Define um atraso para iniciar a tela principal após o tempo de exibição do SplashScreen
        Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // Fecha a SplashScreen para que o usuário não possa voltar para ela ao pressionar o botão de voltar
        }, SPLASH_DELAY)
    }
}
