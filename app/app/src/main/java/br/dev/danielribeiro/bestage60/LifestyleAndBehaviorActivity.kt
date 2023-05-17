package br.dev.danielribeiro.bestage60

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class LifestyleAndBehaviorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lifestyle_and_behavior)

        val btnConceptOfTheBestAge =  findViewById<Button>(R.id.btnConceptOfTheBestAge)
        btnConceptOfTheBestAge.setOnClickListener {
            val intent = Intent(this, ConceptOfTheBestAgeActivity::class.java)
            startActivity(intent)
        }
    }
}