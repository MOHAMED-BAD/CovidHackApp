package com.example.covidhackapp.chat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.covidhackapp.R
import kotlinx.android.synthetic.main.activity_chat.*

class ChatActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        val message = receiveText()
        etMessage.setText(message)
    }

    private fun receiveText(): String {
        val name = intent.getStringExtra("name")
        val age = intent.getStringExtra("age")
        val symptoms = intent.getStringExtra("symptoms")
        val extraSymptoms = intent.getStringExtra("extraSymptoms")
        val temp = intent.getStringExtra("temp")

        var message = ""
        if (extraSymptoms == null) {
            message = "Name: $name \nAge: $age \n$symptoms \nTemp: $temp"
        }else{
            message = "Name: $name \nAge: $age \n$symptoms \nExtra symptoms: $extraSymptoms \nTemp: $temp"
        }
        return message
    }
}
