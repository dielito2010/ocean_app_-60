package br.dev.danielribeiro.bestage60

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import com.bumptech.glide.Glide

class SaoPauloFriendOfTheElderlyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sao_paulo_friend_of_the_elderly)

        //https://www.desenvolvimentosocial.sp.gov.br/wp-content/uploads/2021/06/pexels-marcus-aurelius-6787970-768x512.jpg
        var imgSPFriendElderlyWebActivity =
            findViewById<ImageView>(R.id.imgSPFriendElderlyWebActivity)
        Glide.with(this)
            .load("https://www.desenvolvimentosocial.sp.gov.br/wp-content/uploads/2021/06/pexels-marcus-aurelius-6787970-768x512.jpg?auto=compress&cs=tinysrgb&w=300&h=200&dpr=1")
            .into(imgSPFriendElderlyWebActivity)

        var btnSPFriendElderlyWeb = findViewById<Button>(R.id.btnSPFriendElderlyWeb)
        btnSPFriendElderlyWeb.setOnClickListener {
            val url =
                "https://www.desenvolvimentosocial.sp.gov.br/assistencia-social/sao-paulo-amigo-do-idoso/"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        }
    }
}