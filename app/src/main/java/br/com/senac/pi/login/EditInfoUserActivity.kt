package br.com.senac.pi.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import br.com.senac.pi.R
import br.com.senac.pi.login.model.Info
import br.com.senac.pi.login.model.InfoUser
import br.com.senac.pi.login.model.MsgRetorno
import br.com.senac.pi.login.servicos.*
import com.squareup.picasso.Picasso
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

        val btnSalvar = findViewById<Button>(R.id.editBtnSalvar)
        val btnCancelar = findViewById<Button>(R.id.editBtnCancelar)

        findViewById<EditText>(R.id.editUserNome).setText(usuario)

        btnSalvar.setOnClickListener {
            updateUser()
        }

        btnCancelar.setOnClickListener {
            cancelEdit(usuario, token)
        }
    }
    override fun onResume() {
        super.onResume()

        if(token != ""){
            obterPerfilUser()
        } else {
            redirectLogin()
        }
    }

    fun updateUser(){
        var info = Info()

        usuario = findViewById<EditText>(R.id.editUserNome).text.toString()
        info.name = findViewById<EditText>(R.id.editUserNome).text.toString()
        info.sobrenome = findViewById<EditText>(R.id.editUserSobrenome).text.toString()
        info.cep = findViewById<EditText>(R.id.editUserCep).text.toString()
        info.endereco = findViewById<EditText>(R.id.editUserEndereco).text.toString()
        info.numero = findViewById<EditText>(R.id.editUserNumero).text.toString()
        info.complemento = findViewById<EditText>(R.id.editUserComplemento).text.toString()
        info.bairro = findViewById<EditText>(R.id.editUserBairro).text.toString()
        info.cidade = findViewById<EditText>(R.id.editUserCidade).text.toString()
        info.estado = findViewById<EditText>(R.id.editUserEstado).text.toString()

        val retrofit: Retrofit? = getRetrofit()

        retrofit?.let {
            val ser = it.create(InfoUserService::class.java)

            val call = ser.setPerfil(info, "Bearer $token")

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
                            redirectInfoUserActivity()
                        }
                    }
                }
            }
            call.enqueue(callback)
        }
    }

    fun cancelEdit(usuario: String, token: String){
        Toast.makeText(this, "Cancelado", Toast.LENGTH_LONG).show()
        redirectInfoUserActivity()
    }

    fun redirectLogin(){
        val i: Intent = Intent(this, LoginActivity::class.java)
        startActivity(i)
    }

    fun redirectInfoUserActivity(){
        val i = Intent(this, InfoUserActivity::class.java)
        i.putExtra("usuario", usuario)
        i.putExtra("token", token)
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

    fun obterPerfilUser(){
        val rt: Retrofit? = getRetrofit()
        rt?.let {
            val ser = rt.create(InfoUserService::class.java)

            val call = ser.perfil("Bearer $token")

            val callback = object : Callback<InfoUser> {

                override fun onResponse(call: Call<InfoUser>, response: Response<InfoUser>) {
                    if(response.isSuccessful){
                        val retorno = response.body()
                        retorno?.let {
                            val resp = it.info
                            resp?.let {

                                findViewById<EditText>(R.id.editUserNome).setText(usuario)
                                resp.sobrenome?.let {
                                    findViewById<EditText>(R.id.editUserSobrenome).setText(resp.sobrenome)
                                }
                                resp.cep?.let {
                                    findViewById<EditText>(R.id.editUserCep).setText(resp.cep)
                                }
                                resp.endereco?.let {
                                    findViewById<EditText>(R.id.editUserEndereco).setText(resp.endereco)
                                }
                                resp.numero?.let {
                                    findViewById<EditText>(R.id.editUserNumero).setText(resp.numero)
                                }
                                resp.complemento?.let {
                                    findViewById<EditText>(R.id.editUserComplemento).setText(resp.complemento)
                                }
                                resp.bairro?.let {
                                    findViewById<EditText>(R.id.editUserBairro).setText(resp.bairro)
                                }
                                resp.cidade?.let {
                                    findViewById<EditText>(R.id.editUserCidade).setText(resp.cidade)
                                }
                                resp.estado?.let {
                                    findViewById<EditText>(R.id.editUserEstado).setText(resp.estado)
                                }
                                resp.img_user?.let {
                                    val card = findViewById<ImageView>(R.id.userImage)
                                    val path = urlImage +resp.img_user

                                    Picasso.get().load(path)
                                        .error(R.drawable.ic_launcher_background)
                                        .into(card)
                                }
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