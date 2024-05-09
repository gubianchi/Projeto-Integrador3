package com.example.projetointegrador3

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.TimePicker
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.projetointegrador3.R
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class AddActivityDialog(context: Context, private val listener: OnSaveClickListener) : Dialog(context), View.OnClickListener {

    private lateinit var database: DatabaseReference
    private lateinit var auth: FirebaseAuth

    private lateinit var editTextActivityName: EditText
    private lateinit var timePickerInicio: TimePicker
    private lateinit var timePickerFim: TimePicker
    private lateinit var editTextActivityLocal: EditText

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

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.buttonSave -> {

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
}