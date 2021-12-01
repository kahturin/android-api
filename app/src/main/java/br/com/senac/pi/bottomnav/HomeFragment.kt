package br.com.senac.pi.bottomnav

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.senac.pi.R
import br.com.senac.pi.databinding.ActivityConfirmarPedidoBinding.inflate
import br.com.senac.pi.databinding.ActivityHomeBinding
import br.com.senac.pi.login.adapter.adapterProduto
import br.com.senac.pi.login.model.Produto
import br.com.senac.pi.login.servicos.ProdutoService
import br.com.senac.pi.login.servicos.url
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class HomeFragment : Fragment() {
    lateinit var binding: HomeFragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onResume() {
        super.onResume()
        getProdutos()
    }

    fun getProdutos() {
        val client: OkHttpClient = OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build()

        val rt: Retrofit? = Retrofit.Builder().baseUrl(url).addConverterFactory(
                GsonConverterFactory.create()
        ).client(client).build()

        rt?.let {
            val servico = rt.create(ProdutoService::class.java)
            val call : Call<List<Produto>> = servico.getProdutos()

            val callback = object : Callback<List<Produto>> {
                override fun onResponse(call: Call<List<Produto>>, response: Response<List<Produto>>) {
                    if (response.isSuccessful) {
                        val produtos = response.body()
                        produtos?.let {
                            reloadListProd(produtos)
                        }
                    } else {
//                        val show = Toast.makeText(this@HomeFragment, "Falha ao buscar produtos", Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<List<Produto>?>, t: Throwable) {
                    Log.e("InfoUserActivity", "Perfil", t)
                }
            }
            call.enqueue(callback)
        }
    }

    fun reloadListProd(produtos: List<Produto>) {
//        binding = ActivityHomeBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        binding.recycleView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
//        binding.recycleView.setHasFixedSize(true)

        //Configurando o Adapter
//        val listaProdutos: MutableList<Produto> = mutableListOf()
//        val adapterProduto = adapterProduto(this, listaProdutos)
//        binding.recycleView.adapter = adapterProduto
//
//        var cont = 0
//        produtos.forEach { p ->
//
//            val produto = Produto()
//
//            produto.id = p.id
//            produto.nm_produto = p.nm_produto
//            produto.vl_produto = p.vl_produto
//            produto.nm_categoria = p.nm_categoria
//
//            listaProdutos.add(cont, produto)
//            cont ++
//        }
    }
}