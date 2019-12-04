package com.man.hellosport.ui.league.info.mvp

import com.man.hellosport.model.league.LeaguesItem

interface LeagueContract {
    fun showLeaguedetails(leagues: List<LeaguesItem>)
}