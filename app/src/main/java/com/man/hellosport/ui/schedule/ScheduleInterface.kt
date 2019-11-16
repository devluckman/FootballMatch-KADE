package com.man.hellosport.ui.schedule

import com.man.hellosport.model.Events

interface ScheduleInterface {
    fun showLoading()
    fun hideLoading()
    fun showEventList(data : List<Events>)
}