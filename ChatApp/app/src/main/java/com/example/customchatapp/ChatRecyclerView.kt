package com.example.customchatapp

import android.text.Layout
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.customchatapp.databinding.ItemMessageReceivedBinding
import com.example.customchatapp.databinding.ItemMessageSentBinding
import com.example.customchatapp.model.Chat

class ChatRecyclerView(val author: String): RecyclerView.Adapter<ChatRecyclerView.ViewHolder>() {

    var chat: Chat? = null

    inner class ViewHolder(view: View, type: Int): RecyclerView.ViewHolder(view){
        val message: TextView = if (type == SENT_MESSAGE)
            ItemMessageSentBinding.bind(view).messageSent
        else
            ItemMessageReceivedBinding.bind(view).messageReceived
    }

    companion object{
        const val SENT_MESSAGE = 0
        const val RECEIVED_MESSAGE = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val view = if(viewType == SENT_MESSAGE)
            layoutInflater.inflate(R.layout.item_message_sent, parent, false)
        else
            layoutInflater.inflate(R.layout.item_message_received, parent, false)
        return(ViewHolder(view, viewType))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.message.text = chat?.messages?.get(position)?.content ?: ""
    }

    override fun getItemViewType(position: Int): Int {
        return if(chat?.messages?.get(position)?.author == author) SENT_MESSAGE else RECEIVED_MESSAGE
    }

    override fun getItemCount(): Int {
        return chat?.messages?.size ?: 0
    }
}