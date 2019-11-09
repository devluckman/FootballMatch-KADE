package com.man.hellosport.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Events(
        @SerializedName("dateEvent") var dateEvent: String?,
        @SerializedName("strAwayTeam") var strAwayTeam: String?,
        @SerializedName("strHomeTeam") var strHomeTeam: String?,
        @SerializedName("idAwayTeam") var idAwayTeam: String,
        @SerializedName("idEvent") var idEvent: String,
        @SerializedName("idHomeTeam") var idHomeTeam: String,
        @SerializedName("idLeague") var idLeague: String?,
        @SerializedName("idSoccerXML") var idSoccerXML: String?,
        @SerializedName("intAwayScore") var intAwayScore: String?,
        @SerializedName("intHomeScore") var intHomeScore: String?,
        @SerializedName("intRound") var intRound: String?,
        @SerializedName("strAwayFormation") var strAwayFormation: String?,
        @SerializedName("strAwayGoalDetails") var strAwayGoalDetails: String?,
        @SerializedName("strAwayLineupDefense") var strAwayLineupDefense: String?,
        @SerializedName("strAwayLineupForward") var strAwayLineupForward: String?,
        @SerializedName("strAwayLineupGoalkeeper") var strAwayLineupGoalkeeper: String?,
        @SerializedName("strAwayLineupMidfield") var strAwayLineupMidfield: String?,
        @SerializedName("strHomeFormation") var strHomeFormation: String?,
        @SerializedName("strHomeGoalDetails") var strHomeGoalDetails: String?,
        @SerializedName("intHomeShots") var intHomeShots: String?,
        @SerializedName("strHomeLineupDefense") var strHomeLineupDefense: String?,
        @SerializedName("strHomeLineupForward") var strHomeLineupForward: String?,
        @SerializedName("strHomeLineupGoalkeeper") var strHomeLineupGoalkeeper: String?,
        @SerializedName("strHomeLineupMidfield") var strHomeLineupMidfield: String?
) : Parcelable