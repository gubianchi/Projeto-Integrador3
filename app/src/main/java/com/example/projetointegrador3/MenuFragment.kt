package com.example.projetointegrador3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class MenuFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view =  inflater.inflate(R.layout.fragment_menu, container, false)


        val btnBaterPonto: Button = view.findViewById(R.id.btnIrParaBaterPonto)
        val btnVerRegistros: Button = view.findViewById(R.id.btnIrParaRegistros)
        val btnRegistrarPonto: Button = view.findViewById(R.id.btnIrParaRegistrar)


        btnBaterPonto.setOnClickListener {
            val fragment = LoginFragment()
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.nav_container, fragment)?.commit()
        }
        /*
        btnVerRegistros.setOnClickListener {
            //val fragment = ... tela ver registros
            val transaction = fragmentManager?.beginTransaction()
            //transaction?.replace(R.id.nav_container, tela ver registros)
        }

        btnRegistrarPonto.setOnClickListener {
            //val fragment = ... tela registrar ponto
            val transaction = fragmentManager?.beginTransaction()
            //transaction?.replace(R.id.nav_container, tela registrar ponto)
        }

         */
        return view
    }

}