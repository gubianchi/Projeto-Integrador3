package com.example.projetointegrador3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        val enviarBtn: Button = view.findViewById(R.id.btnEnviar)

        val btnToCadastro: TextView = view.findViewById(R.id.textPergunta)

        enviarBtn.setOnClickListener{
            val fragment = MenuFragment() //navigation para próxima página
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.nav_container, fragment)?.commit()
        }

        btnToCadastro.setOnClickListener {
            val fragment2 = CadastroFragment()
            val transaction2 = fragmentManager?.beginTransaction()
            transaction2?.replace(R.id.nav_container, fragment2)?.commit()
        }

        return view
    }
}