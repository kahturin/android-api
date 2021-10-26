package br.com.senac.pi.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.senac.pi.R
import br.com.senac.pi.databinding.ActivityLoginBinding
import br.com.senac.pi.databinding.ActivityTelaCadastroBinding
import br.com.senac.pi.databinding.ActivityTelaProdutoBinding

class TelaProdutoActivity : AppCompatActivity() {
    lateinit var binding: ActivityTelaProdutoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_produto)

        binding = ActivityTelaProdutoBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}