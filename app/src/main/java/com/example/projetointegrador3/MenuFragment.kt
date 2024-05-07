package com.example.projetointegrador3

import CalendarWithButtonFragment
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton

class MenuFragment : Fragment() {

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view =  inflater.inflate(R.layout.fragment_menu, container, false)


        val btnBaterPonto: Button = view.findViewById(R.id.btnIrParaBaterPonto)
        val btnVerRegistros: Button = view.findViewById(R.id.btnIrParaRegistros)
        val btnCalendario: Button = view.findViewById(R.id.btnIrParaRegistrar)
        val imageButton = view.findViewById<ImageButton>(R.id.imageButton)

        btnCalendario.setOnClickListener {
            fragmentManager?.beginTransaction()?.apply {
                replace(R.id.nav_container, CalendarWithButtonFragment())
                addToBackStack(null)
                commit()
            }
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