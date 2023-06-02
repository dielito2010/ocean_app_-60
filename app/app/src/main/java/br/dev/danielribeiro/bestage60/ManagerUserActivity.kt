package br.dev.danielribeiro.bestage60

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class ManagerUserActivity : AppCompatActivity() {

    private var auth = FirebaseAuth.getInstance()
    private var db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manager_user)

        val currentUser = auth.currentUser
        val txtCurrentUser = findViewById<TextView>(R.id.txtCurrentUser)
        val btnLogout = findViewById<Button>(R.id.btnLogout)
        val emailFirebase = currentUser?.email
        val collectionRef = db.collection("users")
        collectionRef.whereEqualTo("email", emailFirebase)
            .get()
            .addOnSuccessListener { querySnapshot ->
                for (document in querySnapshot) {
                    val data = document.data
                    val welcome = resources.getString(R.string.welcome)
                    val fname = data.getValue("fname") as CharSequence?
                    val lname = data.getValue("lname") as CharSequence?
                    txtCurrentUser.text = "${welcome} \n ${fname} ${lname}"
                    btnLogout.isEnabled = currentUser != null
                }
            }
            .addOnFailureListener { e ->
                Log.e(MainActivity.TAG, "Error to get First Name", e)
            }

        btnLogout.setOnClickListener {
            auth.signOut()
            val loginActivity = Intent(this, LoginActivity::class.java)
            startActivity(loginActivity)
            finish()
        }

        // Mostra o email do usuário atual no EditText
        val editTextTextEmailAddress = findViewById<EditText>(R.id.editTextTextEmailAddress)
        editTextTextEmailAddress.setText(auth.currentUser?.email)
        val btnUpdateEmail = findViewById<Button>(R.id.btnUpdateEmail)
        btnUpdateEmail.setOnClickListener {
            Toast.makeText(
                this@ManagerUserActivity,
                "Verifique o email para poder atualizar...",
                Toast.LENGTH_SHORT
            ).show()
        }
        // Monitora as alterações no campo de email
        editTextTextEmailAddress.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Método chamado antes de o texto ser alterado
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Método chamado quando o texto está sendo alterado
            }

            override fun afterTextChanged(s: Editable?) {
                // Método chamado após o texto ter sido alterado
                val novoEmail = s.toString()
                btnUpdateEmail.setOnClickListener {
                    if (emailFirebase != novoEmail) atualizarEmail(novoEmail) else {
                        val alertDialog =
                            AlertDialog.Builder(this@ManagerUserActivity) // 'this' representa o contexto da Activity atual
                                .setTitle("ATENÇÃO:")
                                .setMessage("O email precisa ser diferente para poder atualizar")
                                .setPositiveButton("OK") { dialog, _ ->
                                    // Lógica a ser executada quando o botão OK é pressionado
                                    dialog.dismiss()
                                }
                                .create()

                        alertDialog.show()
                    }
                }
            }
        })

        val btnUserDelete = findViewById<Button>(R.id.btnUserDelete)
        btnUserDelete.setOnClickListener {
            excluirCadastro(emailFirebase.toString())
        }
    }

    private fun atualizarEmail(novoEmail: String) {
        val user = auth.currentUser
        val emailFirebase = user?.email.toString()

        user?.updateEmail(novoEmail)?.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                // Atualiza o email no Firestore
                db.collection("users")
                    .whereEqualTo("email", emailFirebase)
                    .get()
                    .addOnSuccessListener { documents ->
                        for (document in documents) {
                            val docRef = db.collection("users").document(document.id)
                            docRef.update("email", novoEmail)
                        }
                    }
                Toast.makeText(
                    this,
                    "Email atualizado com sucesso",
                    Toast.LENGTH_SHORT
                ).show()
                val MainActivity = Intent(this, MainActivity::class.java)
                startActivity(MainActivity)
                finish()
            } else {
                Toast.makeText(
                    this,
                    "ERROR: tente SAIR, depois faça LOGIN e então tente atualizar o email",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun excluirCadastro(email: String) {
        val user = auth.currentUser

        // Excluir cadastro do Firebase Auth
        user?.delete()?.addOnCompleteListener { authTask ->
            val alertDialog =
                AlertDialog.Builder(this) // 'this' representa o contexto da Activity atual
                    .setTitle("ATENÇÃO:")
                    .setMessage("Deseja excluir essa conta? Essa operação não poderá ser desfeita.")
                    .setPositiveButton("OK") { dialog, _ ->
                        if (authTask.isSuccessful) {
                            // Cadastro excluído com sucesso no Firebase Auth

                            // Excluir documento correspondente no Firestore
                            db.collection("users")
                                .whereEqualTo("email", email)
                                .get()
                                .addOnSuccessListener { documents ->
                                    for (document in documents) {
                                        val docRef = db.collection("users").document(document.id)
                                        docRef.delete()
                                            .addOnSuccessListener {
                                                // Documento excluído com sucesso no Firestore
                                                Toast.makeText(
                                                    this,
                                                    "Cadastro excluído com sucesso",
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                                val loginActivity =
                                                    Intent(this, LoginActivity::class.java)
                                                startActivity(loginActivity)
                                                finish()
                                            }
                                            .addOnFailureListener { exception ->
                                                // Ocorreu um erro ao excluir o documento no Firestore
                                                Toast.makeText(
                                                    this,
                                                    "Erro ao excluir cadastro",
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                            }
                                    }
                                }
                        } else {
                            // Ocorreu um erro ao excluir o cadastro no Firebase Auth
                            Toast.makeText(
                                this,
                                "Erro ao excluir cadastro",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        dialog.dismiss()
                    }
                    .setNegativeButton("Cancelar") { dialog, _ ->
                        // Lógica a ser executada quando o botão Cancelar é pressionado
                        dialog.dismiss()
                    }
                    .create()

            alertDialog.show()
        }
    }
}