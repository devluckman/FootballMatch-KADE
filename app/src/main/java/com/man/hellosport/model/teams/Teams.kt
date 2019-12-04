package com.man.hellosport.model.teams

import com.google.gson.annotations.SerializedName

data class Teams (

        @SerializedName("idTeam")
        var idTeam: String?,
        @SerializedName("strTeam")
        var strTeam: String?,
        @SerializedName("strTeamBadge")
        var strTeamBadge: String,

        var intStadiumCapacity: String? = null,
        var strTeamShort: Any? = null,
        var strSport: String? = null,
        var strDescriptionCN: Any? = null,
        var strTeamJersey: String? = null,
        var strTeamFanart2: String? = null,
        var strTeamFanart3: String? = null,
        var strTeamFanart4: String? = null,
        var strStadiumDescription: String? = null,
        var strTeamFanart1: String? = null,
        var intLoved: Any? = null,
        var idLeague: String? = null,
        var idSoccerXML: String? = null,
        var strTeamLogo: String? = null,
        var strDescriptionSE: Any? = null,
        var strDescriptionJP: Any? = null,
        var strDescriptionFR: Any? = null,
        var strStadiumLocation: String? = null,
        var strDescriptionNL: Any? = null,
        var strCountry: String? = null,
        var strRSS: String? = null,
        var strDescriptionRU: Any? = null,
        var strTeamBanner: String? = null,
        var strDescriptionNO: Any? = null,
        var strStadiumThumb: String? = null,
        var strDescriptionES: String? = null,
        var intFormedYear: String? = null,
        var strInstagram: String? = null,
        var strDescriptionIT: Any? = null,
        var strDescriptionEN: String? = null,
        var strWebsite: String? = null,
        var strYoutube: String? = null,
        var strDescriptionIL: Any? = null,
        var strLocked: String? = null,
        var strAlternate: String? = null,
        var strTwitter: String? = null,
        var strDescriptionHU: Any? = null,
        var strGender: String? = null,
        var strDivision: Any? = null,
        var strStadium: String? = null,
        var strFacebook: String? = null,
        var strDescriptionPT: Any? = null,
        var strDescriptionDE: Any? = null,
        var strLeague: String? = null,
        var strManager: String? = null,
        var strKeywords: String? = null,
        var strDescriptionPL: Any? = null

)