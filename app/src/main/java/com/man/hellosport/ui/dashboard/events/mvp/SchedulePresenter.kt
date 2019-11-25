package com.man.hellosport.ui.dashboard.events.mvp

import com.google.gson.Gson
import com.man.hellosport.data.network.ApiRepository
import com.man.hellosport.data.network.TheSportdbApi
import com.man.hellosport.model.event.EventsRespone
import com.man.hellosport.utils.CoroutineContextProvider
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

open class SchedulePresenter(private val apiRepository: ApiRepository,
                             private var view: ScheduleInterface,
                             private val gson: Gson, private val context: CoroutineContextProvider = CoroutineContextProvider()
) {

    fun getNextMatch(id : String){
        view.showLoading()
        GlobalScope.launch(context.main){
            val data = gson.fromJson(
                apiRepository.doRequestAsync(TheSportdbApi.getNextMatch(id)).await(),
                EventsRespone::class.java)
            view.let {
                it.hideLoading()
                it.showEventList(data.events)
            }

        }
    }

    fun getLastMatch(id : String){
        view.showLoading()
        GlobalScope.launch(context.main){
            val data = gson.fromJson(
                apiRepository.doRequestAsync(TheSportdbApi.getLastMatch(id)).await(),
                EventsRespone::class.java)
            view.let {
                it.hideLoading()
                it.showEventList(data.events)
            }

        }
    }

}