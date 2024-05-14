package com.example.projetointegrador3
//Declara o pacote no qual esta classe está localizada.

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.projetointegrador3.auth.LoginFragment
//Importa classes e recursos necessários para o funcionamento da atividade,
//incluindo a classe AppCompatActivity, a classe Bundle para passar dados entre componentes,
//o método enableEdgeToEdge para habilitar a tela cheia sem bordas e o fragmento LoginFragment.

class MainActivity : AppCompatActivity() {  //Declara a classe MainActivity, que é uma atividade e estende a classe AppCompatActivity.
    override fun onCreate(savedInstanceState: Bundle?) {  //Sobrescreve o método onCreate que é chamado quando a atividade está sendo criada.
        super.onCreate(savedInstanceState)  //Chama a implementação da superclasse do método onCreate.
        enableEdgeToEdge()  //Chama o método enableEdgeToEdge() para habilitar o modo de tela cheia sem bordas.

        setContentView(R.layout.activity_main)  //Define o layout da atividade como o layout activity_main.

        supportFragmentManager.beginTransaction().replace(R.id.nav_container, LoginFragment()).commit()
        //Inicia uma transação no FragmentManager para substituir o conteúdo do contêiner nav_container pelo fragmento LoginFragment. Em seguida, confirma a transação.


        /*código para passar dados enstre fragments

         val bundle = Bundle()
            bundle.putString("data", binding.#id da onde vai retirar o texto#.text.toString())

            val fragment = AtividadeFragment()
            fragment.arguments = bundle

            depois só fazer a troca de fragments normalmente
            ----------------------------------------------
            o fragment que vai receber os dados deve:

            val args = this.arguments
            val inputData = args?.get("data")
            binding.#id que vai receber o texto#.text = Editable.Factory.getInstance().newEditable(inputData.toString())
         */
    }
}

