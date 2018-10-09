package com.syana.saudi.syanh

import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.widget.Toast

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.lang.Exception

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        checkPermissions()
    }

    val acessLocation = 123
    fun checkPermissions(){
        if (Build.VERSION.SDK_INT >= 23){

            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
                requestPermissions(arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), acessLocation)
                return
            }

        }

        getUserLocation()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            acessLocation->{
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this,"location access is granted!", Toast.LENGTH_LONG).show()
                    getUserLocation()
                }else{
                    Toast.makeText(this,"location access is denied!", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun getUserLocation() {
        val myLocation = MyLocationListener()
        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3, 3f , myLocation)

        val locationUpdaterThread = LocationUpdaterThread()
        locationUpdaterThread.start()
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

        /*// Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney)
                .title("Marker in Sydney")
                .snippet("her is me")
                //.icon(BitmapDescriptorFactory.fromResource(R.drawable.memarker))
        )
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 14f))*/
    }

    var myLocation:Location? = null

    inner class MyLocationListener : LocationListener{

        constructor(){
            myLocation = Location("me")
            myLocation!!.latitude = 50.0
            myLocation!!.longitude = 150.0
        }

        override fun onLocationChanged(location: Location?) {
            myLocation = location
        }

        override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {

        }

        override fun onProviderEnabled(p0: String?) {

        }

        override fun onProviderDisabled(p0: String?) {

        }

    }

    inner class LocationUpdaterThread: Thread{

        constructor(){

        }

        override fun run() {

            while (true){
                try {
                    Toast.makeText(this@MapsActivity, "inside while", Toast.LENGTH_LONG).show()

                    mMap.clear()
                    runOnUiThread {

                        val sydney = LatLng(myLocation!!.altitude, myLocation!!.longitude)
                        mMap.addMarker(MarkerOptions().position(sydney)
                                .title("Marker in Sydney")
                                .snippet("her is me")
                                //.icon(BitmapDescriptorFactory.fromResource(R.drawable.memarker))
                        )
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 14f))

                    }


                    Thread.sleep(100)
                }catch (e: Exception){

                }



            }

        }




    }


}