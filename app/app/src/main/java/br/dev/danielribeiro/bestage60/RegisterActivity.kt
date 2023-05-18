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
    private lateinit var db: FirebaseFirestore
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

        // Inicializa os serviços firebase
        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        buttonRegister.setOnClickListener {
            val city = spinner.selectedItem.toString().trim()
            val fname = editTextFName.text.toString().trim()
            val lname = editTextLName.text.toString().trim()
            val email = editTextEmail.text.toString().trim()
            val passwd = editTextPasswd.text.toString().trim()
            registerWithEmailAndPassword(email, passwd, city)
            createFirestoreRecord(city, fname, lname, email)
        }
    }

    // Função para cadastrar um novo usuário
    private fun registerWithEmailAndPassword(email: String, passwd: String, city: String) {
        if (city == "INFORME SUA CIDADE"){
            Toast.makeText(this, "OBRIGATÓRIO INFORMAR UMA CIDADE", Toast.LENGTH_SHORT).show()
            return
        } else {
            auth.createUserWithEmailAndPassword(email, passwd)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Log.d(TAG, "User registration successful")
                    } else {
                        Log.e(TAG, "User registration failed", task.exception)
                        Toast.makeText(this, "ERRO AO CADASTRAR", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }

    private fun createFirestoreRecord(
        city: String,
        fname: String,
        lname: String,
        email: String){

        if (city == "INFORME SUA CIDADE"){
            Toast.makeText(this, "OBRIGATÓRIO INFORMAR UMA CIDADE", Toast.LENGTH_SHORT).show()
            return
        } else {
            val user = hashMapOf(
                "city" to city,
                "fname" to fname,
                "lname" to lname,
                "email" to email
            )
            db.collection("users")
                .add(user)
                .addOnSuccessListener { documentReference ->
                    Log.d(TAG, "Firestore record created with ID: ${documentReference.id}")
                    Toast.makeText(this, "Dados cadastrados com sucesso!", Toast.LENGTH_SHORT)
                        .show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                .addOnFailureListener { e ->
                    Log.e(TAG, "Error creating Firestore record", e)
                    Toast.makeText(this, "ERRO AO CADASTRAR", Toast.LENGTH_SHORT).show()
                }
        }
    }
    
    companion object {
        private const val TAG = "RegisterActivity"
    }

    fun goBackToLogin(view: View){
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}
