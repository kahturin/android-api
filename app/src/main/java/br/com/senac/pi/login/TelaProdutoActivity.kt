package br.com.senac.pi.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.com.senac.pi.R
import br.com.senac.pi.databinding.ActivityHomeBinding
import br.com.senac.pi.databinding.ActivityLoginBinding
import br.com.senac.pi.databinding.ActivityTelaCadastroBinding
import br.com.senac.pi.databinding.ActivityTelaProdutoBinding

class TelaProdutoActivity : AppCompatActivity() {
    lateinit var binding: ActivityTelaProdutoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTelaProdutoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Toast.makeText(this, "id:${intent.getIntExtra("id", 0)}", Toast.LENGTH_LONG).show()

        binding.buttonAdd.setOnClickListener {
            var quantidade = binding.quantidade.text.toString().toInt()
            quantidade++
            binding.quantidade.text = quantidade.toString()
        }
    }
}