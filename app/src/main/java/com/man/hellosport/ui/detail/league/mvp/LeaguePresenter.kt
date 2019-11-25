package com.man.hellosport.ui.detail.league.mvp

import com.google.gson.Gson
import com.man.hellosport.data.network.ApiRepository
import com.man.hellosport.data.network.TheSportdbApi
import com.man.hellosport.model.league.LeagueResponse
import com.man.hellosport.utils.CoroutineContextProvider
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LeaguePresenter(val view : LeagueContract,
                      private val apiRepository: ApiRepository,
                      private val gson: Gson, private val context: CoroutineContextProvider = CoroutineContextProvider()
) {
    fun getLeagueDetail(idLeague: String) {
        GlobalScope.launch(context.main){
            val data = gson.fromJson(
                apiRepository.doRequestAsync(TheSportdbApi.getLeagueDetail(idLeague)).await(),
                LeagueResponse::class.java)
            view.showLeaguedetails(data.leagues)

        }
    }
}