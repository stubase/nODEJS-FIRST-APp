
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

	@field:SerializedName("pm10")
	val pm10: Double? = null,

	@field:SerializedName("nh3")
	val nh3: Double? = null,

	@field:SerializedName("co")
	val co: Double? = null
) : Parcelable

@Parcelize
data class ListItem(

	@field:SerializedName("dt")
	val dt: Int? = null,

	@field:SerializedName("components")
	val components: Components? = null,

	@field:SerializedName("main")
	val main: MainPollution? = null
) : Parcelable