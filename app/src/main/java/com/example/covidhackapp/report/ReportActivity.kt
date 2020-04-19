package com.example.covidhackapp.report

import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.covidhackapp.R
import com.example.covidhackapp.chat.ChatActivity
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_report.*
import mumayank.com.airlocationlibrary.AirLocation

class ReportActivity : AppCompatActivity() {

    private lateinit var mFirebaseFireStore: FirebaseFirestore

    private lateinit var mFusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report)

        initLocationPermission()
        initBtns()
    }

    private fun initBtns() {
        var temp = ""
        var i = 0
        var j = 0

        radioTemp.setOnCheckedChangeListener { group, checkedId ->
            val radioSelected = findViewById<RadioButton>(checkedId)
            if (radioSelected.text == "Yes"){
                radioTempYes()
                i=1
            }else if (radioSelected.text == "No"){
                radioTempNo()
                i = 2
            }
        }

        radioTempRange.setOnCheckedChangeListener { group, checkedId ->
            j =checkedId
        }

        btnSendReport.setOnClickListener {
            if (i == 1){
                temp = etTemp.text.toString()
            }else if (i == 2){
                temp = findViewById<RadioButton>(j).text.toString()
            }
            val name = etName.text.toString()
            val age = etAge.text.toString()
            val symptoms = getSymptoms()
            val extraSymptoms = etExtraSymptoms.text.toString()

            if (name.isEmpty() || age.isEmpty() || temp == ""){
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }else{
                val intent = Intent(this, ChatActivity::class.java)
                intent.putExtra("name", name)
                intent.putExtra("age", age)
                intent.putExtra("symptoms", symptoms)
                if (extraSymptoms.isNotEmpty()){
                    intent.putExtra("extraSymptoms", extraSymptoms)
                }
                intent.putExtra("temp", temp)

            getCurrentLocation()

                startActivity(intent)
            }
        }
    }

    private fun getSymptoms(): String {
        val symptoms = StringBuffer()
        symptoms.append("Fever check: ").append(chkFever.isChecked)
        symptoms.append("\n")
        symptoms.append("Dry cough check: ").append(chkDryCough.isChecked)
        symptoms.append("\n")
        symptoms.append("Shortness of breath check: ").append(chkShortBreath.isChecked)
        symptoms.append("\n")
        symptoms.append("Sore throat check: ").append(chkSoreThroat.isChecked)
        symptoms.append("\n")
        symptoms.append("Tiredness check: ").append(chkTiredness.isChecked)
        symptoms.append("\n")
        symptoms.append("Diarrhea check: ").append(chkDiarrhea.isChecked)
        symptoms.append("\n")
        symptoms.append("Nasal congestion check: ").append(chkNasalCongestion.isChecked)
        symptoms.append("\n")
        symptoms.append("Runny nose check: ").append(chkRunnyNose.isChecked)

        return symptoms.toString()
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

    private fun radioTempYes(){
        layoutTemp.visibility = View.VISIBLE
        tvTempRange.visibility = View.GONE
        radioTempRange.visibility = View.GONE
    }

    private fun radioTempNo(){
        layoutTemp.visibility = View.GONE
        tvTempRange.visibility = View.VISIBLE
        radioTempRange.visibility = View.VISIBLE
    }

    private fun getCurrentLocation(){
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        mFusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location ->
                val currentLocation = LatLng(
                    location.latitude,
                    location.longitude
                )
                val locationsMap = HashMap<String, LatLng?>()
                locationsMap["Location"] = currentLocation

                mFirebaseFireStore = FirebaseFirestore.getInstance()
                mFirebaseFireStore.collection("Locations").add(locationsMap)
            }
    }
}
