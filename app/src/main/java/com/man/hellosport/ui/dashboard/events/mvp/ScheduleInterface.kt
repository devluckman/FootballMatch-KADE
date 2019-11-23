package com.man.hellosport.ui.dashboard.events.mvp

import com.man.hellosport.model.event.Events

interface ScheduleInterface {
    fun showLoading()
    fun hideLoading()
    fun showEventList(data : List<Events>)
}