package com.example.covidhackapp.home

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.covidhackapp.R
import com.example.covidhackapp.chat.ChatActivity
import com.example.covidhackapp.counter.CounterActivity
import com.example.covidhackapp.instructions.InstructionsActivity
import com.example.covidhackapp.map.MapsRegistrationActivity
import com.example.covidhackapp.report.ReportActivity
import com.example.covidhackapp.states.StatesActivity
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        initLocationPermission()
        initBtns()
    }

    private fun initBtns(){
        btnReport.setOnClickListener {
            val intent = Intent(this, ReportActivity::class.java)
            startActivity(intent)
        }
        btnMap.setOnClickListener {
            val intent = Intent(this, MapsRegistrationActivity::class.java)
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

    private fun initLocationPermission(){
        if (ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ){
            ActivityCompat.requestPermissions(this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                1)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == 1){
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_DENIED)){
                Toast.makeText(this, "Please enable the location permission", Toast.LENGTH_SHORT).show()
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}
