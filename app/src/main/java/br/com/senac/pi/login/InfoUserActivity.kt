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
import br.com.senac.pi.login.servicos.retrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InfoUserActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_user)

        val btnEdit = findViewById<Button>(R.id.infoUserBtnEdit)

        btnEdit.setOnClickListener {
            hello()
        }

        var usuario: String = "djalma"
        var token:String = "Bearer 21|43lo6cQpXZG3CpwA1mKIB0bE0QeXhSTjILhU8vjK"


        //val bundle :Bundle ?=intent.extras
       // if (bundle!=null){
            //usuario = bundle.getString("usuario").toString()
            //token =bundle.getString("token").toString()

            val rt = retrofit
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
                                    findViewById<TextView>(R.id.infoUserNome).text = usuario
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
                        Toast.makeText(this@InfoUserActivity, "Erro", Toast.LENGTH_LONG).show()
                        Log.e("InfoUserActivity", "Perfil", t)
                    }
                }
                call.enqueue(callback)
            }

        }
    //}

    fun hello(){
        Toast.makeText(this@InfoUserActivity, "Funfo", Toast.LENGTH_LONG).show()
    }
}