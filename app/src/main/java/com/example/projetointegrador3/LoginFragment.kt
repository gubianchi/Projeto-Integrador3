package com.example.projetointegrador3

import RecuperarSenhaFragment
import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.projetointegrador3.databinding.FragmentLoginBinding
import android.graphics.Color
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth


class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var auth: FirebaseAuth
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()

        binding.btnEnviar.setOnClickListener { logar() }
        binding.textCadastro.setOnClickListener { toCadastro() }
        binding.textEsqueciSenha.setOnClickListener { toRecuperarSenha() }
    }


    private fun logar() {
        val email = binding.textEmail.text.toString()
        val senha = binding.textSenha.text.toString()

        if (email.isEmpty() || senha.isEmpty()) {
            exibirSnackbar("Preencha todos os campos!", Color.RED)
        } else {
            auth.signInWithEmailAndPassword(email, senha)
                .addOnCompleteListener(requireActivity()) { task ->
                    if (task.isSuccessful) {
                        // Login bem-sucedido, navegue para a próxima página
                        val fragment = MenuFragment()
                        val transaction = parentFragmentManager.beginTransaction()
                        transaction.replace(R.id.nav_container, fragment)
                        transaction.commit()
                    } else {
                        // Se o login não for bem-sucedido, exibir mensagem de erro
                        exibirSnackbar("Erro ao fazer login, email incorreto!", Color.RED)
                    }
                }
        }
    }


    private fun toRecuperarSenha(){
        val fragment = RecuperarSenhaFragment()
        val transaction = fragmentManager?.beginTransaction()
        transaction?.replace(R.id.nav_container, fragment)?.commit()
    }

    private fun toCadastro() {
        val fragment = CadastroFragment()
        val transaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.nav_container, fragment)
        transaction.commit()
    }

    private fun exibirSnackbar(mensagem: String, cor: Int) {
        val snackbar = Snackbar.make(requireView(), mensagem, Snackbar.LENGTH_SHORT)
        snackbar.setBackgroundTint(cor)
        snackbar.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}