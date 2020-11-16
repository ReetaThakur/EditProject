package com.xyz.assignmentone

sealed class UserUI {

    data class Success(val response: ResponseDTO) : UserUI()

    data class Failure(val throwable: Throwable) : UserUI()

}