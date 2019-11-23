package com.man.hellosport.model.league

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LeaguesItem(

	@field:SerializedName("strLogo")
	val strLogo: String? = null,

	@field:SerializedName("strLeague")
	val strLeague: String? = null,

	@field:SerializedName("idLeague")
	val idLeague: String? = null
) : Parcelable