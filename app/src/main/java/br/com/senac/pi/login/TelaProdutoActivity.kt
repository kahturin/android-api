package br.com.senac.pi.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.senac.pi.ConfirmarPedidoActivity
import br.com.senac.pi.R
import br.com.senac.pi.databinding.ActivityConfirmarPedidoBinding
import br.com.senac.pi.databinding.ActivityLoginBinding
import br.com.senac.pi.databinding.ActivityTelaCadastroBinding
import br.com.senac.pi.databinding.ActivityTelaProdutoBinding
import kotlin.reflect.KClass

class TelaProdutoActivity : AppCompatActivity() {
    lateinit var binding: ActivityTelaProdutoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_produto)

        binding = ActivityTelaProdutoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button3.setOnClickListener{
            val i = Intent(this@TelaProdutoActivity, ConfirmarPedidoActivity::class.java)
            startActivity(i)
        }
    }
}