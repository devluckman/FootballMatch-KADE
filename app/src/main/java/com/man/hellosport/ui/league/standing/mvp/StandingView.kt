package com.man.hellosport.ui.league.standing.mvp

import com.man.hellosport.model.standing.TableItem

interface StandingView {
    fun showLoadingStanding()
    fun hideLoadingStanding()
    fun showTableList(tableList: List<TableItem>)
}