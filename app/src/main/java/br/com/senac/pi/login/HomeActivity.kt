package br.com.senac.pi.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.senac.pi.R
import br.com.senac.pi.databinding.ActivityHomeBinding
import br.com.senac.pi.login.adapter.adapterProduto
import br.com.senac.pi.login.model.Produto
import br.com.senac.pi.login.servicos.*
import com.squareup.picasso.Picasso
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
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
                        Toast.makeText(this@HomeActivity, "Falha ao buscar produtos", Toast.LENGTH_LONG).show()
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
            binding = ActivityHomeBinding.inflate(layoutInflater)
            setContentView(binding.root)

            binding.recycleView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            binding.recycleView.setHasFixedSize(true)

            //Configurando o Adapter
            val adapterProduto = adapterProduto(this, produtos)
            binding.recycleView.adapter = adapterProduto
        }



    fun pesquisarProdutos(nome: String) {
        val client: OkHttpClient = OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build()

        val rt: Retrofit? = Retrofit.Builder().baseUrl(url).addConverterFactory(
                GsonConverterFactory.create()
        ).client(client).build()

        rt?.let {
            val servico = rt.create(ProdutoService::class.java)
            val call : Call<List<Produto>> = servico.produrarProduto(nome)

            val callback = object : Callback<List<Produto>> {
                override fun onResponse(call: Call<List<Produto>>, response: Response<List<Produto>>) {
                    if (response.isSuccessful) {
                        val produtos = response.body()
                        produtos?.let {
                            reloadListProd(produtos)
                        }
                    } else {
                        Toast.makeText(this@HomeActivity, "Falha ao buscar produtos com $nome", Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<List<Produto>?>, t: Throwable) {
                    Log.e("InfoUserActivity", "Perfil", t)
                }
            }
            call.enqueue(callback)
        }
    }

    fun pesquisarPorCategoria(idCategoria: Int) {
        val client: OkHttpClient = OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build()

        val rt: Retrofit? = Retrofit.Builder().baseUrl(url).addConverterFactory(
                GsonConverterFactory.create()
        ).client(client).build()

        rt?.let {
            val servico = rt.create(ProdutoService::class.java)
            val call : Call<List<Produto>> = servico.produtosCategoria(idCategoria)

            val callback = object : Callback<List<Produto>> {
                override fun onResponse(call: Call<List<Produto>>, response: Response<List<Produto>>) {
                    if (response.isSuccessful) {
                        val produtos = response.body()
                        produtos?.let {
                            reloadListProd(produtos)
                        }
                    } else {
                        Toast.makeText(this@HomeActivity, "Falha ao buscar produtos da categoria $idCategoria", Toast.LENGTH_LONG).show()
                    }
                }
                override fun onFailure(call: Call<List<Produto>?>, t: Throwable) {
                    Log.e("InfoUserActivity", "Perfil", t)
                }
            }
            call.enqueue(callback)
        }
    }
}