package br.dev.danielribeiro.bestage60

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser

        if (currentUser == null) {
            // Abre a tela de login
            val loginActivity = Intent(this, LoginActivity::class.java)
            startActivity(loginActivity)
            finish() // Fecha a MainActivity para que o usuário não possa voltar para ela ao pressionar o botão Voltar
        } else {
            val txtCurrentUser = findViewById<TextView>(R.id.txtCurrentUser)
            val email = currentUser.email
            txtCurrentUser.text = email
        }

        val btnLogOut = findViewById<Button>(R.id.btnLogOut)
        btnLogOut.setOnClickListener{
            auth.signOut()
            val loginActivity = Intent(this, LoginActivity::class.java)
            startActivity(loginActivity)
            finish()
        }

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