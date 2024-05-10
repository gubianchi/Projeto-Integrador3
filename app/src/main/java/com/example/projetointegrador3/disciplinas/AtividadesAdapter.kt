package com.example.projetointegrador3.disciplinas

import CalendarWithButtonFragment
import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projetointegrador3.R
import com.example.projetointegrador3.databinding.AtividadesRegistradasBinding
import com.example.projetointegrador3.registros.Aula

class AtividadesAdapter(private val aulas: List<Aula>) :
    RecyclerView.Adapter<AtividadesAdapter.ViewHolder>() {
    class ViewHolder(val binding: AtividadesRegistradasBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private lateinit var fragmentManager: FragmentManager
        fun bind(disciplina: Aula) {
            binding.tvNomeDisciplina.text = disciplina.nomeDisciplina
            binding.tvHorarioInicio.text = disciplina.horaInicio
            binding.tvHorarioTermino.text = disciplina.horaFim
            binding.tvLocal.text = disciplina.local
            binding.tvData.text = disciplina.data


            binding.tvNomeDisciplina.setOnClickListener {
                val alertDialogBuilder = AlertDialog.Builder(binding.root.context)
                alertDialogBuilder.setMessage("Editar registro")
                alertDialogBuilder.create()
                alertDialogBuilder.show()
                /*
                alertDialogBuilder.setPositiveButton("OK") { dialog, _ ->

                    val fragment = CalendarWithButtonFragment()
                    val transaction = fragmentManager.beginTransaction()
                    transaction.replace(R.id.nav_container, fragment).commit()
                    dialog.dismiss()
                }
                alertDialogBuilder.setNegativeButton("Voltar") { dialog, _ ->

                    dialog.dismiss()
                }

                 */
            }
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