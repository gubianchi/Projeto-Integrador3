import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.projetointegrador3.databinding.AtividadesRegistradasBinding
import com.example.projetointegrador3.registros.Aula
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.*
import android.widget.Toast
import java.util.Calendar
import android.text.format.DateFormat
import java.util.*
//Essas linhas importam as classes e recursos necessários para o funcionamento do adaptador de RecyclerView,
//incluindo manipulação de cores, inflação de layouts, definição de layouts de itens de RecyclerView,
//manipulação de eventos de clique, exibição de Snackbars e interação com o Firebase Realtime Database.

/*
val databaseReference = FirebaseDatabase.getInstance().getReference("P4m2jHRNBmdNNTYYYb2d2u2F8pl2")

class AtividadesAdapter(private val aulas: List<Aula>) :
    RecyclerView.Adapter<AtividadesAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: AtividadesRegistradasBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private lateinit var buttonBaterPonto: Button

        fun bind(disciplina: Aula) {
            binding.tvNomeDisciplina.text = disciplina.nomeDisciplina
            binding.tvHorarioInicio.text = disciplina.horaInicio
            binding.tvHorarioTermino.text = disciplina.horaFim
            binding.tvLocal.text = disciplina.local

            buttonBaterPonto = binding.button

            // Verificar a data e o horário do Firebase
            verificarDataHoraFirebase()
        }

        private fun verificarDataHoraFirebase() {
            // Referência ao nó no Realtime Database que contém a data
            val dataRef = databaseReference.child("data")

            dataRef.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val dataFirebase = dataSnapshot.getValue(String::class.java)

                    // Verificar se a data do Firebase é válida
                    if (dataFirebase != null && isDataValida(dataFirebase)) {
                        // Habilitar o botão "Bater Ponto"
                        buttonBaterPonto.isEnabled = true
                        buttonBaterPonto.setOnClickListener {
                            // Implementar a lógica para bater o ponto
                            baterPonto(nomeDisciplina = "")
                            Toast.makeText(this, "Ponto Batido", Toast.LENGTH_SHORT).show()

                        }
                    } else {
                        // Desabilitar o botão "Bater Ponto"
                        buttonBaterPonto.isEnabled = false
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    // Lidar com erros de leitura do Realtime Database, se necessário
                }
            })
        }

        private fun isDataValida(dataFirebase: String): Boolean {
            // Obter a data atual
            val dataAtual = Calendar.getInstance().time

            // Converter a data do Firebase para um Date
            val formatter = DateFormat.getDateFormat(itemView.context)
            val dataFirebaseDate = formatter.parse(dataFirebase)

            // Verificar se a dataFirebase é igual à data atual
            return dataFirebaseDate == dataAtual
        }

        private fun baterPonto(nomeDisciplina: String) {
            // Implementar a lógica para bater o ponto
            // Exemplo: Registrar o ponto no banco de dados

            // Exibir um Snackbar para indicar que o ponto foi batido
            Snackbar.make(itemView, "Ponto batido para $nomeDisciplina", Snackbar.LENGTH_SHORT).show()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = AtividadesRegistradasBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(aulas[position])
    }

    override fun getItemCount() = aulas.size
} */