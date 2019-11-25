package com.man.hellosport.ui.detail.events

import com.google.gson.Gson
import com.man.hellosport.data.network.ApiRepository
import com.man.hellosport.data.network.TheSportdbApi
import com.man.hellosport.model.teams.TeamRespone
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class EventsPresenter(val view : EventsContract) {
    fun getTeamDetail(idHomeTeam: String?, idAwayTeam: String?) {
        doAsync {
            val dataHomeTeam = Gson().fromJson(
                ApiRepository()
                    .doRequest(TheSportdbApi.getTeamDetail(idHomeTeam.toString())),
                TeamRespone::class.java)

            val dataAwayHome = Gson().fromJson(
                ApiRepository()
                    .doRequest(TheSportdbApi.getTeamDetail(idAwayTeam.toString())),
                TeamRespone::class.java)

            uiThread {
                view.showTeamdetails(dataHomeTeam.teams, dataAwayHome.teams)
            }
        }

    }
}