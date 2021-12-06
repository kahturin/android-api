package br.com.senac.pi.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import br.com.senac.pi.R
import br.com.senac.pi.login.EditInfoUserActivity
import br.com.senac.pi.login.LoginActivity
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

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PerfilFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PerfilFragment : Fragment() {
//    var textUser = view?.findViewById<TextView>(R.id.editUserNomeFrag)
//    var sbnome = view?.findViewById<TextView>(R.id.infoUserSobrenomeFrag)
//    var cep = view?.findViewById<TextView>(R.id.infoUserCepFrag)
//    var endereco = view?.findViewById<TextView>(R.id.infoUserEnderecoFrag)
//    var numero = view?.findViewById<TextView>(R.id.infoUserNumeroFrag)
//    var complemento = view?.findViewById<TextView>(R.id.infoUserComplementoFrag)
//    var bairro = view?.findViewById<TextView>(R.id.infoUserBairroFrag)
//    var cidade = view?.findViewById<TextView>(R.id.infoUserCidadeFrag)
//    var estado = view?.findViewById<TextView>(R.id.infoUserEstadoFrag)
//    var card = view?.findViewById<ImageView>(R.id.userImageFrag)
    var btEdit: Button? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        if(token != ""){
            getPerfil()
        } else {
            redirectLogin()
        }

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_perfil, container, false)
    }

    fun getPerfil() {

        val client: OkHttpClient = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()

        val rt: Retrofit? = Retrofit.Builder().baseUrl(url).addConverterFactory(
            GsonConverterFactory.create()
        ).client(client).build()
        rt?.let {
            val ser = rt.create(InfoUserService::class.java)

            val call = ser.perfil("Bearer $token")

            val callback = object : Callback<InfoUser> {

                override fun onResponse(call: Call<InfoUser>, response: Response<InfoUser>) {
                    if (response.isSuccessful) {
                        val retorno = response.body()
                        retorno?.let {
                            val resp = it.info
                            resp?.let {
                                view?.findViewById<TextView>(R.id.editUserNomeFrag)?.text = usuario
                                view?.findViewById<TextView>(R.id.infoUserSobrenomeFrag)?.text = it.sobrenome
                                view?.findViewById<TextView>(R.id.infoUserCepFrag)?.text = it.cep
                                view?.findViewById<TextView>(R.id.infoUserEnderecoFrag)?.text =it.endereco
                                view?.findViewById<TextView>(R.id.infoUserNumeroFrag)?.text = it.numero
                                view?.findViewById<TextView>(R.id.infoUserComplementoFrag)?.text = it.complemento
                                view?.findViewById<TextView>(R.id.infoUserBairroFrag)?.text = it.bairro
                                view?.findViewById<TextView>(R.id.infoUserCidadeFrag)?.text = it.cidade
                                view?.findViewById<TextView>(R.id.infoUserEstadoFrag)?.text = it.estado

                                btEdit = view?.findViewById(R.id.editBtnFrag)
                                btEdit?.let {
                                    it.setOnClickListener {
                                        redirectEditUser()
                                    }
                                }

                                resp.img_user?.let {
                                    val card = view?.findViewById<ImageView>(R.id.userImageFrag)
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
    fun redirectEditUser() {
        val i: Intent = Intent(activity, EditInfoUserActivity::class.java)
        startActivity(i)
    }

    fun redirectLogin(){
        val i: Intent = Intent(activity, LoginActivity::class.java)
        startActivity(i)
    }
}