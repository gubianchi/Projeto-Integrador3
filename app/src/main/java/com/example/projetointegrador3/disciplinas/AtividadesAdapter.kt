package com.example.projetointegrador3.disciplinas


import android.app.AlertDialog
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projetointegrador3.databinding.AtividadesRegistradasBinding
import com.example.projetointegrador3.registros.Aula
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import java.util.Calendar

class AtividadesAdapter(private val aulas: List<Aula>) :
    RecyclerView.Adapter<AtividadesAdapter.ViewHolder>() {
    class ViewHolder(val binding: AtividadesRegistradasBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private lateinit var database: DatabaseReference
        private lateinit var auth: FirebaseAuth
        fun bind(disciplina: Aula) {
            auth = Firebase.auth

            // Inicializa a referência do banco de dados
            val uid = auth.currentUser?.uid ?: "Null"
            database = FirebaseDatabase.getInstance().reference
                .child(uid)
                .child(disciplina.nomeDisciplina)

            val currentTime = Calendar.getInstance()
            val currentHour = currentTime.get(Calendar.HOUR_OF_DAY)
            val currentMinute = currentTime.get(Calendar.MINUTE)

            val startTime = disciplina.horaInicio.split(":")
            val endTime = disciplina.horaFim.split(":")

            val startHour = startTime[0].toInt()
            val startMinute = startTime[1].toInt()

            val endHour = endTime[0].toInt()
            val endMinute = endTime[1].toInt()

            // Converte a hora atual e as horas de início/fim para minutos
            val currentTimeInMinutes = currentHour * 60 + currentMinute
            val startTimeInMinutes = startHour * 60 + startMinute
            val endTimeInMinutes = endHour * 60 + endMinute

            // Verifica se a hora atual está entre a hora de início e de término
            val isWithinTimeRange = currentTimeInMinutes in startTimeInMinutes until endTimeInMinutes

            if(disciplina.pontoBatido){
                binding.textView3.text = "Ponto batido"
                binding.textView3.setTextColor(Color.GREEN)
                binding.button.visibility = View.INVISIBLE
            }
            else if (isWithinTimeRange) {
                binding.button.visibility = View.VISIBLE
                binding.button.text = "Bater ponto"
                binding.button.setTextColor(Color.RED)

                binding.button.setOnClickListener {


                    val alertDialogBuilder = AlertDialog.Builder(binding.root.context)
                    alertDialogBuilder.setMessage("Bater ponto")

                    alertDialogBuilder.setPositiveButton("Bater") { dialog, _ ->

                        val novoDado = mapOf(
                            "pontoBatido" to true
                        )

                        // Atualiza o valor no Firebase
                        database.updateChildren(novoDado).addOnSuccessListener {
                            binding.button.text = "Ponto batido!"
                            binding.button.setTextColor(Color.GREEN)
                            disciplina.pontoBatido = true
                        }
                    }
                    alertDialogBuilder.setNegativeButton("Cancelar") { dialog, _ ->
                        dialog.dismiss()
                    }

                    try {
                        alertDialogBuilder.create().show()
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            } else {
                binding.button.visibility = View.GONE
            }

            binding.tvNomeDisciplina.text = disciplina.nomeDisciplina
            binding.tvHorarioInicio.text = disciplina.horaInicio
            binding.tvHorarioTermino.text = disciplina.horaFim
            binding.tvLocal.text = disciplina.local
            binding.tvData.text = disciplina.data
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = AtividadesRegistradasBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(aulas[position])
    }
    
    override fun getItemCount() = aulas.size
}