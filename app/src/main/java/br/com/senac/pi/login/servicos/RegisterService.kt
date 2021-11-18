package br.com.senac.pi.login.servicos

import br.com.senac.pi.login.model.Login
import br.com.senac.pi.login.model.Register
import retrofit2.Call
import retrofit2.http.*

interface RegisterService {
    @Headers("Content-Type: application/json;charset=UTF-8", "Accept: application/json")
    @POST("/api/register")
    fun registrar(@Body registro: Register, @Header("Authorization") authorization: String):Call<Login>
}