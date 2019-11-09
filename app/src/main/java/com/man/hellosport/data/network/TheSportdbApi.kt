package com.man.hellosport.data.network

import com.man.hellosport.BuildConfig

object TheSportdbApi {

    fun getLastMatch(id: String): String {
        return BuildConfig.BASE_URL +
                "eventspastleague.php?id=$id"
    }

    fun getNextMatch(id: String): String {
        return BuildConfig.BASE_URL +
                "eventsnextleague.php?id=${id}"
    }
    fun getTeamDetail(id: String): String {
        return BuildConfig.BASE_URL +
                "lookupteam.php?id=$id"
    }
}