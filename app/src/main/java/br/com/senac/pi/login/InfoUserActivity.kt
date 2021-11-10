package br.com.senac.pi.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import br.com.senac.pi.R
import br.com.senac.pi.login.model.InfoUser
import br.com.senac.pi.login.servicos.InfoUserService
import br.com.senac.pi.login.servicos.url
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class InfoUserActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_user)
        var usuario: String = ""
        var token:String = ""

        val btnEdit = findViewById<Button>(R.id.editBtnSalvar)

        btnEdit.setOnClickListener {
            hello()
        }


        val bundle :Bundle ?=intent.extras
        if (bundle!=null){
            usuario = bundle.getString("usuario").toString()
            token =bundle.getString("token").toString()

            val client: OkHttpClient = OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build()

            val rt: Retrofit? =  Retrofit.Builder().baseUrl(url).addConverterFactory(
                GsonConverterFactory.create()).client(client).build()
            rt?.let {
                val ser = rt.create(InfoUserService::class.java)

                val call = ser.perfil(token)

                val callback = object : Callback<InfoUser> {

                    override fun onResponse(call: Call<InfoUser>, response: Response<InfoUser>) {
                        if(response.isSuccessful){
                            val retorno = response.body()
                            retorno?.let {
                                val resp = it.info
                                resp?.let {
                                    findViewById<TextView>(R.id.editUserNome).text = usuario
                                    findViewById<TextView>(R.id.infoUserSobrenome).text = resp.sobrenome
                                    findViewById<TextView>(R.id.infoUserCep).text = resp.cep
                                    findViewById<TextView>(R.id.infoUserEndereco).text = resp.endereco
                                    findViewById<TextView>(R.id.infoUserNumero).text = resp.numero
                                    findViewById<TextView>(R.id.infoUserComplemento).text = resp.complemento
                                    findViewById<TextView>(R.id.infoUserBairro).text = resp.bairro
                                    findViewById<TextView>(R.id.infoUserCidade).text = resp.cidade
                                    findViewById<TextView>(R.id.infoUserEstado).text = resp.estado
                                }
                            }
                        }
                    }

                    override fun onFailure(call: Call<InfoUser>, t: Throwable) {
                        Log.e("InfoUserActivity", "Perfil", t)
                    }
                }
                call.enqueue(callback)
            }

        }
    }

    fun hello(){
        Toast.makeText(this, "Funfo", Toast.LENGTH_LONG).show()
    }
}