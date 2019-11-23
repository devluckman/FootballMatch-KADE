package com.man.hellosport.ui.detail.league

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.man.hellosport.R
import com.man.hellosport.model.event.Events
import com.man.hellosport.model.league.LeaguesItem

class LeagueActivity : AppCompatActivity() {

    private lateinit var data: LeaguesItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_league)
        data = intent.getParcelableExtra("league")!!

    }
}
