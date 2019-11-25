package com.man.hellosport.model.event

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Events(
        @SerializedName("dateEvent") var dateEvent: String? = null,
        @SerializedName("strAwayTeam") var strAwayTeam: String?= null,
        @SerializedName("strHomeTeam") var strHomeTeam: String?= null,
        @SerializedName("idAwayTeam") var idAwayTeam: String? = null,
        @SerializedName("idEvent") var idEvent: String? = null,
        @SerializedName("idHomeTeam") var idHomeTeam: String?= null,
        @SerializedName("intAwayScore") var intAwayScore: String?= null,
        @SerializedName("intHomeScore") var intHomeScore: String?= null,
        @SerializedName("strAwayGoalDetails") var strAwayGoalDetails: String?= null,
        @SerializedName("strAwayLineupDefense") var strAwayLineupDefense: String?= null,
        @SerializedName("strAwayLineupForward") var strAwayLineupForward: String?= null,
        @SerializedName("strAwayLineupGoalkeeper") var strAwayLineupGoalkeeper: String?= null,
        @SerializedName("strAwayLineupMidfield") var strAwayLineupMidfield: String?= null,
        @SerializedName("strHomeGoalDetails") var strHomeGoalDetails: String?= null,
        @SerializedName("strHomeLineupDefense") var strHomeLineupDefense: String?= null,
        @SerializedName("strHomeLineupForward") var strHomeLineupForward: String?= null,
        @SerializedName("strHomeLineupGoalkeeper") var strHomeLineupGoalkeeper: String?= null,
        @SerializedName("strHomeLineupMidfield") var strHomeLineupMidfield: String?= null
) : Parcelable {
        override fun toString(): String {
                return "Events(dateEvent=$dateEvent, strAwayTeam=$strAwayTeam, strHomeTeam=$strHomeTeam, idAwayTeam=$idAwayTeam, idEvent=$idEvent, idHomeTeam=$idHomeTeam, intAwayScore=$intAwayScore, intHomeScore=$intHomeScore, strAwayGoalDetails=$strAwayGoalDetails, strAwayLineupDefense=$strAwayLineupDefense, strAwayLineupForward=$strAwayLineupForward, strAwayLineupGoalkeeper=$strAwayLineupGoalkeeper, strAwayLineupMidfield=$strAwayLineupMidfield, strHomeGoalDetails=$strHomeGoalDetails, strHomeLineupDefense=$strHomeLineupDefense, strHomeLineupForward=$strHomeLineupForward, strHomeLineupGoalkeeper=$strHomeLineupGoalkeeper, strHomeLineupMidfield=$strHomeLineupMidfield)"
        }
}