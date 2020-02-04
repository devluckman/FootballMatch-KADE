package com.man.hellosport.model.standing

import com.google.gson.annotations.SerializedName

data class StandingResponse(

	@field:SerializedName("table")
	val table: List<TableItem>
)