package com.example.projetointegrador3

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.example.projetointegrador3.databinding.FragmentCadastroBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class CadastroFragment : Fragment(R.layout.fragment_cadastro) {

    private var _binding: FragmentCadastroBinding? = null
    private val binding get()= _binding!!
    private lateinit var database: DatabaseReference


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentCadastroBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnCadastrar.setOnClickListener { cadastrar() }
    }


    private fun cadastrar(){

        //                                        ***mais pra frente é preciso adicionar validações
        //---------COLETA DADOS DO USUÁRIO-----------para ver se o cpf é válido (tenho essa função pronta)

        val emailUsuario = binding.textCadastroEmail.text.toString().trim()// "trim" torna o campo como preenchimento obirgatório
        val nomeUsuario = binding.textCadastroNome.text.toString().trim()
        val cpfUsuario = binding.textCadastroCPF.text.toString().trim()
        val senhaUsuario = binding.textCadastroSenha.text.toString().trim()

        //                                             ***mais pra frente é preciso colocar validações
        //---------INSERÇÃO NO BANCO DE DADOS ----------- para checar se ja existe um cadastro com dados iguais

         inserirDadoBD(nomeUsuario, emailUsuario, cpfUsuario, senhaUsuario)

        val alertDialogBuilder = AlertDialog.Builder(requireContext())
        alertDialogBuilder.setTitle("Conta Criada!")


        alertDialogBuilder.setPositiveButton("Voltar para tela de login", DialogInterface.OnClickListener { dialog, id ->
            val fragment = LoginFragment()
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.nav_container, fragment)?.commit()
        })
        alertDialogBuilder.create()
        alertDialogBuilder.show()
    }


    private fun inserirDadoBD(nomeUsuario: String, emailUsuario: String, cpfUsuario: String, senhaUsuario: String){

        database =  FirebaseDatabase.getInstance().getReference("usuarios")
        val usuario = Usuario(nomeUsuario, emailUsuario, cpfUsuario, senhaUsuario)
        database.child(cpfUsuario).setValue(usuario)
    }


}