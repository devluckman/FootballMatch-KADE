package com.man.hellosport.ui.league.teams.mvp

import com.google.gson.Gson
import com.man.hellosport.data.network.ApiRepository
import com.man.hellosport.data.network.TheSportdbApi
import com.man.hellosport.model.teams.TeamRespone
import com.man.hellosport.utils.CoroutineContextProvider
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TeamsPresenter(private val apiRepository: ApiRepository,
                     private var view: TeamsInterface,
                     private val gson: Gson, private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getTeamsLeague(id : String){
        view.showLoading()
        GlobalScope.launch(context.main){
            val data = gson.fromJson(
                apiRepository.doRequestAsync(TheSportdbApi.getTeamsLeague(id)).await(),
                TeamRespone::class.java)
            view.let {
                it.hideLoading()
                it.showTeamList(data.teams)
            }

        }
    }

    fun getSearchTeams(query: String) {
        view.showLoading()
        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                apiRepository.doRequestAsync(TheSportdbApi.getSearchTeams(query)).await(),
                TeamRespone::class.java
            )
            view.hideLoading()

            if (!data.teams.isNullOrEmpty())
                view.showTeamList(data.teams)


        }
    }

}