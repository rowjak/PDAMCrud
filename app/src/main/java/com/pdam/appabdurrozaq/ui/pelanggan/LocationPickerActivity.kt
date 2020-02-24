package com.pdam.appabdurrozaq.ui.pelanggan

import android.app.Activity
import android.content.Intent
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.common.api.Status
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.PlacesClient
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener
import com.pdam.appabdurrozaq.R
import com.pdam.appabdurrozaq.data.Constant
import com.pdam.appabdurrozaq.utils.place.FieldSelector
import kotlinx.android.synthetic.main.activity_location_picker.*
import java.io.IOException
import java.util.*

class LocationPickerActivity : AppCompatActivity(), OnMapReadyCallback , PlaceSelectionListener {

    private lateinit var mMap: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var lastLocation: Location
    private val marker = MarkerOptions()
    lateinit var fieldSelector: FieldSelector
    lateinit var placesClient: PlacesClient
    var errorMessage: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location_picker)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        supportActionBar!!.title = "Pilih Lokasi"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        // Initialize Places.
        Places.initialize(getApplicationContext(), getString(R.string.google_maps_key))
        placesClient = Places.createClient(this)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        fieldSelector = FieldSelector(findViewById(R.id.custom_fields_list))

        val autocompleteFragment = supportFragmentManager.findFragmentById(R.id.autocomplete_fragment)
                as AutocompleteSupportFragment
        autocompleteFragment.view!!.findViewById<EditText>(R.id.places_autocomplete_search_input).textSize = 15f
        autocompleteFragment.view!!.findViewById<EditText>(R.id.places_autocomplete_search_input).setHint("Cari Lokasi")
        autocompleteFragment.setPlaceFields(getPlaceFields())
        autocompleteFragment.setOnPlaceSelectedListener(this)
    }

    private fun getPlaceFields(): List<Place.Field> {
        return fieldSelector.getAllFields()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.setOnCameraMoveListener {
            gpsMarker.setPadding(0,0,0,10)
            Log.d("Move Camera","CAMERA IS MOVING")
        }

        mMap.setOnCameraIdleListener {
            gpsMarker.setPadding(0,0,0,0)
            val location = mMap.cameraPosition.target
            Constant.LATITUDE = location.latitude.toString()
            Constant.LONGITUDE = location.longitude.toString()
            Log.d("Move Camera","CAMERA IS IDLE")

            val geocoder = Geocoder(this, Locale.getDefault())
            var addresses: List<Address> = emptyList()

            try {
                addresses = geocoder.getFromLocation(
                    location.latitude,
                    location.longitude,
                    // In this sample, we get just a single address.
                    1)
            } catch (ioException: IOException) {
                errorMessage = "service tidak ditemukan"
                Log.e("errorGeocoder", errorMessage, ioException)
            } catch (illegalArgumentException: IllegalArgumentException) {
                errorMessage = "invalid latlng"
                Log.e("errorGeocoder", "$errorMessage. Latitude = $location.latitude , " +
                        "Longitude =  $location.longitude", illegalArgumentException)
            }

            if (addresses.isEmpty()) {
                if (errorMessage.isEmpty()) {
                    errorMessage = "alamat tidak ditemukan"
                    Log.e("errorGeocoder", errorMessage)
                }
            } else {
                val address = addresses[0]
                val addressFragments = with(address) {
                    (0..maxAddressLineIndex).map { getAddressLine(it) }
                }
                Log.i("errorGeocoder", "alamat tidemukan")
                Constant.ALAMAT = addressFragments.joinToString(separator = "\n")
                Log.i("Hasil Geocoder",Constant.ALAMAT)
            }

        }

        mMap.uiSettings.isZoomControlsEnabled = true
        mMap.uiSettings.isCompassEnabled = true
        mMap.isMyLocationEnabled = true

        fusedLocationClient.lastLocation.addOnSuccessListener(this){location ->
            if (location != null){
                lastLocation = location
                var currentLatLng = LatLng(location.latitude, location.longitude)
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 14f))

                Constant.LATITUDE = location.latitude.toString()
                Constant.LONGITUDE = location.longitude.toString()

//                marker.position(currentLatLng)
//                mMap.addMarker(marker)

            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_maps, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.action_save -> {
                val result = Intent()
                setResult(Activity.RESULT_OK, result)
                finish()
                return true
            }else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onPlaceSelected(place: Place) {
//        Toast.makeText(applicationContext,""+p0!!.name+p0!!.latLng,Toast.LENGTH_LONG).show();
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(place.latLng, 18f))

    }

    override fun onError(status: Status) {
        Toast.makeText(applicationContext,""+status.toString(),Toast.LENGTH_LONG).show();
    }
}
