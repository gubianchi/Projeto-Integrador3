package com.example.projetointegrador3.disciplinas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projetointegrador3.MenuFragment
import com.example.projetointegrador3.R
import com.example.projetointegrador3.databinding.FragmentExibeDisciplinasBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class ExibeDisciplinasFragment : Fragment() {
    private var _binding: FragmentExibeDisciplinasBinding? = null
    private val binding get() = _binding!!

    private lateinit var database: DatabaseReference
    private lateinit var atividadesAdapter: AtividadesAdapter
    private val listaDisciplinas = mutableListOf<Aula>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentExibeDisciplinasBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupFirebase()
        setupRecyclerView()
        fetchDisciplinas()
        val imageButton = view.findViewById<ImageButton>(R.id.imageButton)
        imageButton.setOnClickListener {
            val fragment = MenuFragment()
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.nav_container, fragment)
            transaction.commit()
        }
    }

    private fun setupFirebase() {
        database = FirebaseDatabase
            .getInstance().reference
            .child(FirebaseAuth.getInstance().currentUser?.uid ?: "Null")
    }

    private fun setupRecyclerView() {
        atividadesAdapter = AtividadesAdapter(listaDisciplinas)
        binding.recyclerDisciplinas.layoutManager = LinearLayoutManager(context)
        binding.recyclerDisciplinas.adapter = atividadesAdapter
    }

    private fun fetchDisciplinas() {
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                listaDisciplinas.clear()
                for (childSnapshot in snapshot.children) {
                    val disciplina = childSnapshot.getValue(Aula::class.java)
                    disciplina?.let { listaDisciplinas.add(it) }
                }
                atividadesAdapter.notifyDataSetChanged()
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
}