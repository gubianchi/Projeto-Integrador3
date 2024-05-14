import android.app.AlertDialog
import android.graphics.Color
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
import com.example.projetointegrador3.disciplinas.Aula
import com.example.projetointegrador3.R
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
//Essas linhas importam classes e recursos necessários para o funcionamento do fragmento,
//incluindo elementos de diálogo, manipulação de cores, tratamento de bundles, inflação de layouts,
//manipulação de eventos de clique, exibição de Toasts, diálogos personalizados, classes de modelo (Aula),
//recursos de layout e bibliotecas do Firebase.

class CalendarWithButtonFragment : Fragment(), AddActivityDialog.OnSaveClickListener {  //Declara a classe CalendarWithButtonFragment, que é um fragmento. Este fragmento implementa a interface AddActivityDialog.OnSaveClickListener.
    private lateinit var database: DatabaseReference
    private lateinit var auth: FirebaseAuth
    private lateinit var data: String
    //Declara propriedades privadas para referências ao banco de dados Firebase, autenticação do Firebase e uma variável de data.

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? { //Sobrescreve o método onCreateView para inflar e retornar o layout do fragmento.
        val view = inflater.inflate(R.layout.fragment_calendar_with_button, container, false)

        val calendarView = view.findViewById<CalendarView>(R.id.calendarView)
        val addEventButton = view.findViewById<Button>(R.id.addEventButton)
        val imageButton = view.findViewById<ImageButton>(R.id.imageButton)
        //Recupera referências aos elementos de interface do usuário do layout do fragmento.


        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val selectedDate = "$dayOfMonth/${month + 1}/$year"
            this.data = selectedDate
            Toast.makeText(requireContext(), "Selecionado: $selectedDate", Toast.LENGTH_SHORT).show()
        }  //Configura um ouvinte para o CalendarView para capturar a data selecionada pelo usuário.

        addEventButton.setOnClickListener {
            showAddActivityDialog()
        }  //Define um ouvinte para o botão "Adicionar Evento" para mostrar um diálogo personalizado ao ser clicado.

        imageButton.setOnClickListener {
            // Chamando onBackPressed() para voltar para a página anterior
            requireActivity().onBackPressed()
        }

        return view
    }



    private fun showAddActivityDialog() {
        val dialog = AddActivityDialog(requireContext(), this)
        dialog.show()
    }  //Método para exibir o diálogo de adição de atividade.

    override fun onSaveClick(aula: Aula) {  //Método da interface OnSaveClickListener chamado quando o usuário salva uma atividade no diálogo.
        //Salva os dados da aula no banco de dados Firebase Realtime.

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
                if (task.isSuccessful)  // Em caso de sucesso, exibe um Snackbar indicando que a aula foi registrada.
                {
                    val snackbar = Snackbar.make(requireView(), "Aula registrada!", Snackbar.LENGTH_SHORT)
                    snackbar.setBackgroundTint(Color.BLUE)
                    snackbar.show()

                    alertDialogBuilder.create()
                    alertDialogBuilder.show()
                }else{  // Em caso de falha, exibe um Toast com a mensagem de erro.
                    Toast.makeText(requireContext(),task.exception?.message, Toast.LENGTH_SHORT).show()
                }
            }
    }
}
