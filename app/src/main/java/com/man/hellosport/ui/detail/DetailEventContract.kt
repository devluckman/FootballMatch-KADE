package com.man.hellosport.ui.detail

import com.man.hellosport.model.Teams

interface DetailEventContract {
    fun showLoading()
    fun hideLoading()
    fun showTeamdetails(dataHomeTeam: List<Teams>, dataAwayTeam: List<Teams>)
}