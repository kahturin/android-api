package br.com.senac.pi.login

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import br.com.senac.pi.R
import br.com.senac.pi.login.model.Info
import br.com.senac.pi.login.model.InfoUser
import br.com.senac.pi.login.model.MsgRetorno
import br.com.senac.pi.login.servicos.InfoUserService
import br.com.senac.pi.login.servicos.url
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class EditInfoUserActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_info_user)

        var usuario: String = ""
        var token:String = ""

        val bundle :Bundle ?=intent.extras
        bundle?.let {
            usuario = bundle.getString("usuario").toString()
            token =bundle.getString("token").toString()
        }

        if (token != ""){

        }else{
            redirectLogin(this)
        }
    }

    fun updateUser(info: Info){
        Toast.makeText(this, "Editando", Toast.LENGTH_LONG).show()

       /* val retrofit: Retrofit? = getRetrofit()

        retrofit?.let {
            val ser = it.create(InfoUserService::class.java)
            var i: Info = Info()

            val call = ser.setPerfil(i, "Bearer $token")

            val callback = object : Callback<MsgRetorno> {

                override fun onFailure(call: Call<MsgRetorno>, t: Throwable) {
                    Log.e("InfoUserActivity", "Perfil", t)
                }

                override fun onResponse(
                    call: Call<MsgRetorno>,
                    response: Response<MsgRetorno>
                ) {
                    if(response.isSuccessful){
                        val retorno = response.body()
                        retorno?.let {
                            Toast.makeText(this@EditInfoUserActivity, it.msg.toString(), Toast.LENGTH_LONG)
                            redirectInfoUserActivity(this@EditInfoUserActivity, usuario, token)
                        }
                    }
                }
            }
            call.enqueue(callback)
        }*/
    }

    fun cancelEdit(){
        Toast.makeText(this, "Cancelado", Toast.LENGTH_LONG).show()
    }

    fun redirectLogin(context: Context){
        val i: Intent = Intent(context, LoginActivity::class.java)
        startActivity(i)
    }

    fun redirectInfoUserActivity(c: Context, u: String, t: String){
        val i = Intent(c, InfoUserActivity::class.java)
        i.putExtra("usuario", u)
        i.putExtra("token", t)
        startActivity(i)
    }

    fun getRetrofit():Retrofit?{
        val client: OkHttpClient = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()

        val rt: Retrofit? =  Retrofit.Builder().baseUrl(url).addConverterFactory(
            GsonConverterFactory.create()).client(client).build()

        return rt
    }
}