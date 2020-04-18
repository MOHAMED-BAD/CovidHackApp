package com.example.covidhackapp.authentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.covidhackapp.R
import com.example.covidhackapp.home.HomeActivity
import kotlinx.android.synthetic.main.activity_verification.*

class VerificationActivity : AppCompatActivity() {

    private var phoneNumber: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verification)

        phoneNumber = intent.getStringExtra("Phone number")
        phoneNumberTV.text = phoneNumber

        iniBtns()
    }

    private fun iniBtns(){
        btnGo.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
    }
}
