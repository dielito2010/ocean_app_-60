package br.dev.danielribeiro.bestage60

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser
        val db = FirebaseFirestore.getInstance()

        if (currentUser == null) {
            // Abre a tela de login
            val loginActivity = Intent(this, LoginActivity::class.java)
            startActivity(loginActivity)
            finish() // Fecha a MainActivity para que o usuário não possa voltar para ela ao pressionar o botão Voltar
        } else {
            val txtCurrentUser = findViewById<TextView>(R.id.txtCurrentUser)
            val btnLogOut = findViewById<Button>(R.id.btnLogOut)
            val emailFirebase = currentUser.email
            val collectionRef = db.collection("users")
            collectionRef.whereEqualTo("email", emailFirebase)
                .get()
                .addOnSuccessListener { querySnapshot ->
                    for (document in querySnapshot) {
                        val data = document.data
                        txtCurrentUser.text = "Bem vindo!  " + data.getValue("fname") as CharSequence?
                        btnLogOut.isEnabled = currentUser != null
                    }
                }
                .addOnFailureListener { e ->
                    Log.e(TAG, "Error to get First Name", e)
                }
        }

        val btnHealthAndWellBeing = findViewById<Button>(R.id.btnHealthAndWellBeing)
        btnHealthAndWellBeing.setOnClickListener {
            val intent = Intent(this, HealthAndWellBeingActivity::class.java)
            startActivity(intent)
        }

        val btnLifestyleAndBehavior = findViewById<Button>(R.id.btnLifestyleAndBehavior)
        btnLifestyleAndBehavior.setOnClickListener {
            val intent = Intent(this, LifestyleAndBehaviorActivity::class.java)
            startActivity(intent)
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

    companion object {
        const val TAG = "MAinActivity"
    }
}