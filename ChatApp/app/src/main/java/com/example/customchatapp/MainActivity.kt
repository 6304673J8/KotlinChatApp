package com.example.customchatapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isGone
import com.example.customchatapp.model.Chat
import com.example.customchatapp.model.Message
import com.example.customchatapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var chat: Chat
    lateinit var adapter: ChatRecyclerView

    val chatsViewModel: ChatsViewModel by viewModels()
    var author = "Sergi" //TODO

    //private val db = Firebase.database()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = ChatRecyclerView(author)
        binding.messagesList.adapter = adapter

        chatsViewModel.openChat("0") {
            binding.progressBar.isGone = true

            if (it != null) {
                chat = it
                adapter.updateChat(chat)
            } else {
                val message = Message(author, "Tas bé?", 123L);
                chat = Chat("0", author, "Juls", "BirthDay", arrayListOf(message))
                adapter.updateChat(chat)
                ChatsViewModel.createChat(c)
            }
        }
        binding.sendButton.setOnClickListener {
            val text = binding.messageInput.text
            adapter.notifyDataSetChanged()

            if (!text.isNullOrEmpty()) {
                ChatsViewModel.sendMessage(chat, text.toString(), author)
                binding.messageInput.setText("")
            }
        }
    }
}
