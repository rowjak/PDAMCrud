package com.pdam.appabdurrozaq.ui.pelanggan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.pdam.appabdurrozaq.R
import com.pdam.appabdurrozaq.api.ApiService
import com.pdam.appabdurrozaq.data.model.pelanggan.ResponsePelangganList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        supportActionBar!!.title="Peta Pelanggan"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera


        ApiService.endpoint.getPelanggan("-6.889836","109.674591")
            .enqueue(object: Callback<ResponsePelangganList> {
                override fun onFailure(call: Call<ResponsePelangganList>, t: Throwable) {

                }

                override fun onResponse(
                    call: Call<ResponsePelangganList>,
                    response: Response<ResponsePelangganList>
                ) {

                    if (response.isSuccessful){
                        val responsePelangganList: ResponsePelangganList? = response.body()

                        var sydney : LatLng
                        var x = 0
                        for (i in responsePelangganList!!.dataPelanggan){
                            sydney = LatLng(responsePelangganList.dataPelanggan.get(x).latitude!!.toDouble(), responsePelangganList.dataPelanggan.get(x).longitude!!.toDouble())
                            mMap.addMarker(MarkerOptions().position(sydney).title(responsePelangganList.dataPelanggan.get(x).nama))
                            x++
                        }
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(LatLng(-6.889836,109.674591), 12f))
                    }
                }

            })
    }
}
