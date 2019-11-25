package com.man.hellosport.ui.detail.events

import com.google.gson.Gson
import com.man.hellosport.data.network.ApiRepository
import com.man.hellosport.data.network.TheSportdbApi
import com.man.hellosport.model.teams.TeamRespone
import com.man.hellosport.utils.CoroutineContextProvider
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class EventsPresenter(val view : EventsContract,
                      private val apiRepository: ApiRepository,
                      private val gson: Gson, private val context: CoroutineContextProvider = CoroutineContextProvider()
) {
    fun getTeamDetail(idHomeTeam: String?, idAwayTeam: String?) {
        GlobalScope.launch(context.main){
            val dataHomeTeam = gson.fromJson(
                apiRepository.doRequestAsync(TheSportdbApi.getTeamDetail(idHomeTeam.toString())).await(),
                TeamRespone::class.java)

            val dataAwayHome = gson.fromJson(
                apiRepository.doRequestAsync(TheSportdbApi.getTeamDetail(idAwayTeam.toString())).await(),
                TeamRespone::class.java)

            view.showTeamdetails(dataHomeTeam.teams, dataAwayHome.teams)
        }
    }
}