package br.com.senac.pi.login

import android.content.Intent
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import br.com.senac.pi.R
import br.com.senac.pi.databinding.ActivityLoginBinding
import br.com.senac.pi.login.model.Login
import br.com.senac.pi.login.servicos.LoginService
import br.com.senac.pi.login.servicos.getRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
            preencherCampo(binding.edittEmailAddress)
            preencherCampo(binding.editPassword)

            login.email = binding.edittEmailAddress.toString()
            login.password = binding.editPassword.toString()

            val rt = getRetrofit()
            Toast.makeText(
                this@LoginActivity,
                login.email + "-" + login.password,
                Toast.LENGTH_LONG
            ).show()
            rt?.let {
                val ser = rt.create(LoginService::class.java)

                val call = ser.login(login)

                val callback = object : Callback<Login> {

                    override fun onResponse(call: Call<Login>, response: Response<Login>) {
                        if (response.isSuccessful) {
                            val l = response.body()
                            l?.let {
                                println(l.nome)
                                println(l.token)
                            }
                        } else {
                            Toast.makeText(
                                this@LoginActivity,
                                "Erro ao realizar login",
                                Toast.LENGTH_LONG
                            ).show()
                            Log.e(response.code().toString(), response.errorBody().toString())
                        }
                    }

                    override fun onFailure(call: Call<Login>, t: Throwable) {
                        Toast.makeText(this@LoginActivity, "Erro", Toast.LENGTH_LONG).show()
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
}