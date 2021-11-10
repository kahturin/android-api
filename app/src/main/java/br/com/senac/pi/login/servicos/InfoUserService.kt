package br.com.senac.pi.login.servicos

import br.com.senac.pi.login.model.Info
import br.com.senac.pi.login.model.InfoUser
import br.com.senac.pi.login.model.MsgRetorno
import retrofit2.Call
import retrofit2.http.*

interface InfoUserService {
    @Headers("Content-Type: application/json;charset=UTF-8", "Accept: application/json")
    @GET("/api/perfil")
    fun perfil(@Header("Authorization") authorization: String): Call<InfoUser>

    @POST("/api/perfil/update")
    fun setPerfil(@Body info: Info, @Header("Authorization") authorization: String): Call <MsgRetorno>
}