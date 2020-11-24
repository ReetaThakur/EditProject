package com.xyz.firebasechat.model

data class Chat(
    val message: String, val timeStamp: String, val sender: String, val receiver: String) {
    constructor() : this("a", "b","","")
}