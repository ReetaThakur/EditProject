package com.xyz.evaluations_week16

import com.google.gson.annotations.SerializedName

data class ResponseModel(

	@field:SerializedName("page")
	val page: Int? = null,

	@field:SerializedName("per_page")
	val perPage: Int? = null,

	@field:SerializedName("total")
	val total: Int? = null,

	@field:SerializedName("total_pages")
	val totalPages: Int? = null,

	@field:SerializedName("data")
	val data: List<DataModel?>? = null,

	@field:SerializedName("ad")
	val ad: AdModel? = null
)