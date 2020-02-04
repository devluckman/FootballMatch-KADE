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

    fun getLeagueDetail(id: String): String {
        return BuildConfig.BASE_URL +
                "lookupleague.php?id=$id"
    }

    fun getSearch(query: String): String {
        return BuildConfig.BASE_URL +
                "searchevents.php?e=$query"
    }

    fun getTeamsLeague(id: String): String {
        return BuildConfig.BASE_URL +
                "lookup_all_teams.php?id=$id"
    }

    fun getStandingLeague(id: String): String {
        return BuildConfig.BASE_URL +
                "lookuptable.php?l=$id"
    }

    fun getSearchTeams(query: String): String {
        return BuildConfig.BASE_URL +
                "searchteams.php?t=$query"
    }

}