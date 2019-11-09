package com.man.hellosport.data.local


class Favorite(
    val idEvent: String,
    val dateEvent: String?,
    val strHomeTeam: String?,
    val intHomeScore: String?,
    val strHomeGoalDetails: String?,
    val strHomeLineupDefense: String?,
    val strHomeLineupForward: String?,
    val strHomeLineupGoalkeeper: String?,
    val strHomeLineupMidfield: String?,
    val strTeamBadgeHome: String?,
    val strAwayTeam: String?,
    val intAwayScore: String?,
    val strAwayGoalDetails: String?,
    val strAwayLineupDefense: String?,
    val strAwayLineupForward: String?,
    val strAwayLineupGoalkeeper: String?,
    val strAwayLineupMidfield: String?,
    val strTeamBadgeAway: String?

) {
    companion object{
        const val TABLE_FAVORITE = "TABLE_FAVORITE"
        const val ID = "ID_EVENT"
        const val DATE_EVENT = "DATE_EVENT"
        const val HOME_TEAM_NAME =  "HOME_TEAM_NAME"
        const val HOME_TEAM_SCORE =  "HOME_TEAM_SCORE"
        const val HOME_TEAM_GOALS = "HOME_TEAM_GOALS"
        const val HOME_TEAM_GK =  "HOME_TEAM_GK"
        const val HOME_TEAM_DEF = "HOME_TEAM_DEF"
        const val HOME_TEAM_MID =  "HOME_TEAM_MID"
        const val HOME_TEAM_FORW =  "HOME_TEAM_FORW"
        const val HOME_TEAM_BADGE =  "HOME_TEAM_BADGE"
        const val AWAY_TEAM_NAME =  "AWAY_TEAM_NAME"
        const val AWAY_TEAM_SCORE =  "AWAY_TEAM_SCORE"
        const val AWAY_TEAM_GOALS =   "AWAY_TEAM_GOALS"
        const val AWAY_TEAM_GK =  "AWAY_TEAM_GK"
        const val AWAY_TEAM_DEF =  "AWAY_TEAM_DEF"
        const val AWAY_TEAM_MID =  "AWAY_TEAM_MID"
        const val AWAY_TEAM_FORW =  "AWAY_TEAM_FORW"
        const val AWAY_TEAM_BADGE =  "AWAY_TEAM_BADGE"

    }
}