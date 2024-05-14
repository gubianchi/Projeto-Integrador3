package com.example.projetointegrador3
//Declara o pacote no qual esta classe está localizada.

import CalendarWithButtonFragment
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
// import com.example.projetointegrador3.disciplinas.ExibeDisciplinasFragment
//Importa classes e recursos necessários para o funcionamento do fragmento, incluindo outros fragmentos,
//classes para manipulação de bundles, inflação de layouts, elementos de interface do usuário e classes
//de fragmentos que não estão sendo utilizadas no momento.

class MenuFragment : Fragment() {  //Declara a classe MenuFragment, que é um fragmento.

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(  //Sobrescreve o método onCreateView, que é chamado para criar e retornar a hierarquia de visualização associada ao fragmento.
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view =  inflater.inflate(R.layout.fragment_menu, container, false)  //Infla o layout do fragmento.

        val btnBaterPonto: Button = view.findViewById(R.id.btnIrParaBaterPonto)
        val btnCalendario: Button = view.findViewById(R.id.btnIrParaRegistrar)
        val btnMapa: Button = view.findViewById(R.id.btnIrParaMapa)
        val imageButton = view.findViewById<ImageButton>(R.id.imageButton)
        //Recupera referências aos elementos de interface do usuário do layout.

        btnCalendario.setOnClickListener {
            fragmentManager?.beginTransaction()?.apply {
                replace(R.id.nav_container, CalendarWithButtonFragment())
                addToBackStack(null)
                commit()
            }
        }  //Define um ouvinte para o botão de calendário para substituir o conteúdo do contêiner de navegação pelo CalendarWithButtonFragment.

        /* btnBaterPonto.setOnClickListener {
             fragmentManager?.beginTransaction()?.apply {
                 replace(R.id.nav_container, ExibeDisciplinasFragment())
                 addToBackStack(null)
                 commit()
             }
         } */

        btnMapa.setOnClickListener {
            fragmentManager?.beginTransaction()?.apply {
                replace(R.id.nav_container, MapaFragment())
                addToBackStack(null)
                commit()
            }
        }  //Define um ouvinte para o botão do mapa para substituir o conteúdo do contêiner de navegação pelo MapaFragment.

        /*  btnCalendario.setOnClickListener {

              fragmentManager?.beginTransaction()?.apply {
                  replace(R.id.nav_container, CalendarWithButtonFragment())
                  addToBackStack(null)
                  commit()

              val fragment = CalendarWithButtonFragment()
              val transaction = fragmentManager?.beginTransaction()
              transaction?.replace(R.id.nav_container, fragment)?.commit()
              }

          btnVerRegistros.setOnClickListener {
              val fragment = ExibeDisciplinasFragment()
              val transaction = fragmentManager?.beginTransaction()
              transaction?.replace(R.id.nav_container, fragment)?.commit()
          }


          imageButton.setOnClickListener {
              // Chamando onBackPressed() para voltar para a página anterior
              requireActivity().onBackPressed()
          } */


        return view
    }

}