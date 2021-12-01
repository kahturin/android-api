package br.com.senac.pi.login.servicos

import br.com.senac.pi.login.model.Login
import br.com.senac.pi.login.model.MsgRetorno
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST



interface LoginService {
    @Headers("Content-Type: application/json;charset=UTF-8", "Accept: application/json")
    @POST("/api/login")
    fun login(@Body login: Login): Call<Login>

    @Headers("Content-Type: application/json;charset=UTF-8", "Accept: application/json")
    @POST("/api/logout")
    fun logout(@Header("Authorization") autorization: String): Call<MsgRetorno>
}