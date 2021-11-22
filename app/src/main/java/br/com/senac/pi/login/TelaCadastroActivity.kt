package br.com.senac.pi.login

import android.content.Intent
import android.graphics.Paint
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

        binding.cadastrese.setPaintFlags(binding.entrarCadastro.getPaintFlags() or Paint.UNDERLINE_TEXT_FLAG)

        binding.buttomCadastrar.setOnClickListener {
            if (binding.editNome.text.isNullOrEmpty() || binding.editEmail.text.isNullOrEmpty() || binding.editSenha.text.isNullOrEmpty() || binding.editConfirmSenha.text.isNullOrEmpty()){
                preencherCampo(binding.editNome)
                preencherCampo(binding.editEmail)
                preencherCampo(binding.editSenha)
                preencherCampo(binding.editConfirmSenha)
            } else {
                val intent = Intent(applicationContext, HomeActivity::class.java)
                startActivity(intent)
            }
        }
    }

    fun preencherCampo(text: EditText){
        if (text.text.isNullOrEmpty()){
            text.error = "preencha este campo"
            text.requestFocus()
        }
    }
}