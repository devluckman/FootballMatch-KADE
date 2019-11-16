package com.man.hellosport.ui.schedule

import com.google.gson.Gson
import com.man.hellosport.data.network.ApiRepository
import com.man.hellosport.data.network.TheSportdbApi
import com.man.hellosport.model.EventsRespone
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

open class SchedulePresenter(private val apiRepository: ApiRepository) {

    private var view: ScheduleInterface? = null

    fun getNextMatch(id : String){
        view!!.showLoading()
        doAsync {
            val data = Gson().fromJson(
                apiRepository.doRequest(TheSportdbApi.getNextMatch(id)),
                EventsRespone::class.java)
            uiThread {
                view?.let {
                    it.hideLoading()
                    it.showEventList(data.events)
                }
            }
        }
    }

    fun getLastMatch(id : String){
        view!!.showLoading()
        doAsync {
            val data = Gson().fromJson(
                apiRepository.doRequest(TheSportdbApi.getLastMatch(id)),
                EventsRespone::class.java)
            uiThread {
                view?.let {
                    it.hideLoading()
                    it.showEventList(data.events)
                }
            }
        }
    }


    fun subscribe(view: ScheduleInterface) {
        this@SchedulePresenter.view = view
    }

    fun unsubscribe() {
        this@SchedulePresenter.view = null
    }


}