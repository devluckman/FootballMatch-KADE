package com.man.hellosport.ui.detail.league.mvp

import com.man.hellosport.model.league.LeaguesItem

interface LeagueContract {
    fun showLeaguedetails(leaguesItem: LeaguesItem)
}