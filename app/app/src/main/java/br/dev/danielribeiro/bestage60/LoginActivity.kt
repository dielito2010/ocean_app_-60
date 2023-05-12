package br.dev.danielribeiro.bestage60

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth



class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Inicializa o Firebase Authentication
        auth = FirebaseAuth.getInstance()
        val editTextEmail: EditText = findViewById(R.id.editTextEmail)
        val editTextSenha: EditText = findViewById(R.id.editTextSenha)
        val buttonCadastro: Button = findViewById(R.id.buttonCadastro)

        // Adiciona um ouvinte para o campo de e-mail
        editTextEmail.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Verifica se o campo de e-mail é válido
                val email = s.toString().trim()
                val senha = editTextSenha.text.toString().trim()
                buttonCadastro.isEnabled = email.isNotEmpty() && senha.isNotEmpty()
            }
        })

        // Adiciona um ouvinte para o campo de senha
        editTextSenha.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Verifica se o campo de senha é válida
                val email = editTextEmail.text.toString().trim()
                val senha = s.toString().trim()
                buttonCadastro.isEnabled = email.isNotEmpty() && senha.isNotEmpty()
            }
        })
    }

    // Função para fazer login
    fun login(view: View) {
        val editTextEmail: EditText = findViewById(R.id.editTextEmail)
        val editTextSenha: EditText = findViewById(R.id.editTextSenha)
        val email = editTextEmail.text.toString().trim()
        val senha = editTextSenha.text.toString().trim()

        if (email.isNotEmpty() && senha.isNotEmpty()) {
            // Faz o login com o Firebase Authentication
            auth.signInWithEmailAndPassword(email, senha)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Login bem sucedido
                        Toast.makeText(this, "Login bem sucedido", Toast.LENGTH_SHORT).show()
                        // Abre a tela principal do aplicativo
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        // Login falhou
                        Toast.makeText(this, "Login falhou", Toast.LENGTH_SHORT).show()
                    }
                }
        }

    }
    // Função para cadastrar um novo usuário
    fun cadastrar(view: View) {
        val editTextEmail: EditText = findViewById(R.id.editTextEmail)
        val editTextSenha: EditText = findViewById(R.id.editTextSenha)
        val email = editTextEmail.text.toString().trim()
        val senha = editTextSenha.text.toString().trim()

        if (email.isNotEmpty() && senha.isNotEmpty()) {
            // Cadastra um novo usuário com o Firebase Authentication
            auth.createUserWithEmailAndPassword(email, senha)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Cadastro bem sucedido
                        Toast.makeText(this, "Cadastro bem sucedido!", Toast.LENGTH_SHORT).show()
                        // Abre a tela principal do aplicativo
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        // Cadastro falhou
                        Toast.makeText(this, "Erro ao cadastrar", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }
}
