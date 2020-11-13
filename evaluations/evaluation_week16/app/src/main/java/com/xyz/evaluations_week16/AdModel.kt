package com.xyz.evaluations_week16

import com.google.gson.annotations.SerializedName

data class AdModel(

	@field:SerializedName("company")
	val company: String? = null,

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("text")
	val text: String? = null
)