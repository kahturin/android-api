package br.com.senac.pi.login

import android.content.Intent
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import br.com.senac.pi.R
import br.com.senac.pi.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.cadastrarLogin.setOnClickListener{
            val intent = Intent(applicationContext,TelaCadastroActivity::class.java)
            startActivity(intent)
        }

        binding.entrarLogin.setPaintFlags(binding.entrarLogin.getPaintFlags() or Paint.UNDERLINE_TEXT_FLAG)

        binding.buttonEntrar.setOnClickListener {
            preencherCampo(binding.edittEmailAddress)
            preencherCampo(binding.editPassword)
        }
    }

    fun preencherCampo(text: EditText){
        if (text.text.isNullOrEmpty()){
            text.error = "preencha este campo"
            binding.editPassword.requestFocus()
            binding.edittEmailAddress.requestFocus()
        }
    }
}