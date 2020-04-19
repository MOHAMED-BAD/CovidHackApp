package com.example.covidhackapp.counter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.covidhackapp.R
import com.google.maps.android.data.geojson.GeoJsonLayer
import kotlinx.android.synthetic.main.activity_counter.*
import java.text.SimpleDateFormat
import java.util.*

class CounterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_counter)

        initBtns()

    }

    private fun initBtns() {
        btnStart.setOnClickListener {
            val calender = Calendar.getInstance()
            val startDate = calender.time
            val dateFormat = SimpleDateFormat("dd-MMM-yyyy")
            val formattedStartDate = dateFormat.format(startDate)
            tvDayOfStartDate.text = formattedStartDate

            val calender2 = calender.apply { add(Calendar.DAY_OF_YEAR, +14) }
            val endDate = calender2.time
            val formattedEndDate = dateFormat.format(endDate)
            tvDayOfEndDate.text = formattedEndDate

        }
        
    }
}
