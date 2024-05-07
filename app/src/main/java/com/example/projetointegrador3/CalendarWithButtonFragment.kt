import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.CalendarView
import android.widget.ImageButton
import android.widget.Toast
import com.example.projetointegrador3.AddActivityDialog
import com.example.projetointegrador3.R

class CalendarWithButtonFragment : Fragment(), AddActivityDialog.OnSaveClickListener {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_calendar_with_button, container, false)

        val calendarView = view.findViewById<CalendarView>(R.id.calendarView)
        val addEventButton = view.findViewById<Button>(R.id.addEventButton)
        val imageButton = view.findViewById<ImageButton>(R.id.imageButton)

        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val selectedDate = "$dayOfMonth/${month + 1}/$year"
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

    override fun onSaveClick(activityName: String) {
        Toast.makeText(requireContext(), "Atividade salva: $activityName", Toast.LENGTH_SHORT).show()
    }
}
