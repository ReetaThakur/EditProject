package com.xyz.mvvmarchitecture.model

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

	@field:SerializedName("support")
	val support: SupportModel? = null,

	@field:SerializedName("ad")
	val ad: AdModel? = null
)