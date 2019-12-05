package com.man.hellosport.ui.league.favorite.mvp

import android.content.Context
import com.man.hellosport.data.local.FavoriteTeam
import com.man.hellosport.data.local.database
import com.man.hellosport.model.teams.Teams
import com.man.hellosport.utils.CoroutineContextProvider
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.db.rowParser
import org.jetbrains.anko.db.select

class TeamFavoritePresenter (val view : TeamFavoriteView, val activity: Context,
                             private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getFromLocalData(){
        view.showLoading()
        GlobalScope.launch(context.main){
            activity.database.use {
                val result = select(FavoriteTeam.TABLE_TEAMS)
                val favorite = result.parseList(parser = rowParser {
                        idTeam : String,
                        strTeam : String,
                        strTeamBadge : String,
                        strCountry : String,
                        strFacebook : String,
                        strInstagram : String,
                        strYoutube : String,
                        strWebsite : String,
                        strTwitter : String,
                        strDescriptionEN : String,
                        strStadium : String
                    -> Teams(
                        idTeam = idTeam,
                        strTeam = strTeam,
                        strTeamBadge = strTeamBadge,
                        strCountry = strCountry,
                        strInstagram = strInstagram,
                        strWebsite = strWebsite,
                        strYoutube = strYoutube,
                        strTwitter = strTwitter,
                        strFacebook = strFacebook,
                        strDescriptionEN = strDescriptionEN,
                        strStadium = strStadium)
                })

                if (favorite.isNullOrEmpty()) {
                    view.hideLoading()
                    view.emptyData()
                } else {
                    view.hideLoading()
                    view.showTeamListFavorite(favorite)
                }
            }
        }
    }

}