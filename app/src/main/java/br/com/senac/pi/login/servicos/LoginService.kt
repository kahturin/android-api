package br.com.senac.pi.login.servicos

import br.com.senac.pi.login.model.Login
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST



interface LoginService {
    @POST("/api/login")
    fun login(@Body login: Login): Call<Login>
}