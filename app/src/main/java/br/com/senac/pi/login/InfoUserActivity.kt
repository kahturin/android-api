package br.com.senac.pi.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import br.com.senac.pi.R
import br.com.senac.pi.databinding.ActivityInfoUserBinding
import br.com.senac.pi.login.model.InfoUser
import br.com.senac.pi.login.servicos.InfoUserService
import br.com.senac.pi.login.servicos.retrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InfoUserActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_user)

        var usuario: String = ""
        var token:String = ""
        var infoUser: InfoUser?

        val bundle :Bundle ?=intent.extras
        if (bundle!=null){
            usuario = bundle.getString("usuario").toString()
            token =bundle.getString("token").toString()

            val rt = retrofit
            rt?.let {
                val ser = rt.create(InfoUserService::class.java)

                val call = ser.perfil(token)

                val callback = object : Callback<InfoUser> {

                    override fun onResponse(call: Call<InfoUser>, response: Response<InfoUser>) {
                        if(response.isSuccessful){
                            val resp = response.body()
                            resp?.let {
                                infoUser = resp
                            }
                        }
                    }

                    override fun onFailure(call: Call<InfoUser>, t: Throwable) {
                        Toast.makeText(this@InfoUserActivity, "Erro", Toast.LENGTH_LONG).show()
                        Log.e("InfoUserActivity", "Perfil", t)
                    }
                }
                call.enqueue(callback)
            }

        }
    }
}