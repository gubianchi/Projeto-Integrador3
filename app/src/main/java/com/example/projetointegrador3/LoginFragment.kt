package com.example.projetointegrador3

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.projetointegrador3.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginFragment : Fragment(R.layout.fragment_login) {

    private val email = "gu_vbianchi@hotmail.com"
    private var senha = "1234"

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

        binding.btnEnviar.setOnClickListener{logar()}

        binding.textCadastro.setOnClickListener{toCadastro()}

        binding.textEsqueciSenha.setOnClickListener{toRecuperarSenha()}
    }

    private fun logar(){

        val emailUsuario = binding.textEmail.text.toString()
        val senhaUsuario = binding.textSenha.text.toString()

        if((this.email == emailUsuario)&&(this.senha == senhaUsuario)){
            val fragment = MenuFragment() //navigation para próxima página
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.nav_container, fragment)?.commit()
        }else{
            val alertDialogBuilder = AlertDialog.Builder(requireContext())

            alertDialogBuilder.setTitle("Credencias incorretas!")
            alertDialogBuilder.setMessage("Confira seu email ou senha.")
            alertDialogBuilder.create()
            alertDialogBuilder.show()
        }
    }

    private fun toCadastro(){
        val fragment = CadastroFragment()
        val transaction = fragmentManager?.beginTransaction()
        transaction?.replace(R.id.nav_container, fragment)?.commit()
    }

    private fun toRecuperarSenha(){
        val fragment = RecuperarSenhaFragment()
        val transaction = fragmentManager?.beginTransaction()
        transaction?.replace(R.id.nav_container, fragment)?.commit()
    }
}