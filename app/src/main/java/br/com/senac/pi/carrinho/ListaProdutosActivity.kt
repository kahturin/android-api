package br.com.senac.pi.carrinho

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.senac.pi.R
import br.com.senac.pi.databinding.ActivityListaProdutosBinding
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import br.com.senac.pi.login.servicos.ProdutoService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import br.com.senac.pi.login.model.Produto
import com.google.android.material.snackbar.Snackbar
import android.util.Log
import br.com.senac.pi.databinding.CardItemCarrinhoBinding
import br.com.senac.pi.login.servicos.url
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

class ListaProdutosActivity : AppCompatActivity() {
    lateinit var binding: ActivityListaProdutosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_produtos)
    }

    override fun onResume(){
        super.onResume()
        atualizarProdutos()
    }

    fun atualizarProdutos(){
        val client: OkHttpClient = OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build()

        val rt: Retrofit? = Retrofit.Builder().baseUrl(url).addConverterFactory(
                GsonConverterFactory.create()
        ).client(client).build()

        rt?.let {

            val service = rt.create(ProdutoService::class.java)

            val call : Call<List<Produto>> = service.getProdutos()


            val callback = object : Callback<List<Produto>> {
                override fun onResponse(call: Call<List<Produto>>, response: Response<List<Produto>>) {
                    if (response.isSuccessful) {
                        val listaProdutos = response.body()
                        listaProdutos.let {
                            atuaizarUI(it)
                        }

                    } else {
                        //val error = response.errorBody().toString()
                        Snackbar.make(binding.container, "Nao eh possivel atualizar os produtos", Snackbar.LENGTH_LONG).show()

                        Log.e("error", response.errorBody().toString())
                    }
                }

                override fun onFailure(call: Call<List<Produto>>, t: Throwable) {
                    Snackbar.make(binding.container, "Nao eh possivel conctarse ao servidor", Snackbar.LENGTH_LONG).show()

                    Log.e("ListProductActivity", "Falha ao obter produtos", t)
                }
            }

            call.enqueue(callback)
        }

    }

    fun atuaizarUI(lista: List<Produto>?){
        binding.container.removeAllViews()
        lista?.let {
            lista.forEach{
                val carBinding = CardItemCarrinhoBinding.inflate(layoutInflater)

                carBinding.textTipo.text = it.nm_produto
                carBinding.textDesc.text = it.vl_produto.toString()

                binding.container.addView(carBinding.root)
            }
        }
    }

}