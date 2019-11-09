package com.man.hellosport.ui.schedule.nextmatch

import com.man.hellosport.data.local.Favorite
import com.man.hellosport.model.Events

interface NextMatchContract {
    fun showLoading()
    fun hideLoading()
    fun showEventList(data : List<Events>)
    fun showEventFromDb(data : List<Favorite>)
}