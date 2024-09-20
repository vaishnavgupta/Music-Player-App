package com.example.musicplayer

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash_screen)

        Handler().postDelayed({      //2 parameter--> runnable and the delay millisec
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()  //jab mainactivity par back press krege
            //toh yeh line call ho jayegi aur splash screen nahi dikhegi
        },2500)

    }
}