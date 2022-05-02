package com.example.customchatapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.customchatapp.model.Chat
import com.example.customchatapp.model.Message
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.*

class ChatsViewModel: ViewModel() {
    private val db =
        Firebase.database("https://metachat-ba1eb-default-rtdb.europe-west1.firebasedatabase.app/")
            .getReference("chats")

    var chat = MutableLiveData<Chat>()

    fun createChat(chat: Chat) {
        chat.id?.let { chatId ->
            db.child(chatId).setValue(chat)
        }
    }

    fun openChat(chatId: String, onResponse: (Chat?) -> Unit) {
        val request = db.child(chatId).get()

        request.addOnSuccessListener {
            chat.postValue(it.getValue(Chat::class.java))
        }
    }

    fun sendMessage(text: String, author: String) {
        val message = Message(author, text, System.currentTimeMillis())
        val cht = chat.value ?: return

        cht.messages.add(message)

        cht.let {
            db.child(cht.id ?: "0")
                .child("messages")
                .child((it.messages.size - 1).toString())
                .setValue(message)
        }
    }

    //We suscribe to the chat in case of creating a chat
    fun subscribe(id: String){
        db.child(id).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                chat.postValue(snapshot.getValue(Chat::class.java))
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}