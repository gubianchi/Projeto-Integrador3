package com.example.projetointegrador3.auth
//Declara o pacote no qual esta classe está localizada.

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.projetointegrador3.databinding.FragmentLoginBinding
import android.graphics.Color
import com.example.projetointegrador3.MenuFragment
import com.example.projetointegrador3.R
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
//Importa classes e recursos necessários para o funcionamento do fragmento, incluindo classes para manipular bundles,
//manipulação de fragments, criação de layouts, tratamento de cores, navegação entre fragmentos, exibição de Snackbars
//e autenticação com Firebase.


class LoginFragment : Fragment(R.layout.fragment_login) { //Declara a classe LoginFragment, que é uma subclasse de Fragment, e define o layout associado a este fragmento.

    private lateinit var auth: FirebaseAuth
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    //Declara propriedades privadas para armazenar a referência à instância de FirebaseAuth e ao binding do layout.

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }
    //Cria e retorna a hierarquia de visualização associada ao fragmento, inicializando o binding do layout.

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()

        binding.btnEnviar.setOnClickListener { logar() }
        binding.textCadastro.setOnClickListener { toCadastro() }
        binding.textEsqueciSenha.setOnClickListener { toRecuperarSenha() }
    }
    //Executa após a criação da view, inicializa a instância de FirebaseAuth, define listeners para os cliques nos botões de enviar, cadastro e esqueci a senha.


    private fun logar() { //Função chamada quando o botão de login é clicado.
        val email = binding.textEmail.text.toString()
        val senha = binding.textSenha.text.toString()
        //Extrai os dados inseridos nos campos de email e senha.

        if (email.isEmpty() || senha.isEmpty()) { //Verifica se os campos de email e senha estão vazios e exibe um Snackbar se estiverem.
            exibirSnackbar("Preencha todos os campos!", Color.RED)
        } else { // Tenta fazer login com Firebase Auth
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


    private fun toRecuperarSenha(){  //Função chamada para navegar para a tela de recuperação de senha.
        val fragment = RecuperarSenhaFragment()
        val transaction = fragmentManager?.beginTransaction()
        transaction?.replace(R.id.nav_container, fragment)?.commit()
    }

    private fun toCadastro() { //Função chamada para navegar para a tela de recuperação de senha.
        val fragment = CadastroFragment()
        val transaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.nav_container, fragment)
        transaction.commit()
    }

    private fun exibirSnackbar(mensagem: String, cor: Int) {  //Função para exibir um Snackbar com uma mensagem específica e uma cor específica.
        val snackbar = Snackbar.make(requireView(), mensagem, Snackbar.LENGTH_SHORT)
        snackbar.setBackgroundTint(cor)
        snackbar.show()
    }

    override fun onDestroyView() {  //Executa quando a view do fragmento está sendo destruída, desvincula o binding do layout.
        super.onDestroyView()
        _binding = null
    }
}