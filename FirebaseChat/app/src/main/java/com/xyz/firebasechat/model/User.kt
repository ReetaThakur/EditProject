package com.xyz.firebasechat.model

data class User(val name: String, val userId: String) {
    constructor() : this("", "")
}