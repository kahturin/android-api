package br.com.senac.pi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.senac.pi.databinding.ActivityMinhasComprasBinding
import br.com.senac.pi.login.adapter.adapaterPedidos
import br.com.senac.pi.login.model.Pedido
import br.com.senac.pi.login.model.Pedidos
import br.com.senac.pi.login.model.Produto
import br.com.senac.pi.login.servicos.PedidosService
import br.com.senac.pi.login.servicos.ProdutoService
import br.com.senac.pi.login.servicos.token
import br.com.senac.pi.login.servicos.url
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class MinhasCompras : AppCompatActivity() {
    lateinit var binding: ActivityMinhasComprasBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onResume() {
        super.onResume()

        getPedidos()
    }

    fun getPedidos(){
        val client: OkHttpClient = OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build()

        val rt: Retrofit? = Retrofit.Builder().baseUrl(url).addConverterFactory(
                GsonConverterFactory.create()
        ).client(client).build()

        rt?.let {
            val servico = rt.create(PedidosService::class.java)
            val call : Call<List<Pedido>> = servico.getPedidos("Bearer $token")

            val callback = object : Callback<List<Pedido>> {
                override fun onResponse(call: Call<List<Pedido>>, response: Response<List<Pedido>>) {
                    if (response.isSuccessful) {
                        val pedidos = response.body()
                        pedidos?.let {
                            listarPedidos(pedidos)
                        }
                    } else {
                        Toast.makeText(this@MinhasCompras, "Falha ao buscar produtos", Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<List<Pedido>?>, t: Throwable) {
                    Log.e("InfoUserActivity", "Perfil", t)
                }
            }
            call.enqueue(callback)
        }
    }

    fun listarPedidos(pedidos: List<Pedido>){
        binding = ActivityMinhasComprasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recycleViewCompras.layoutManager = LinearLayoutManager(this)
        binding.recycleViewCompras.setHasFixedSize(true)

        //Configurando o Adapter
        val listaPedidos: MutableList<Pedidos> = mutableListOf()
        val adapaterPedidos = adapaterPedidos(this,listaPedidos)
        binding.recycleViewCompras.adapter = adapaterPedidos

        var cont = 0
        pedidos.forEach { p ->
            val pedido = Pedidos()

            pedido.numero = p.id_pedido.toString()
            pedido.status = p.status
            pedido.vl_total = p.vl_total


            listaPedidos.add(cont, pedido)
            cont ++
        }
    }
}