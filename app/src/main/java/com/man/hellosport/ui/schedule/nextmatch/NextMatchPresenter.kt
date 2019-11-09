package com.man.hellosport.ui.schedule.nextmatch

import android.content.Context
import com.google.gson.Gson
import com.man.hellosport.data.local.Favorite
import com.man.hellosport.data.local.database
import com.man.hellosport.data.network.ApiRepository
import com.man.hellosport.data.network.TheSportdbApi
import com.man.hellosport.model.EventsRespone
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class NextMatchPresenter(private val view : NextMatchContract) {
    fun getFromApi(id : String){
        view.showLoading()
        doAsync {
            val data = Gson().fromJson(
                ApiRepository().doRequest(TheSportdbApi.getNextMatch(id)),
                EventsRespone::class.java)
            uiThread {
                view.hideLoading()
                view.showEventList(data.events)
            }
        }
    }

    fun getFromLocal(context: Context){
        context.database.use {
            val result = select(Favorite.TABLE_FAVORITE)
            val favorite = result.parseList(classParser<Favorite>())
            view.showEventFromDb(favorite)
        }
    }
}