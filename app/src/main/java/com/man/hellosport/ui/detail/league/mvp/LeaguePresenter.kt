package com.man.hellosport.ui.detail.league.mvp

import com.google.gson.Gson
import com.man.hellosport.data.network.ApiRepository
import com.man.hellosport.data.network.TheSportdbApi
import com.man.hellosport.model.league.LeagueResponse
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class LeaguePresenter(val view : LeagueContract) {
    fun getLeagueDetail(idLeague: String) {
        doAsync {
            val data = Gson().fromJson(
                ApiRepository()
                    .doRequest(TheSportdbApi.getLeagueDetail(idLeague)),
                LeagueResponse::class.java)

            uiThread {
                view.showLeaguedetails(data.leagues[0])
            }
        }
    }
}