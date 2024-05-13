package com.example.projetointegrador3.auth

import android.app.AlertDialog
import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import com.example.projetointegrador3.MenuFragment
import com.example.projetointegrador3.R
import com.example.projetointegrador3.databinding.FragmentCadastroBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth

class CadastroFragment : Fragment(R.layout.fragment_cadastro) {

    private var _binding: FragmentCadastroBinding? = null
    private val binding get()= _binding!!
    private val auth = FirebaseAuth.getInstance()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentCadastroBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageButton = view.findViewById<ImageButton>(R.id.imageButton)
        imageButton.setOnClickListener {
            val fragment = MenuFragment()
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.nav_container, fragment)
            transaction.commit()
        }
        binding.btnCadastrar.setOnClickListener { cadastrar() }
    }


    private fun cadastrar(){

        //                                        ***mais pra frente é preciso adicionar validações
        //---------COLETA DADOS DO USUÁRIO-----------para ver se o cpf é válido (tenho essa função pronta)

        val emailUsuario = binding.textCadastroEmail.text.toString().trim()// "trim" torna o campo como preenchimento obirgatório
        val nomeUsuario = binding.textCadastroNome.text.toString().trim()
        val cpfUsuario = binding.textCadastroCPF.text.toString().trim()
        val senhaUsuario = binding.textCadastroSenha.text.toString().trim()

        val alertDialogBuilder = AlertDialog.Builder(requireContext())

        if (emailUsuario.isEmpty() || senhaUsuario.isEmpty() || cpfUsuario.isEmpty() || nomeUsuario.isEmpty()){
            val snackbar = Snackbar.make(requireView(), "Preencha todos os campos!", Snackbar.LENGTH_SHORT)
            snackbar.setBackgroundTint(Color.RED)
            snackbar.show()
        } else {
            auth.createUserWithEmailAndPassword(emailUsuario, senhaUsuario).addOnCompleteListener { cadastro ->
                if (cadastro.isSuccessful) {
                    val snackbar = Snackbar.make(requireView(), "Conta Cadastrada!", Snackbar.LENGTH_SHORT)
                    snackbar.setBackgroundTint(Color.BLUE)
                    snackbar.show()
                    binding.textCadastroEmail.setText("")
                    binding.textCadastroSenha.setText("")

                    alertDialogBuilder.setPositiveButton("Voltar para tela de login", DialogInterface.OnClickListener { dialog, id ->
                        val fragment = LoginFragment()
                        val transaction = fragmentManager?.beginTransaction()
                        transaction?.replace(R.id.nav_container, fragment)?.commit()
                    })
                    alertDialogBuilder.create()
                    alertDialogBuilder.show()
                } else {
                    // Se o cadastro não for bem-sucedido, mostrar uma mensagem de erro
                    val snackbar = Snackbar.make(requireView(), "Erro ao cadastrar: ${cadastro.exception?.message}", Snackbar.LENGTH_SHORT)
                    snackbar.setBackgroundTint(Color.RED)
                    snackbar.show()
                }
            }
        }
    }
}