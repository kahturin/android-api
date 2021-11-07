package br.com.senac.pi.login.servicos

import br.com.senac.pi.login.model.InfoUser
import br.com.senac.pi.login.model.MsgRetorno
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface InfoUserService {
    @GET("/perfil")
    fun perfil(@Header("Authorization") authorization: String): Call<InfoUser>

    @POST("/perfil/update")
    fun setPerfil(@Body info: InfoUser, @Header("Authorization") authorization: String): Call <MsgRetorno>
}