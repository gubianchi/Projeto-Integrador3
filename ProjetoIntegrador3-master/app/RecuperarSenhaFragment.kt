package com.example.projetointegrador3.auth
//Este código pertence ao pacote com.example.projetointegrador3.auth.

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import com.google.firebase.auth.FirebaseAuth
import com.example.projetointegrador3.R
import android.widget.Toast
//Aqui estão as importações necessárias para as classes e recursos utilizados no código,
// incluindo componentes da interface do usuário (EditText, Button, ImageButton, Toast),
// o FirebaseAuth para autenticação no Firebase, e o recurso R.layout.fragment_recuperar_senha.

class RecuperarSenhaFragment : Fragment() {  //Declaração da classe RecuperarSenhaFragment, que estende a classe Fragment.

    private lateinit var auth: FirebaseAuth  //Declaração da variável auth que será utilizada para autenticação no Firebase. Ela é inicializada posteriormente.

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {  //Sobrescreve a função onCreateView para inflar o layout do fragmento.
        val view = inflater.inflate(R.layout.fragment_recuperar_senha, container, false)  //Infla o layout do fragmento fragment_recuperar_senha.
        auth = FirebaseAuth.getInstance()  //Inicializa a variável auth com uma instância de FirebaseAuth.

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {  //Sobrescreve a função onViewCreated para executar ações após a view do fragmento ser criada.
        super.onViewCreated(view, savedInstanceState)

        val editTextEmail = view.findViewById<EditText>(R.id.textEmailRecuperarSenha)
        val btnEnviarEmail = view.findViewById<Button>(R.id.btnRecuperarSenha)
        val backButton = view.findViewById<ImageButton>(R.id.imageButton)
        //Obtém as referências para os elementos da interface do usuário (EditText, Button, ImageButton) do layout do fragmento.

        btnEnviarEmail.setOnClickListener {
            val email = editTextEmail.text.toString()
            if (email.isNotEmpty()) {
                enviarEmailRecuperacaoSenha(email)
            } else {
                Toast.makeText(requireContext(), "Insira seu email de cadastro.", Toast.LENGTH_SHORT).show()
            }
        }
        //Define um listener de clique para o botão btnEnviarEmail.
        // Quando clicado, verifica se o campo de email não está vazio e, se não estiver,
        // chama a função enviarEmailRecuperacaoSenha(email). Caso contrário, exibe uma mensagem de
        // toast pedindo para inserir o email.
        backButton.setOnClickListener {
            // Chamando onBackPressed() para voltar para a página anterior
            requireActivity().onBackPressed()
        }

    }

    private fun enviarEmailRecuperacaoSenha(email: String) {  //Declaração da função enviarEmailRecuperacaoSenha que recebe um email como parâmetro.
        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Se o e-mail de recuperação for enviado com sucesso, exibir mensagem de sucesso
                    Toast.makeText(requireContext(), "E-mail de recuperação enviado com sucesso!", Toast.LENGTH_SHORT).show()
                } else {
                    // Se ocorrer algum erro ao enviar o e-mail de recuperação, exibir mensagem de erro
                    Toast.makeText(requireContext(), "Erro ao enviar e-mail de recuperação: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }

}