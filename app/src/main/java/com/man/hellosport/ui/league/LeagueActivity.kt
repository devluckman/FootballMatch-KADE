package com.man.hellosport.ui.league

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.man.hellosport.R
import com.man.hellosport.model.league.LeaguesItem
import com.man.hellosport.ui.league.info.LeagueFragment
import com.man.hellosport.ui.league.standing.StandingFragment
import com.man.hellosport.ui.league.teams.TeamsFragment
import kotlinx.android.synthetic.main.activity_league.*


class LeagueActivity : AppCompatActivity() {

    private lateinit var data: LeaguesItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_league)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        data = intent.getParcelableExtra("league")!!
        title = data.strLeague
        init()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    fun getLeagueItem() : LeaguesItem{
        return data
    }

    private  fun init() {
        viewpager.adapter = object : FragmentStateAdapter(this) {
            override fun createFragment(position: Int): Fragment {
                return when (position) {
                    0 -> LeagueFragment()
                    1 -> TeamsFragment()
                    else -> StandingFragment()
                }
            }
            override fun getItemCount(): Int = 3
        }
        TabLayoutMediator(tabs, viewpager) { tab, position ->
            tab.text = when (position) {
                0 -> "Info"
                1 -> "Teams"
                else -> "Standing"
            }
        }.attach()
    }


}
