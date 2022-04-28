package com.example.customchatapp

import android.widget.Toast
import com.example.customchatapp.data.Chat
import com.example.customchatapp.data.Message
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

object Database {
    private val db =
        Firebase.database("https://metachat-ba1eb-default-rtdb.europe-west1.firebasedatabase.app/")
            .getReference("chats")

    fun openChat(chatId: String, onResponse: (Chat?) -> Unit) {
        val request = db.child(chatId).get()

        request.addOnSuccessListener {
            try {
                val chat = it.getValue(Chat::class.java)
                onResponse(chat)
            } catch (e: Exception) {
                onResponse(null)
            }
        }

        request.addOnFailureListener() {
            onResponse(null)
        }
    }

    fun sendMessage(chat: Chat, text: String, author: String) {
        val message = Message(author, text, System.currentTimeMillis())

        chat.messages.add(message)

        chat.id?.let {
            db.child(it)
                .child("messages")
                .child((chat.messages.size - 1).toString())
                .setValue(message)
        }

    }
}