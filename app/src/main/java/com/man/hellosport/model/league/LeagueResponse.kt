package com.man.hellosport.model.league

import com.google.gson.annotations.SerializedName

data class LeagueResponse(
	@SerializedName("leagues")
	val leagues: List<LeaguesItem>? = null
)