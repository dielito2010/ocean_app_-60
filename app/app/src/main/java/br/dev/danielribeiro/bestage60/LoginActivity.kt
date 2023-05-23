package br.dev.danielribeiro.bestage60

import android.content.Intent
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Inicializa o Firebase Authentication
        auth = FirebaseAuth.getInstance()

        var imgLogo = findViewById<ImageView>(R.id.imgLogo)
        Glide.with(this)
            .load("https://raw.githubusercontent.com/dielito2010/ocean_app_mais_60/main/img/Captura%20de%20tela%202023-04-13%20152001.jpg")
            .into(imgLogo)
    }

    // Função para fazer login
    fun login(view: View) {
        val progressBar: ProgressBar = findViewById(R.id.progressbarLogin)
        val editTextEmail: EditText = findViewById(R.id.editTextEmail)
        val editTextSenha: EditText = findViewById(R.id.editTextSenha)
        val email = editTextEmail.text.toString().trim()
        val senha = editTextSenha.text.toString().trim()

        if (email.isNotEmpty() && senha.isNotEmpty()) {
            progressBar.setVisibility(View.VISIBLE)
            // Faz o login com o Firebase Authentication
            auth.signInWithEmailAndPassword(email, senha)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Login bem sucedido
                        Toast.makeText(this, "Login bem sucedido", Toast.LENGTH_SHORT).show()
                        // Abre a tela principal do aplicativo
                        progressBar.setVisibility(View.GONE)
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        // Login falhou
                        Toast.makeText(this, "ERRO: verifique email e senha...", Toast.LENGTH_SHORT).show()
                        progressBar.setVisibility(View.GONE)
                    }
                }
        } else {
            Toast.makeText(this,"Informe todo os campos antes de continuar", Toast.LENGTH_SHORT).show()
        }
    }
    fun register(view: View){
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
        finish()
    }
}
