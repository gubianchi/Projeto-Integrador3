package com.example.projetointegrador3

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.TimePicker
import com.example.projetointegrador3.R

class AddActivityDialog(context: Context, private val listener: OnSaveClickListener) : Dialog(context), View.OnClickListener {

    private lateinit var editTextActivityName: EditText
    private lateinit var timePicker: TimePicker

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_add_activity)

        val buttonSave = findViewById<Button>(R.id.buttonSave)
        editTextActivityName = findViewById(R.id.editTextActivityName)
        timePicker = findViewById(R.id.timePicker)

        buttonSave.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.buttonSave -> {
                val activityName = editTextActivityName.text.toString().trim()
                if (activityName.isNotEmpty()) {
                    listener.onSaveClick(activityName)
                    dismiss()
                }
                val hour = timePicker.hour
                val minute = timePicker.minute
                val timeString = String.format("%02d:%02d", hour, minute)
                listener.onSaveClick(timeString)
                dismiss()
            }
        }
    }

    interface OnSaveClickListener {
        fun onSaveClick(activityName: String)
    }
}