package com.example.customchatapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isGone
import com.example.customchatapp.data.Chat
import com.example.customchatapp.data.Message
import com.example.customchatapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var chat: Chat
    var author = "Sergi" //TODO

    //private val db = Firebase.database()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Database.openChat("0") {
            binding.progressBar.isGone = true
            if (it != null) {
                chat = it
            } else {
                val message = Message(author, "Tas bé?", 123L);
                val c = Chat("0", author, "Juls", "BirthDay", )

                //Database.createChat(c)
            }
        }
        //binding.sendButton.setOnClickListener {

        //}
    }
}
