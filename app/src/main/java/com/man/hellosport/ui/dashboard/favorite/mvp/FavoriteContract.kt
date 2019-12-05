package com.man.hellosport.ui.dashboard.favorite.mvp

import com.man.hellosport.model.event.Events

interface FavoriteContract {
    fun showLoading()
    fun hideLoading()
    fun emptyData()
    fun showEventList(data : List<Events>)
}