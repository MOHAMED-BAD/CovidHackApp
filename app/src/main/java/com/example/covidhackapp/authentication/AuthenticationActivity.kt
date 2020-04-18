package com.example.covidhackapp.authentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.covidhackapp.R
import kotlinx.android.synthetic.main.activity_authentication.*

class AuthenticationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authentication)

        initBtns()
    }

    private fun initBtns(){
        btnNext.setOnClickListener {
            val number = etPhoneNumber.text.toString().removePrefix("0")
            val countryCode = ccp.selectedCountryCode

            val intent = Intent(this, VerificationActivity::class.java)
            intent.putExtra("Phone number", countryCode+number)

            startActivity(intent)

        }
    }
}
