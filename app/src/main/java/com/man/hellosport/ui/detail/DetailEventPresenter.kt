package com.man.hellosport.ui.detail

import com.google.gson.Gson
import com.man.hellosport.data.network.ApiRepository
import com.man.hellosport.data.network.TheSportdbApi
import com.man.hellosport.model.TeamRespone
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class DetailEventPresenter(val view : DetailEventContract) {
    fun getTeamDetail(idHomeTeam: String?, idAwayTeam: String?) {
        view.showLoading()

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
                view.hideLoading()
                view.showTeamdetails(dataHomeTeam.teams!!, dataAwayHome.teams!!)
            }
        }

    }
}