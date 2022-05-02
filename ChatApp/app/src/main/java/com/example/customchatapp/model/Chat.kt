package com.example.customchatapp.model

data class Chat (
    val id: String? = null,
    val user1: String? = null,
    val user2: String? = null,
    val name: String? = null,
    val messages: ArrayList<Message> = arrayListOf()
)