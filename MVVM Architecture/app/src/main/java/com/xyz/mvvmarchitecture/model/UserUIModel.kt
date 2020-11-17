package com.xyz.mvvmarchitecture.model

sealed class UserUIModel {

    data class Success(val dataModelList: List<DataModel>) : UserUIModel()

    data class Failure(val error: String) : UserUIModel()
}