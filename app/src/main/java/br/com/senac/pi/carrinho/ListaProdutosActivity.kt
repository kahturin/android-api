package br.com.senac.pi.carrinho

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.senac.pi.R
import br.com.senac.pi.databinding.ActivityListaProdutosBinding
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import br.com.senac.pi.services.ProdutoService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import br.com.senac.pi.login.model.Produto
import com.google.android.material.snackbar.Snackbar
import android.util.Log
import br.com.senac.pi.databinding.CardItemCarrinhoBinding

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
        val retrofit = Retrofit.Builder().baseUrl("https://oficinacordova.azurewebsites.net")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(ProdutoService::class.java)

        val vall = service.listar()


    }

    fun atuaizarUI(lista: List<Produto>){
        binding.container.removeAllViews()

        lista.forEach{
            val carBinding = CardItemCarrinhoBinding.inflate(layoutInflater)

            carBinding.textTipo.text = it.nome
            carBinding.textDesc.text = it.preco

            binding.container.addView(carBinding.root)
        }
    }

    val callback = object : Callback<List<Produto>> {
        override fun onResponse(call: Call<List<Produto>>, response: Response<List<Produto>>) {
            if(response.isSuccessful){
                val listaProdutos = response.body()
                atuaizarUI(listaProdutos)

            }else {
                //val error = response.errorBody().toString()
                Snackbar.make(binding.container, "Nao eh possivel atualizar os produtos", snackbar.LENGTH_LONG.SHOW())

                Log.e("error", response.errorBody().toString())
            }
        }

        override fun onFailure(call: Call<List<Produto>>, t: Throwable) {
            Snackbar.make(binding.container, "Nao eh possivel conctarse ao servidor", snackbar.LENGTH_LONG.SHOW())

            Log.e("error", response.errorBody().toString())
        }
    }

    call.enqueue(callback)

}