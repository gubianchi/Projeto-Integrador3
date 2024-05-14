package com.example.projetointegrador3
//Declara o pacote no qual esta classe está localizada.

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.TimePicker
import com.example.projetointegrador3.registros.Aula
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
//Importa as classes e recursos necessários para o funcionamento do diálogo,
//incluindo elementos de interface do usuário, classes de modelo (Aula),
//classes de autenticação e manipulação de banco de dados Firebase.

class AddActivityDialog(context: Context, private val listener: OnSaveClickListener) : Dialog(context), View.OnClickListener {  //Declara a classe AddActivityDialog, que é um diálogo personalizado. Ele estende a classe Dialog e implementa a interface View.OnClickListener.

    private lateinit var database: DatabaseReference
    private lateinit var auth: FirebaseAuth
    //Declara propriedades para referências ao banco de dados e autenticação do Firebase.

    private lateinit var editTextActivityName: EditText
    private lateinit var timePickerInicio: TimePicker
    private lateinit var timePickerFim: TimePicker
    private lateinit var editTextActivityLocal: EditText
    //Declara propriedades para referências aos elementos de interface do usuário dentro do diálogo.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_add_activity)

        val buttonSave = findViewById<Button>(R.id.buttonSave)
        editTextActivityName = findViewById(R.id.editTextActivityName)
        timePickerInicio = findViewById(R.id.timePickerInicio)
        timePickerFim = findViewById(R.id.timePickerFim)
        editTextActivityLocal = findViewById(R.id.editTextActivityLocal)

        buttonSave.setOnClickListener(this)
    }
    //Sobrescreve o método onCreate para configurar a interface do usuário do diálogo.
    //Infla o layout do diálogo, associa as referências dos elementos de interface do usuário com
    //as propriedades e define um listener para o botão de salvar.

    override fun onClick(v: View?) {  //Sobrescreve o método onClick para tratar os cliques nos elementos de interface do usuário. Quando o botão de salvar é clicado, executa a lógica para salvar os dados do formulário do diálogo.
        when (v?.id) {
            R.id.buttonSave -> {
                // Lógica para salvar os dados do formulário do diálogo

                database = Firebase.database.reference
                auth = Firebase.auth

                val local = editTextActivityLocal.text.toString()
                val nomeDisciplina = editTextActivityName.text.toString()


                // Convertendo a hora e o minuto para uma string
                val hourInicio = timePickerInicio.hour
                val minuteInicio = timePickerInicio.minute
                val timeStringInicio = String.format("%02d:%02d", hourInicio, minuteInicio)


                // Convertendo a hora e o minuto para uma string
                val hourFim = timePickerFim.hour
                val minuteFim = timePickerFim.minute
                val timeStringFim = String.format("%02d:%02d", hourFim, minuteFim)
                val data = ""

                val aula = Aula(timeStringFim, timeStringInicio, data, local, nomeDisciplina) // nomeDisciplina, local, timeStringInicio, timeStringFim
                listener.onSaveClick(aula)
                dismiss()
            }
        }
    }

    interface OnSaveClickListener {
        fun onSaveClick(aula: Aula)
    }
    //Declara uma interface OnSaveClickListener para lidar com os eventos de clique do botão salvar.
}