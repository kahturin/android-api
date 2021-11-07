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
import br.com.senac.pi.login.servicos.retrofit
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
            //preencherCampo(binding.edittEmailAddress)
            //preencherCampo(binding.editPassword)

            //login.password = binding.editPassword.text.toString()
            //login.email = binding.edittEmailAddress.text.toString()
            login.password = "senha123321"
            login.email = "djalma@djalma.com"


            val rt = retrofit
            rt?.let {
                val ser = rt.create(LoginService::class.java)

                val call = ser.login(login)

                val callback = object : Callback<Login> {

                    override fun onResponse(call: Call<Login>, response: Response<Login>) {
                        if (response.isSuccessful) {
                            val l = response.body()
                            l?.let {
                               val usuario = l.nome
                               val token = l.token
                                goToHome(usuario, token)
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

    fun goToHome(u: String, t:String){
        val i = Intent(this@LoginActivity, HomeActivity::class.java)
        i.putExtra("usuario", u)
        i.putExtra("token", t)
        startActivity(i)
    }
}