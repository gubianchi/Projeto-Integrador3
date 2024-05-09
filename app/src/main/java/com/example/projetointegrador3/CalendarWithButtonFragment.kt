import android.app.AlertDialog
import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.CalendarView
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TimePicker
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.projetointegrador3.AddActivityDialog
import com.example.projetointegrador3.Aula
import com.example.projetointegrador3.LoginFragment
import com.example.projetointegrador3.MenuFragment
import com.example.projetointegrador3.R
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.time.LocalTime

class CalendarWithButtonFragment : Fragment(), AddActivityDialog.OnSaveClickListener {
    private lateinit var database: DatabaseReference
    private lateinit var auth: FirebaseAuth
    private lateinit var data: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_calendar_with_button, container, false)

        val calendarView = view.findViewById<CalendarView>(R.id.calendarView)
        val addEventButton = view.findViewById<Button>(R.id.addEventButton)
        val imageButton = view.findViewById<ImageButton>(R.id.imageButton)


        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val selectedDate = "$dayOfMonth/${month + 1}/$year"
            this.data = selectedDate
            Toast.makeText(requireContext(), "Selecionado: $selectedDate", Toast.LENGTH_SHORT).show()
        }

        addEventButton.setOnClickListener {
            showAddActivityDialog()
        }

        imageButton.setOnClickListener {
            // Chamando onBackPressed() para voltar para a página anterior
            requireActivity().onBackPressed()
        }

        return view
    }



    private fun showAddActivityDialog() {
        val dialog = AddActivityDialog(requireContext(), this)
        dialog.show()
    }

    override fun onSaveClick(aula: Aula) {

        database = Firebase.database.reference
        auth = Firebase.auth

        val uid = auth.currentUser?.uid ?: "Null"
        val id = database.push().key ?: "Null"
        val alertDialogBuilder = AlertDialog.Builder(requireContext())
        aula.data = this.data

        database
            .child(uid)
            .child(aula.nomeDisciplina)
            .setValue(aula)
            .addOnCompleteListener { task ->
                if (task.isSuccessful)
                {
                    val snackbar = Snackbar.make(requireView(), "Aula registrada!", Snackbar.LENGTH_SHORT)
                    snackbar.setBackgroundTint(Color.BLUE)
                    snackbar.show()

                    alertDialogBuilder.create()
                    alertDialogBuilder.show()
                }else{
                    Toast.makeText(requireContext(),task.exception?.message, Toast.LENGTH_SHORT).show()
                }
            }
    }
}
