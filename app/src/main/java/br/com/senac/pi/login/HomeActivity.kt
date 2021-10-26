package br.com.senac.pi.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.senac.pi.R
import br.com.senac.pi.databinding.ActivityHomeBinding
import br.com.senac.pi.login.adapter.adapterProduto
import br.com.senac.pi.login.model.Produto

class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recycleView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.recycleView.setHasFixedSize(true)

        //Configurando o Adapter
        val listaProdutos: MutableList<Produto> = mutableListOf()
        val adapterProduto = adapterProduto(this,listaProdutos)
        binding.recycleView.adapter = adapterProduto

        val produto1 = Produto(
            R.drawable.gamer,
            "Apple/Android",
            "40R$"
        )

        listaProdutos.add(produto1)

        val produto2 = Produto(
            R.drawable.apple,
            "Apple/Android",
            "40R$"
        )

        listaProdutos.add(produto2)
    }
}