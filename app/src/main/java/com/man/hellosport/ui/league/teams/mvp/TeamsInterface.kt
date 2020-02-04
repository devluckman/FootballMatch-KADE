package com.man.hellosport.ui.league.teams.mvp

import com.man.hellosport.model.teams.Teams

interface TeamsInterface {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data : List<Teams>)
}