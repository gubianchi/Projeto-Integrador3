package com.example.projetointegrador3
//Declara o pacote no qual esta classe está localizada.

import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.projetointegrador3.databinding.FragmentMapaBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.*
//Importa classes e recursos necessários para o funcionamento do fragmento,
//incluindo classes para manipulação de bundles, inflação de layouts,
//elementos de interface do usuário, suporte ao Google Maps e modelos de marcadores.

class MapaFragment : Fragment(), OnMapReadyCallback {  //Declara a classe MapaFragment, que é um fragmento e implementa a interface OnMapReadyCallback, necessária para receber notificações quando o mapa está pronto para ser usado.
    private var myMap: GoogleMap? = null  //Declara uma variável para armazenar uma instância do GoogleMap.

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_mapa, container, false)
    }  //Sobrescreve o método onCreateView para inflar e retornar o layout do fragmento.

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment!!.getMapAsync(this@MapaFragment)
    }  //Sobrescreve o método onViewCreated para configurar o mapa quando a view do fragmento é criada. Obtém uma referência ao SupportMapFragment dentro do layout e chama getMapAsync para receber uma notificação quando o mapa estiver pronto.

    override fun onMapReady(googleMap: GoogleMap) {
        myMap = googleMap
        val campinas = LatLng(-22.907104, -47.063240)
        myMap!!.addMarker(MarkerOptions().position(campinas).title("Campinas"))
        myMap!!.moveCamera(CameraUpdateFactory.newLatLng(campinas))
    }  //Sobrescreve o método onMapReady, que é chamado quando o mapa está pronto para ser usado. Aqui, configura o mapa adicionando um marcador na posição de Campinas e movendo a câmera para essa posição.

}
