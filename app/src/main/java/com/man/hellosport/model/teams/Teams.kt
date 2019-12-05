package com.man.hellosport.model.teams

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Teams (

        @SerializedName("idTeam")
        var idTeam: String?,
        @SerializedName("strTeam")
        var strTeam: String?,
        @SerializedName("strTeamBadge")
        var strTeamBadge: String,

        var strCountry: String? = null,

        var strInstagram: String? = null,
        var strWebsite: String? = null,
        var strYoutube: String? = null,
        var strTwitter: String? = null,
        var strFacebook: String? = null,

        var strDescriptionEN: String? = null,
        var strStadium: String? = null


) : Parcelable