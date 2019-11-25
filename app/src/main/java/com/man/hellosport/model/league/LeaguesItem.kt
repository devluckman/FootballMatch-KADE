package com.man.hellosport.model.league

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LeaguesItem(
	val dateFirstEvent: String? = null,
	val intFormedYear: String? = null,
	val strBanner: String? = null,
	val strSport: String? = null,
	val strDescriptionEN: String? = null,
	val strWebsite: String? = null,
	val strYoutube: String? = null,
	val idCup: String? = null,
	val strComplete: String? = null,
	val strLocked: String? = null,
	val idLeague: String,
	val idSoccerXML: String? = null,
	val strTrophy: String? = null,
	val strBadge: String? = null,
	val strTwitter: String? = null,
	val strGender: String? = null,
	val strLeagueAlternate: String? = null,
	val strNaming: String? = null,
	val strDivision: String? = null,
	val strFanart1: String? = null,
	val strFanart2: String? = null,
	val strFanart3: String? = null,
	val strFacebook: String? = null,
	val strFanart4: String? = null,
	val strCountry: String? = null,
	val strRSS: String? = null,
	val strLogo: String? = null,
	val strLeague: String? = null,
	val strPoster: String? = null
) : Parcelable
