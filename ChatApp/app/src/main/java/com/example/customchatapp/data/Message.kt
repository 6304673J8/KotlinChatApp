package com.example.customchatapp.data

import java.sql.Timestamp

class Message(
    val author: String? = null,
    val content: String? = null,
    val time: Long
)