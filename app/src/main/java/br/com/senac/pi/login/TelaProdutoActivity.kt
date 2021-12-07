package br.com.senac.pi.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import br.com.senac.pi.ConfirmarPedidoActivity
import br.com.senac.pi.R
import br.com.senac.pi.databinding.ActivityConfirmarPedidoBinding
import br.com.senac.pi.databinding.ActivityHomeBinding
import br.com.senac.pi.databinding.ActivityLoginBinding
import br.com.senac.pi.databinding.ActivityTelaCadastroBinding
import br.com.senac.pi.databinding.ActivityTelaProdutoBinding
import br.com.senac.pi.login.model.MsgRetorno
import br.com.senac.pi.login.model.Produto
import br.com.senac.pi.login.servicos.*
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import kotlin.reflect.KClass

class TelaProdutoActivity : AppCompatActivity() {
    lateinit var binding: ActivityTelaProdutoBinding

    override fun onCreate(savedInstanceState: Bundle?) {


        if(token == ""){
            redirectLogin()
        }


        binding.buttonAdd.setOnClickListener{
            var numero = binding.quantidade.text.toString()

        }

        super.onCreate(savedInstanceState)
        binding = ActivityTelaProdutoBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_tela_produto)

        binding = ActivityTelaProdutoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button3.setOnClickListener{
//            val i = Intent(this@TelaProdutoActivity, ConfirmarPedidoActivity::class.java)
//            startActivity(i)

            val client: OkHttpClient = OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build()

            val rt: Retrofit? = Retrofit.Builder().baseUrl(url).addConverterFactory(
                GsonConverterFactory.create()
            ).client(client).build()

            rt?.let {
                val servico = rt.create(CarrinhoService::class.java)
                val call : Call<MsgRetorno> = servico.addProdutoCarrinho(token, 2, 3)

                val callback = object : Callback<MsgRetorno> {
                    override fun onResponse(call: Call<MsgRetorno>, response: Response<MsgRetorno>) {
                        if (response.isSuccessful) {
                            Toast.makeText(this@TelaProdutoActivity, response.body()?.msg, Toast.LENGTH_LONG).show()
                        } else {
                            Toast.makeText(this@TelaProdutoActivity, "Falha ao buscar produtos", Toast.LENGTH_LONG).show()
                        }
                    }

                    override fun onFailure(call: Call<MsgRetorno>, t: Throwable) {
                        Log.e("InfoUserActivity", "Perfil", t)
                    }
                }
                call.enqueue(callback)
            }
        }
    }

    fun redirectEditUser() {
        val i: Intent = Intent(this, EditInfoUserActivity::class.java)
        i.putExtra("usuario", usuario)
        i.putExtra("token", token)
        startActivity(i)
    }

    fun redirectLogin(){
        val i: Intent = Intent(this, LoginActivity::class.java)
        startActivity(i)
    }
}