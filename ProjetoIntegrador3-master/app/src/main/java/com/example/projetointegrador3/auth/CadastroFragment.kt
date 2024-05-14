package com.example.projetointegrador3.auth
// Esta linha declara o pacote no qual esta classe está localizada.

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
//Essas linhas importam classes e recursos necessários para o funcionamento do fragmento,
//incluindo classes para construir diálogos de alerta, manipulação de cores, tratamento de bundles,
//manipulação de fragments, criação de layouts, exibição de Snackbars e autenticação com Firebase.

class CadastroFragment : Fragment(R.layout.fragment_cadastro) {  //Esta linha declara a classe CadastroFragment, que é uma subclasse de Fragment, e define o layout associado a este fragmento.

    private var _binding: FragmentCadastroBinding? = null
    private val binding get()= _binding!!
    private val auth = FirebaseAuth.getInstance()
    //Essas linhas declaram propriedades privadas para armazenar a referência ao binding do layout e à instância de FirebaseAuth.


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentCadastroBinding.inflate(inflater, container, false)
        return binding.root
    }
    //Este método é chamado para criar e retornar a hierarquia de visualização associada ao fragmento, e aqui o binding do layout é inicializado.

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
    //Este método é chamado após a criação da view, e aqui o listener para o botão de cadastro é definido.


    private fun cadastrar(){ //Este método é chamado quando o botão de cadastro é clicado.

        //                                        ***mais pra frente é preciso adicionar validações
        //---------COLETA DADOS DO USUÁRIO-----------para ver se o cpf é válido (tenho essa função pronta)

        val emailUsuario = binding.textCadastroEmail.text.toString().trim()// "trim" torna o campo como preenchimento obirgatório
        val nomeUsuario = binding.textCadastroNome.text.toString().trim()
        val cpfUsuario = binding.textCadastroCPF.text.toString().trim()
        val senhaUsuario = binding.textCadastroSenha.text.toString().trim()

        val alertDialogBuilder = AlertDialog.Builder(requireContext()) //Esta linha cria um construtor de diálogo de alerta.

        if (emailUsuario.isEmpty() || senhaUsuario.isEmpty() || cpfUsuario.isEmpty() || nomeUsuario.isEmpty()){
            //// Código para exibir um Snackbar se algum campo estiver vazio
            val snackbar = Snackbar.make(requireView(), "Preencha todos os campos!", Snackbar.LENGTH_SHORT)
            snackbar.setBackgroundTint(Color.RED)
            snackbar.show()
        } else {
            //// Código para criar um usuário com o email e senha fornecidos usando o Firebase Auth e define um listener para tratar o resultado.
            auth.createUserWithEmailAndPassword(emailUsuario, senhaUsuario).addOnCompleteListener { cadastro ->
                if (cadastro.isSuccessful) {
                    // Código para exibir um Snackbar se o cadastro for bem-sucedido
                    // Código para configurar um AlertDialog com um botão de retorno ao login
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

    /*
    private fun inserirDadoBD(nomeUsuario: String, emailUsuario: String, cpfUsuario: String, senhaUsuario: String){

        database =  FirebaseDatabase.getInstance().getReference("usuarios")
        val usuario = Usuario(nomeUsuario, emailUsuario, cpfUsuario, senhaUsuario)
        database.child(cpfUsuario).setValue(usuario)
    }
    */
}