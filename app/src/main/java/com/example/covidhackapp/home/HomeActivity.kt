package com.example.covidhackapp.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.covidhackapp.R
import com.example.covidhackapp.chat.ChatActivity
import com.example.covidhackapp.counter.CounterActivity
import com.example.covidhackapp.instructions.InstructionsActivity
import com.example.covidhackapp.map.MapActivity
import com.example.covidhackapp.report.ReportActivity
import com.example.covidhackapp.states.StatesActivity
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        initBtns()
    }

    private fun initBtns(){
        btnReport.setOnClickListener {
            val intent = Intent(this, ReportActivity::class.java)
            startActivity(intent)
        }
        btnMap.setOnClickListener {
            val intent = Intent(this, MapActivity::class.java)
            startActivity(intent)
        }
        btnChat.setOnClickListener {
            val intent = Intent(this, ChatActivity::class.java)
            startActivity(intent)
        }
        btnCounter.setOnClickListener {
            val intent = Intent(this, CounterActivity::class.java)
            startActivity(intent)
        }
        btnStates.setOnClickListener {
            val intent = Intent(this, StatesActivity::class.java)
            startActivity(intent)
        }
        btnInstructions.setOnClickListener {
            val intent = Intent(this, InstructionsActivity::class.java)
            startActivity(intent)
        }
    }
}
