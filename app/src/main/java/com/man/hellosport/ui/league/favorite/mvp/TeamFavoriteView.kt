package com.man.hellosport.ui.league.favorite.mvp

import com.man.hellosport.model.teams.Teams

interface TeamFavoriteView {
    fun showLoading()
    fun hideLoading()
    fun emptyData()
    fun showTeamListFavorite(data : List<Teams>)
}