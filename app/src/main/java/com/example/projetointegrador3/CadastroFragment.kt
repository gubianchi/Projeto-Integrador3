package com.example.projetointegrador3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class CadastroFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_cadastro, container, false)
        val cadastrarBtn: Button = view.findViewById(R.id.btnCadastrar)

        cadastrarBtn.setOnClickListener{
            val fragment = MenuFragment() //navigation para próxima página
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.nav_container, fragment)?.commit()
        }
        return view
    }
}