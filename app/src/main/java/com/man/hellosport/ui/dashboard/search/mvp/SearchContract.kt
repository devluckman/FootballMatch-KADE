package com.man.hellosport.ui.dashboard.search.mvp

import com.man.hellosport.model.event.Events

interface SearchContract {
    fun showLoading()
    fun hideLoading()
    fun showResult(list: List<Events>)
}