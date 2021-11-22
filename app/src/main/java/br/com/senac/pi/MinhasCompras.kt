package br.com.senac.pi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.senac.pi.databinding.ActivityMinhasComprasBinding
import br.com.senac.pi.login.adapter.adapaterPedidos
import br.com.senac.pi.login.model.Pedidos

class MinhasCompras : AppCompatActivity() {
    lateinit var binding: ActivityMinhasComprasBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMinhasComprasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recycleViewCompras.layoutManager = LinearLayoutManager(this)
        binding.recycleViewCompras.setHasFixedSize(true)

        //Configurando o Adapter
        val listaPedidos: MutableList<Pedidos> = mutableListOf()
        val adapaterPedidos = adapaterPedidos(this,listaPedidos)
        binding.recycleViewCompras.adapter = adapaterPedidos

        val pedido1 = Pedidos(
            "Enviado",
            "#6546561",
            R.drawable.apple
        )

        listaPedidos.add(pedido1)

        val pedido2 = Pedidos(
            "Cancelado",
            "#6545861",
            R.drawable.apple
        )

        listaPedidos.add(pedido2)
    }
}