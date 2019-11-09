package com.man.hellosport.ui.schedule.lastmatch

import com.man.hellosport.data.local.Favorite
import com.man.hellosport.model.Events

interface LastMatchContract {
    fun showLoading()
    fun hideLoading()
    fun showEventList(data : List<Events>)
    fun showEventFromDb(data : List<Favorite>)
}