package br.com.senac.pi.login

import android.app.DownloadManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import br.com.senac.pi.R
import br.com.senac.pi.databinding.ActivityRegistroBinding
import br.com.senac.pi.login.model.InfoUser
import br.com.senac.pi.login.servicos.*
import com.squareup.picasso.Picasso
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RegistroActivity : AppCompatActivity() {
    lateinit var binding: ActivityRegistroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        fun onResume() {
//            super.onResume()
//
//            if (token != "") {
//                getPerfil()
//            } else {
//                redirectLogin()
//            }
//        }
//
//        fun getPerfil() {
//            val bundle: Bundle? = intent.extras
//            if (bundle != null) {
//                usuario = bundle.getString("usuario").toString()
//                token = bundle.getString("token").toString()
//
//                val client: OkHttpClient = OkHttpClient.Builder()
//                    .connectTimeout(30, TimeUnit.SECONDS)
//                    .readTimeout(30, TimeUnit.SECONDS)
//                    .build()
//
//                val rt: Retrofit? = Retrofit.Builder().baseUrl(url).addConverterFactory(
//                    GsonConverterFactory.create()
//                ).client(client).build()
//                rt?.let {
//                    val ser = rt.create(RegisterService::class.java)
//
//                    val call = ser.registrar("Bearer $token")
//
//                    val callback = object : Callback<InfoUser> {
//
//                        override fun onResponse(
//                            call: Call<InfoUser>,
//                            response: Response<InfoUser>
//                        ) {
//                            if (response.isSuccessful) {
//                                val retorno = response.body()
//                                retorno?.let {
//                                    val resp = it.info
//                                    resp?.let {
//                                        findViewById<TextView>(binding.).text = usuario
//                                        findViewById<TextView>(R.id.infoUserSobrenome).text =
//                                            resp.sobrenome
//                                        findViewById<TextView>(R.id.infoUserCep).text = resp.cep
//                                        findViewById<TextView>(R.id.infoUserEndereco).text =
//                                            resp.endereco
//                                        findViewById<TextView>(R.id.infoUserNumero).text =
//                                            resp.numero
//                                        findViewById<TextView>(R.id.infoUserComplemento).text =
//                                            resp.complemento
//                                        findViewById<TextView>(R.id.infoUserBairro).text =
//                                            resp.bairro
//                                        findViewById<TextView>(R.id.infoUserCidade).text =
//                                            resp.cidade
//                                        findViewById<TextView>(R.id.infoUserEstado).text =
//                                            resp.estado
//
//
//                                        resp.img_user?.let {
//                                            val card = findViewById<ImageView>(R.id.userImage)
//                                            val path = urlImage + resp.img_user
//
//                                            Picasso.get().load(path)
//                                                .error(R.drawable.ic_launcher_background)
//                                                .into(card)
//                                        }
//                                    }
//                                }
//                            }
//                        }
//
//                        override fun onFailure(call: Call<InfoUser>, t: Throwable) {
//                            Log.e("InfoUserActivity", "Perfil", t)
//                        }
//                    }
//                    call.enqueue(callback)
//
//                }
//            }
//        }
//
//        fun redirectEditUser() {
//            val i: Intent = Intent(this, EditInfoUserActivity::class.java)
//            i.putExtra("usuario", usuario)
//            i.putExtra("token", token)
//            startActivity(i)
//        }
//
//        fun redirectLogin() {
//            val i: Intent = Intent(this, LoginActivity::class.java)
//            startActivity(i)
//        }
    }
}