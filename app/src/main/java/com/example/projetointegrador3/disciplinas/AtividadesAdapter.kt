package com.example.projetointegrador3.disciplinas

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projetointegrador3.databinding.AtividadesRegistradasBinding
import com.example.projetointegrador3.registros.Aula

class AtividadesAdapter(private val aulas: List<Aula>) :
    RecyclerView.Adapter<AtividadesAdapter.ViewHolder>() {
    class ViewHolder(val binding: AtividadesRegistradasBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(disciplina: Aula) {
            binding.tvNomeDisciplina.text = disciplina.nomeDisciplina
            binding.tvHorarioInicio.text = disciplina.horaInicio
            binding.tvHorarioTermino.text = disciplina.horaFim
            binding.tvLocal.text = disciplina.local
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