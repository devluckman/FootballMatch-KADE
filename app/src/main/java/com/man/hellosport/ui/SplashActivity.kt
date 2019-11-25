package com.man.hellosport.ui

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.man.hellosport.R
import org.jetbrains.anko.intentFor

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        supportActionBar!!.hide()
        Handler().postDelayed({
            startActivity(intentFor<MainActivity>())
            finish()
        }, 3000)
    }
}
