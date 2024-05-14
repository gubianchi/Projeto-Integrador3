package com.example.projetointegrador3.disciplinas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projetointegrador3.databinding.FragmentOrdenaAulasBinding
import com.example.projetointegrador3.registros.Aula
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
/*
class OrdenaAulasFragment : Fragment() {
    private var _binding: FragmentOrdenaAulasBinding? = null
    private val binding get() = _binding!!

    private lateinit var database: DatabaseReference
    private lateinit var disciplinaAdapter: AtividadesAdapter
    private val listaDisciplinas = mutableListOf<Aula>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentOrdenaAulasBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupFirebase()
        setupRecyclerView()
        fetchDisciplinas()
    }

    private fun setupFirebase() {
        database = FirebaseDatabase
            .getInstance().reference
            .child(FirebaseAuth.getInstance().currentUser?.uid ?: "Null")
    }

    private fun setupRecyclerView() {
        disciplinaAdapter = AtividadesAdapter(listaDisciplinas)
        binding.recyclerAtividades.layoutManager = LinearLayoutManager(context)
        binding.recyclerAtividades.adapter = disciplinaAdapter
    }

    private fun fetchDisciplinas() {
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                listaDisciplinas.clear()
                for (childSnapshot in snapshot.children) {
                    val disciplina = childSnapshot.getValue(Aula::class.java)
                    disciplina?.let { listaDisciplinas.add(it) }
                }
                disciplinaAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(requireContext(),"Erro ao acessar o Banco de Dados", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}*/