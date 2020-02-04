package com.man.hellosport.ui.league.standing.mvp

import com.google.gson.Gson
import com.man.hellosport.data.network.ApiRepository
import com.man.hellosport.data.network.TheSportdbApi
import com.man.hellosport.model.standing.StandingResponse
import com.man.hellosport.utils.CoroutineContextProvider
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class StandingPresenter(val view : StandingView,
                        private val apiRepository: ApiRepository,
                        private val gson: Gson,
                        private val context: CoroutineContextProvider = CoroutineContextProvider()
) {

    fun getStandingLeague(idLeague : String){
        view.showLoadingStanding()
        GlobalScope.launch(context.main){
            val data = gson.fromJson(
                apiRepository.doRequestAsync(TheSportdbApi.getStandingLeague(idLeague)).await(),
                StandingResponse::class.java)
            view.hideLoadingStanding()
            view.showTableList(data.table)

        }
    }

}