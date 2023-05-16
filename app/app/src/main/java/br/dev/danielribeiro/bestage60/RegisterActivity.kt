package br.dev.danielribeiro.bestage60

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class RegisterActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var spinner: Spinner
    private val options = arrayOf(
        "INFORME SUA CIDADE",
        "Aracaju",
        "Belém",
        "Belo Horizonte",
        "Boa Vista",
        "Brasília",
        "Campo Grande",
        "Cuiabá",
        "Curitiba",
        "Florianópolis",
        "Fortaleza",
        "Goiânia",
        "João Pessoa",
        "Macapá",
        "Maceió",
        "Manaus",
        "Natal",
        "Palmas",
        "Porto Alegre",
        "Porto Velho",
        "Recife",
        "Rio Branco",
        "Rio de Janeiro",
        "Salvador",
        "São Luís",
        "São Paulo",
        "Teresina",
        "Vitória"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        spinner = findViewById(R.id.spinnerCity)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, options)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                // lógica para lidar com a seleção do usuário
                val selectedItem = options[position]
                Toast.makeText(applicationContext, "$selectedItem", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // lógica para lidar com nenhum item selecionado
            }
        }

        //Validações de campos
        val editTextFName: EditText = findViewById(R.id.editTextFName)
        val editTextLName: EditText = findViewById(R.id.editTextLName)
        val editTextEmail: EditText = findViewById(R.id.editTextEmail)
        val editTextPasswd: EditText = findViewById(R.id.editTextPasswd)
        val buttonRegister: Button = findViewById(R.id.buttonRegister)

        // Adiciona um ouvinte para o campo de nome
        editTextFName.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Verifica se o campo de nome é válido
                val fName = s.toString().trim()
                val lName = editTextLName.text.toString().trim()
                val email = editTextEmail.text.toString().trim()
                val password = editTextPasswd.text.toString().trim()
                buttonRegister.isEnabled = fName.isNotEmpty() && lName.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()
            }
        })

        // Adiciona um ouvinte para o campo de sobrenome
        editTextLName.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Verifica se o campo de sobrenome é válido
                val fName = editTextLName.text.toString().trim()
                val lName = s.toString().trim()
                val email = editTextEmail.text.toString().trim()
                val password = editTextPasswd.text.toString().trim()
                buttonRegister.isEnabled = fName.isNotEmpty() && lName.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()
            }
        })

        // Adiciona um ouvinte para o campo de nome
        editTextEmail.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Verifica se o campo de nome é válido
                val fName = editTextFName.text.toString().trim()
                val lName = editTextLName.text.toString().trim()
                val email = s.toString().trim()
                val password = editTextPasswd.text.toString().trim()
                buttonRegister.isEnabled = fName.isNotEmpty() && lName.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()
            }
        })

        // Adiciona um ouvinte para o campo de nome
        editTextPasswd.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Verifica se o campo de nome é válido
                val fName = editTextFName.text.toString().trim()
                val lName = editTextLName.text.toString().trim()
                val email = editTextEmail.text.toString().trim()
                val password = s.toString().trim()
                buttonRegister.isEnabled = fName.isNotEmpty() && lName.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()
            }
        })

        // Inicializa o Firebase Authentication
        auth = FirebaseAuth.getInstance()
    }

    // Função para cadastrar um novo usuário
    fun register(view: View) {
        val editTextEmail: EditText = findViewById(R.id.editTextEmail)
        val editTextSenha: EditText = findViewById(R.id.editTextSenha)
        val spinnerCity: Spinner = findViewById(R.id.spinnerCity)
        val editTextFName: EditText = findViewById(R.id.editTextFName)
        val editTextLName: EditText = findViewById(R.id.editTextLName)
        val db = FirebaseFirestore.getInstance()
        val city = spinnerCity.selectedItem.toString().trim()
        val senha = editTextSenha.text.toString().trim()
        val email = editTextEmail.text.toString().trim()
        val fname = editTextFName.text.toString().trim()
        val lname = editTextLName.text.toString().trim()

        if (email.isNotEmpty() && senha.isNotEmpty()) {
            // Cadastra um novo usuário com o Firebase Authentication
            auth.createUserWithEmailAndPassword(email, senha)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Cadastro bem sucedido
                        val uid = auth.uid
                        val user = hashMapOf(
                            "city" to city,
                            "fname" to fname,
                            "lname" to lname,
                            "uid" to uid
                        )
                        val usersCollection = db.collection("users")
                        usersCollection.add(user)
                            .addOnSuccessListener { documentReference ->
                                // Sucesso na inserção
                                Log.d(TAG, "Documento inserido com ID: ${documentReference.id}")
                            }
                            .addOnFailureListener { e ->
                                // Falha na inserção
                                Log.e(TAG, "Erro ao inserir documento", e)
                            }
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

    fun goBackToLogin(view: View){
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}
