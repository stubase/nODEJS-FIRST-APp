package com.capstone.hibykes.ui.maps

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.capstone.hibykes.R
import com.capstone.hibykes.data.local.entity.StationEntity
import com.capstone.hibykes.databinding.FragmentMapsBinding
import com.capstone.hibykes.ui.station.StationActivity
import com.capstone.hibykes.viewmodel.ViewModelFactory
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.util.*

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS", "RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS", "CAST_NEVER_SUCCEEDS")
class MapsFragment : Fragment(), OnMapReadyCallback {

    private lateinit var fragmentMapsBinding: FragmentMapsBinding
    private lateinit var viewModel: MapsViewModel
    private lateinit var mMap: GoogleMap
    private lateinit var currentLocation: Location
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private val permissionCode = 101
    var markerMap = HashMap<String, String>()
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentMapsBinding = FragmentMapsBinding.inflate(layoutInflater, container, false)
        return fragmentMapsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = ViewModelFactory.getInstance(requireActivity())
        viewModel = ViewModelProvider(this, factory)[MapsViewModel::class.java]

        val mapFragment = childFragmentManager.findFragmentById((R.id.map)) as SupportMapFragment
        mapFragment.getMapAsync(this)

        fusedLocationProviderClient =  LocationServices.getFusedLocationProviderClient(requireContext())

        auth = Firebase.auth
        val name = auth.currentUser?.displayName
        fragmentMapsBinding.tvHi.text = getString(R.string.hai, name)
    }

    private fun fetchLocation(){
        if (ActivityCompat.checkSelfPermission(requireActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED && context?.let {
                ActivityCompat.checkSelfPermission(it, Manifest.permission.ACCESS_COARSE_LOCATION)
            } != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), permissionCode)
            return
        }
        fusedLocationProviderClient.lastLocation
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String?>, grantResults: IntArray) {
        when (requestCode) {
            permissionCode -> if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                fetchLocation()
            }
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val boundsBuilder = LatLngBounds.Builder()
        viewModel.getStationsData().observe(viewLifecycleOwner, {
            for (station in it) {
                val latLngStation = LatLng(station.latitude?.toDouble()!!, station.longitude?.toDouble()!!)
                boundsBuilder.include(latLngStation)
                val stationMarker = mMap.addMarker(MarkerOptions()
                        .position(latLngStation)
                        .title(station.name)
                        .snippet(station.description)
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                )
                val stationMarkerId = stationMarker.id
                markerMap[stationMarkerId] = station.toString()

                mMap.setOnInfoWindowClickListener { marker ->
                    val data = markerMap[marker.id]
                    var dataObject: StationEntity
                    viewModel.getStationsData().observe(viewLifecycleOwner, {stationOnMarker ->
                        for (item in stationOnMarker) {
                            if (item.toString() == data) {
                                dataObject = item
                                val intent =  Intent(context, StationActivity::class.java)
                                intent.putExtra(StationActivity.EXTRA_STATION, dataObject)
                                startActivity(intent)
                            }
                        }
                    })
                }
                mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(boundsBuilder.build(), 1000, 1000, 0))
            }
        })

        if (ActivityCompat.checkSelfPermission(requireActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED && context?.let {
                ActivityCompat.checkSelfPermission(it, Manifest.permission.ACCESS_COARSE_LOCATION)
            } != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), permissionCode)
            return
        }
        val task = fusedLocationProviderClient.lastLocation
        task.addOnSuccessListener { location ->
            if (location != null) {
                currentLocation = location
                val latLngLocation = LatLng(currentLocation.latitude, currentLocation.longitude)
                val latLng = LatLng(currentLocation.latitude, currentLocation.longitude)
                mMap.addMarker(MarkerOptions().position(latLngLocation).title("My Location"))
                mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng))
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 5f))
            }
        }
    }

    private fun getCityName(lat: Double, long: Double):String{
        var cityName = ""
        var countryName = ""
        val geoCoder = Geocoder(requireContext(), Locale.getDefault())
        val address = geoCoder.getFromLocation(lat, long, 3)

        cityName = address[0].getAddressLine(0)
        countryName = address[0].countryName
        return cityName
    }
}