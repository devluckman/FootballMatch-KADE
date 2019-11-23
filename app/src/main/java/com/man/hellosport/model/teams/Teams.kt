package com.man.hellosport.model.teams

import com.google.gson.annotations.SerializedName

data class Teams (

        @SerializedName("idTeam")
        var idTeam: String?,
        @SerializedName("strTeam")
        var strTeam: String?,
        @SerializedName("strTeamBadge")
        var strTeamBadge: String

)