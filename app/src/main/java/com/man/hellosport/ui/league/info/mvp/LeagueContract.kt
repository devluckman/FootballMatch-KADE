package com.man.hellosport.ui.league.info.mvp

import com.man.hellosport.model.league.LeaguesItem

interface LeagueContract {
    fun showLoad()
    fun hideLoad()
    fun showLeaguedetails(leagues: List<LeaguesItem>)
}