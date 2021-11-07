package br.com.senac.pi.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.senac.pi.R
import br.com.senac.pi.databinding.ActivityHomeBinding
import br.com.senac.pi.login.adapter.adapterProduto
import br.com.senac.pi.login.model.Produto

class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        var usuario: String = ""
        var token:String = ""
        val bundle :Bundle ?=intent.extras
        if (bundle!=null){
            usuario = bundle.getString("usuario").toString()
            token =bundle.getString("token").toString()
        }

        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recycleView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recycleView.setHasFixedSize(true)

        //Configurando o Adapter
        val listaProdutos: MutableList<Produto> = mutableListOf()
        val adapterProduto = adapterProduto(this,listaProdutos)
        binding.recycleView.adapter = adapterProduto

        val produto1 = Produto(
            0,
            "Quadros",
            "Apple/Android",
            0F,
            R.drawable.hp,
            0,
            "40R$"
        )

        listaProdutos.add(produto1)
        val produto2 = Produto(
            0,
            "Quadros",
            "Apple/Android",
            0F,
            R.drawable.hp,
            0,
            "40R$"
        )

        listaProdutos.add(produto2)
    }
}