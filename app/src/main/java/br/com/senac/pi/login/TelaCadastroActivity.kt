package br.com.senac.pi.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import br.com.senac.pi.R
import br.com.senac.pi.databinding.ActivityLoginBinding
import br.com.senac.pi.databinding.ActivityTelaCadastroBinding

class TelaCadastroActivity : AppCompatActivity() {
    lateinit var binding: ActivityTelaCadastroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_cadastro)

        binding = ActivityTelaCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.entrarCadastro.setOnClickListener{
            val intent = Intent(applicationContext,LoginActivity::class.java)
            startActivity(intent)
        }

        binding.buttomCadastrar.setOnClickListener {
            preencherCampo(binding.editNome)
            preencherCampo(binding.editEmail)
            preencherCampo(binding.editSenha)
            preencherCampo(binding.editConfirmSenha)
        }
    }

    fun preencherCampo(text: EditText){
        if (text.text.isNullOrEmpty()){
            text.error = "preencha este campo"
            binding.editNome.requestFocus()
        }
    }
}