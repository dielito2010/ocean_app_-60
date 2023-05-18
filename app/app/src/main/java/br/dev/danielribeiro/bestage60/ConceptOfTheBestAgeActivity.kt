package br.dev.danielribeiro.bestage60

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import com.bumptech.glide.Glide

class ConceptOfTheBestAgeActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_concept_of_the_best_age)

        var imgConceptOfTheBestAgeActivity = findViewById<ImageView>(R.id.imgConceptOfTheBestAgeActivity)
        Glide.with(this)
            .load("https://images.pexels.com/photos/339620/pexels-photo-339620.jpeg?auto=compress&cs=tinysrgb&w=300&h=200&dpr=1")
            .into(imgConceptOfTheBestAgeActivity)

        var imgConceptOfTheBestAgeActivity1 = findViewById<ImageView>(R.id.imgConceptOfTheBestAgeActivity1)
        Glide.with(this)
            .load("https://images.pexels.com/photos/6158652/pexels-photo-6158652.jpeg?auto=compress&cs=tinysrgb&w=300&h=200&dpr=1")
            .into(imgConceptOfTheBestAgeActivity1)

        var btnConceptOfTheBestAgeActivityWeb = findViewById<Button>(R.id.btnConceptOfTheBestAgeActivityWeb)
        btnConceptOfTheBestAgeActivityWeb.setOnClickListener {
            val url = "https://vivatecs.com/ebook"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        }
    }
}