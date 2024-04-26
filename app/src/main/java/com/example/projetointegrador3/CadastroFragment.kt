package com.example.projetointegrador3

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.projetointegrador3.databinding.FragmentCadastroBinding

class CadastroFragment : Fragment(R.layout.fragment_cadastro) {

    private var _binding: FragmentCadastroBinding? = null
    private val binding get()= _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCadastroBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnCadastrar.setOnClickListener { cadastrar() }
    }

    private fun cadastrar(){
        val emailUsuario = binding.textCadastroEmail.toString().trim()//trim torna o campo como preenchimento obirgatório
        val nomeUsuario = binding.textCadastroNome.toString().trim()
        val cpfCliente = binding.textCadastroCPF.toString().trim()
        val senhaCliente = binding.textCadastroSenha.toString().trim()

        val alertDialogBuilder = AlertDialog.Builder(requireContext())//criador do alerta
        alertDialogBuilder.setTitle("Conta Criada!")

        alertDialogBuilder.setPositiveButton("Voltar para tela de login", DialogInterface.OnClickListener { dialog, id ->
            val fragment = LoginFragment()
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.nav_container, fragment)?.commit()
             }) //dispara um alerta notificando que a conta foi criada e manda ele de volta para a tela de login

        alertDialogBuilder.create()
        alertDialogBuilder.show()
    }
}