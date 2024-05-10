package com.example.projetointegrador3

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.projetointegrador3.auth.LoginFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_main)

      supportFragmentManager.beginTransaction().replace(R.id.nav_container, LoginFragment()).commit()


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