package br.dev.danielribeiro.bestage60

import android.content.Context
import android.location.Geocoder
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import java.io.IOException
import java.util.*

class LocationManagerHelper(
    private val context: Context,
    private val onLocationChanged: (Location) -> Unit
) {
    private val locationManager: LocationManager =
        context.getSystemService(Context.LOCATION_SERVICE) as LocationManager

    private val locationListener: LocationListener = object : LocationListener {
        override fun onLocationChanged(location: Location) {
            onLocationChanged.invoke(location)
        }

        override fun onStatusChanged(provider: String, status: Int, extras: Bundle?) {}

        override fun onProviderEnabled(provider: String) {}

        override fun onProviderDisabled(provider: String) {}
    }

    fun requestLocationUpdates() {
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            try {
                locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    0,
                    0f,
                    locationListener
                )
            } catch (e: SecurityException) {
                e.printStackTrace()
            }
        }
    }

    fun stopLocationUpdates() {
        locationManager.removeUpdates(locationListener)
    }

    fun getCityName(latitude: Double, longitude: Double): String? {
        val geocoder = Geocoder(context, Locale.getDefault())
        try {
            val addresses = geocoder.getFromLocation(latitude, longitude, 1)
            if (!addresses.isNullOrEmpty()) {
                return addresses[0].locality
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return null
    }

}

