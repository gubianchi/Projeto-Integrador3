package com.example.projetointegrador3

import com.example.projetointegrador3.disciplinas.AtividadesAdapter
import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import androidx.fragment.app.Fragment
import android.widget.Toast
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.projetointegrador3.databinding.FragmentMapaBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.tasks.Task
import kotlin.properties.Delegates


//codigo funcionando antes de adicionar a localização no botão:
private const val FINE_PERMISSION_CODE = 1
private lateinit var mGoogleMap: GoogleMap
private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
val mapaFragment = MapaFragment()


class MapaFragment : Fragment(), OnMapReadyCallback {
    private lateinit var currentLocation: Location
    //private var localizacaoValida by Delegates.notNull<Boolean>() -> tentei usar alguma variavel global para evitar chamar as funções mas n deu certo
                                                                      // dps da uma olhada na getLastLocation()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_mapa, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(requireActivity())

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync { googleMap ->
            mGoogleMap = googleMap
        }

        checkLocationPermission()
    }

    fun checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED &&
            ContextCompat.checkSelfPermission(
                requireContext(),
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions(
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                FINE_PERMISSION_CODE
            )
        } else {
            getLastLocation()
        }
    }

    private fun getLastLocation() {
        if (if (ActivityCompat.checkSelfPermission(/* context = */ requireContext(), /* permission = */ Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(requireContext(),
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                true
            } else {
                false
            }
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        fusedLocationProviderClient.lastLocation.addOnSuccessListener { location ->
            if (location != null) {
                currentLocation = location
                onMapReady(mGoogleMap)

                /*
                if ((currentLocation.latitude< -22.8 && currentLocation.latitude > -22.9) &&
                    (currentLocation.longitude < -47.0 && currentLocation.longitude > -47.1)){
                    this.localizacaoValida = true
                }
                 */
            }
        }
    }

    //fun isLocalizacaoValida(): Boolean {
        //return this.localizacaoValida
    //}

    override fun onMapReady(googleMap: GoogleMap) {
        mGoogleMap = googleMap

        val campinas = LatLng(currentLocation.latitude, currentLocation.longitude)
        val markerIcon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)
        mGoogleMap.addMarker(MarkerOptions().position(campinas).title("Sua Localização Atual").icon(markerIcon))
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(campinas))
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == FINE_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLastLocation()
            } else {
                Toast.makeText(requireContext(), "Localização negada, permita a localização", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun comparaLocalizacao(): Boolean {

        //getLastLocation()

        //loc puc -22.83080, -47.04399

        return ((-22.83080< -22.8 && -22.83080 > -22.9) &&
                (-47.04399 < -47.0 && -47.04399 > -47.1))
    }

    //fun isLocalizacaoValida(): Boolean {
    //return this.localizacaoValida
    //}
}

