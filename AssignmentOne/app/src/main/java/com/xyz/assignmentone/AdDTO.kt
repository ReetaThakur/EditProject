package com.xyz.assignmentone

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class AdDTO(

	@field:SerializedName("company")
	val company: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("url")
	val url: String? = null
)