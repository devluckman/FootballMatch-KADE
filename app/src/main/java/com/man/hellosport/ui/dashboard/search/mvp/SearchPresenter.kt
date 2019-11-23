package com.man.hellosport.ui.dashboard.search.mvp

import com.google.gson.Gson
import com.man.hellosport.data.network.ApiRepository
import com.man.hellosport.data.network.TheSportdbApi
import com.man.hellosport.model.search.SearchResponse
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class SearchPresenter(private val view : SearchContract) {

    fun getSearchMatch(query: String) {
        view.showLoading()
        try {
            doAsync {
                val data = Gson().fromJson(
                    ApiRepository()
                        .doRequest(TheSportdbApi.getSearch(query)),
                    SearchResponse::class.java)

                uiThread {
                    view.hideLoading()
                    view.showResult(data.event)
                }
            }
        }catch (e : Exception){
            view.hideLoading()
        }
    }
}