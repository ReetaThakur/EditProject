package com.xyz.assignmentone

import com.google.gson.annotations.SerializedName

data class SupportDTO(

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("text")
	val text: String? = null
)