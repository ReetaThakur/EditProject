package com.xyz.mvvmarchitecture.model

import com.google.gson.annotations.SerializedName

data class AdModel(

	@field:SerializedName("company")
	val company: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("url")
	val url: String? = null
)