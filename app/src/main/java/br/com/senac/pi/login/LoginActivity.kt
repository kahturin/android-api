package br.com.senac.pi.login

import android.content.Intent
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import br.com.senac.pi.MinhasComprasActivity
import br.com.senac.pi.R
import br.com.senac.pi.databinding.ActivityLoginBinding
import br.com.senac.pi.login.model.Login
import br.com.senac.pi.login.servicos.LoginService
import br.com.senac.pi.login.servicos.token
import br.com.senac.pi.login.servicos.url
import br.com.senac.pi.login.servicos.usuario
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val login = Login()

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.cadastrarLogin.setOnClickListener{
            val intent = Intent(applicationContext,TelaCadastroActivity::class.java)
            startActivity(intent)
        }

        binding.entrarLogin.paintFlags = binding.entrarLogin.paintFlags or Paint.UNDERLINE_TEXT_FLAG

        binding.buttonEntrar.setOnClickListener {
            //preencherCampo(binding.edittEmailAddress)
            //preencherCampo(binding.editPassword)

            //login.password = binding.editPassword.text.toString()
            //login.email = binding.edittEmailAddress.text.toString()
            login.password = "senha123321"
            login.email = "djalma@djalma.com"




            val client: OkHttpClient = OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build()

            val rt: Retrofit? =  Retrofit.Builder().baseUrl(url).addConverterFactory(
                GsonConverterFactory.create()).client(client).build()

            rt?.let {
                val ser = rt.create(LoginService::class.java)

                val call = ser.login(login)

                val callback = object : Callback<Login> {

                    override fun onResponse(call: Call<Login>, response: Response<Login>) {
                        if (response.isSuccessful) {
                            val l = response.body()
                            l?.let {
                               usuario = l.nome
                                token = l.token
                                redirectHome()
                            }
                        } else {
                            Toast.makeText(
                                this@LoginActivity,
                                "Usuario ou senha inválido",
                                Toast.LENGTH_LONG
                            ).show()
                            Log.e(response.code().toString(), response.errorBody().toString())
                        }
                    }

                    override fun onFailure(call: Call<Login>, t: Throwable) {
                        Toast.makeText(this@LoginActivity, "Erro", Toast.LENGTH_LONG).show()
                        Log.e("loginActivity", "login", t)
                    }
                }
                call.enqueue(callback)
            }
        }
    }
    fun preencherCampo(text: EditText){
        if (text.text.isNullOrEmpty()){
            text.error = "preencha este campo"
            binding.editPassword.requestFocus()
            binding.edittEmailAddress.requestFocus()
        }
    }

    fun redirectHome(){
       // val i = Intent(this@LoginActivity, HomeActivity::class.java)
        //i.putExtra("usuario", usuario)
        //i.putExtra("token", token)
        //startActivity(i)

        val i = Intent(this@LoginActivity, MinhasComprasActivity::class.java)
        i.putExtra("usuario", usuario)
        i.putExtra("token", token)
        startActivity(i)
    }
}