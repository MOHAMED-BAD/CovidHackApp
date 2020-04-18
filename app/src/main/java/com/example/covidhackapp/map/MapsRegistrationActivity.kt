package com.example.covidhackapp.map

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.example.covidhackapp.R

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import kotlinx.android.synthetic.main.activity_maps_registration.*
import mumayank.com.airlocationlibrary.AirLocation

class MapsRegistrationActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private var airLocation: AirLocation? = null
    private var currentLocation: LatLng? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps_registration)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        confirm_button.setOnClickListener {
          val resultIntent = Intent().apply {
               putExtra("Location",currentLocation)
           }
            setResult(Activity.RESULT_OK,resultIntent)
            finish()
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            mMap.isMyLocationEnabled = true
        } else {
            // Show rationale and request permission.
        }

        airLocation = AirLocation(this, true, true, object : AirLocation.Callbacks {
            override fun onSuccess(location: Location) {
                // location fetched successfully, proceed with it
                val latLng = LatLng(
                    location.latitude,
                    location.longitude
                )
                if (currentLocation == null)
                    currentLocation = latLng

                mMap.animateCamera(
                    CameraUpdateFactory.newLatLngZoom(
                        latLng,15f
                    )
                )


                confirm_button.isEnabled = true
            }

            override fun onFailed(locationFailedEnum: AirLocation.LocationFailedEnum) {
                // couldn't fetch location due to reason available in locationFailedEnum
                // you may optionally do something to inform the user, even though the reason may be obvious
//                toast(locationFailedEnum.name)
            }

        })
        // Add a marker in Sydney and move the camera
        mMap.setOnCameraIdleListener {
            confirm_button.isEnabled = true
            currentLocation = mMap.cameraPosition.target

        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        airLocation?.onActivityResult(
            requestCode,
            resultCode,
            data
        ) // ADD THIS LINE INSIDE onActivityResult
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        airLocation?.onRequestPermissionsResult(
            requestCode,
            permissions,
            grantResults
        ) // ADD THIS LINE INSIDE onRequestPermissionResult
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}
