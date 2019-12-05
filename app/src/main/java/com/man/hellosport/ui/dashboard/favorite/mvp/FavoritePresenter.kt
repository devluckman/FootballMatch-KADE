package com.man.hellosport.ui.dashboard.favorite.mvp

import android.content.Context
import com.man.hellosport.data.local.FavoriteMatch
import com.man.hellosport.data.local.FavoriteTeam
import com.man.hellosport.data.local.database
import com.man.hellosport.model.event.Events
import com.man.hellosport.utils.CoroutineContextProvider
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.db.rowParser
import org.jetbrains.anko.db.select

class FavoritePresenter(val view : FavoriteContract, val activity: Context,
                        private val context: CoroutineContextProvider = CoroutineContextProvider()
) {

    fun getFromLocalData(){
        view.showLoading()

        GlobalScope.launch(context.main){
            activity.database.use {
                val result = select(FavoriteMatch.TABLE_FAVORITE)
                val favorite = result.parseList(parser = rowParser {
                        eventid : String, dateEvent : String,
                        idHome : String, strHomeName : String, homeScore : String, homeGoals : String, homeGk : String, homeDef : String, homeMid : String, homeFor : String,
                        idAway : String, strAwayName : String, awayScore : String, awayGoals : String, awayGk : String, awayDef : String, awayMid : String, awayFor : String
                    ->
                    Events(
                        idEvent = eventid,
                        dateEvent = dateEvent,
                        idHomeTeam = idHome,
                        strHomeTeam = strHomeName,
                        intHomeScore = homeScore,
                        strHomeGoalDetails = homeGoals,
                        strHomeLineupGoalkeeper = homeGk,
                        strHomeLineupDefense = homeDef,
                        strHomeLineupMidfield = homeMid,
                        strHomeLineupForward = homeFor,
                        idAwayTeam = idAway,
                        strAwayTeam = strAwayName,
                        intAwayScore = awayScore,
                        strAwayGoalDetails = awayGoals,
                        strAwayLineupGoalkeeper = awayGk,
                        strAwayLineupDefense = awayDef,
                        strAwayLineupMidfield = awayMid,
                        strAwayLineupForward = awayFor
                    )
                })

                if (favorite.isNullOrEmpty()) {
                    view.hideLoading()
                    view.emptyData()
                } else {
                    view.hideLoading()
                    view.showEventList(favorite)
                }
            }
    }
    }

}