
package com.capstone.hibykes.data.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class AirPollutionResponse(

	@field:SerializedName("coord")
	val coord: CoordPollution? = null,

	@field:SerializedName("list")
	val list: List<ListItem?>? = null
) : Parcelable

@Parcelize
data class MainPollution(

	@field:SerializedName("aqi")
	val aqi: Int? = null
) : Parcelable

@Parcelize
data class CoordPollution(

	@field:SerializedName("lon")
	val lon: Double? = null,

	@field:SerializedName("lat")
	val lat: Double? = null
) : Parcelable

@Parcelize
data class Components(

	@field:SerializedName("no2")
	val no2: Double? = null,

	@field:SerializedName("no")
	val no: Double? = null,

	@field:SerializedName("o3")
	val o3: Double? = null,

	@field:SerializedName("so2")
	val so2: Double? = null,

	@field:SerializedName("pm2_5")
	val pm25: Double? = null,
