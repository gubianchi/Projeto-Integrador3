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


class RecuperarSenhaFragment : Fragment() {

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_recuperar_senha, container, false)
        auth = FirebaseAuth.getInstance()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val editTextEmail = view.findViewById<EditText>(R.id.textEmailRecuperarSenha)
        val btnEnviarEmail = view.findViewById<Button>(R.id.btnRecuperarSenha)
        val backButton = view.findViewById<ImageButton>(R.id.imageButton)

        btnEnviarEmail.setOnClickListener {
            val email = editTextEmail.text.toString()
            if (email.isNotEmpty()) {
                enviarEmailRecuperacaoSenha(email)
            } else {
                Toast.makeText(requireContext(), "Insira seu email de cadastro.", Toast.LENGTH_SHORT).show()
            }
        }
        backButton.setOnClickListener {
            // Chamando onBackPressed() para voltar para a página anterior
            requireActivity().onBackPressed()
        }

    }

    private fun enviarEmailRecuperacaoSenha(email: String) {
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