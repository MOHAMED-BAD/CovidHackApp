package com.example.covidhackapp.report

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import com.example.covidhackapp.R
import kotlinx.android.synthetic.main.activity_report.*

class ReportActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report)

        initRadioTemp()
    }

    private fun initRadioTemp() {
        radioTemp.setOnCheckedChangeListener { group, checkedId ->
            val radioSelected = findViewById<RadioButton>(checkedId)
            if (radioSelected.text == "Yes") {
                layoutTemp.visibility = View.VISIBLE
                tvTempRange.visibility = View.GONE
                radioTempRange.visibility = View.GONE
            } else if (radioSelected.text == "No") {
                layoutTemp.visibility = View.GONE
                tvTempRange.visibility = View.VISIBLE
                radioTempRange.visibility = View.VISIBLE
            }
        }

    }
}
