package br.com.senac.pi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.senac.pi.databinding.ActivityConfirmarPedidoBinding
import br.com.senac.pi.databinding.ActivityTelaProdutoBinding

class ConfirmarPedidoActivity : AppCompatActivity() {
    lateinit var binding: ActivityConfirmarPedidoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfirmarPedidoBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_confirmar_pedido)

        a
    }
}