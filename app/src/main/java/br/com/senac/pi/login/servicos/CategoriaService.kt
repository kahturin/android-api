package br.com.senac.pi.login.servicos

import br.com.senac.pi.login.model.Categoria
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface CategoriaService {
    @Headers("Content-Type: application/json;charset=UTF-8", "Accept: application/json")
    @GET("/api/categorias")
    fun getCategorias():Call<List<Categoria>>

    @Headers("Content-Type: application/json;charset=UTF-8", "Accept: application/json")
    @GET("/api/categoria/:id")
    fun getCategoria(@Path("id") id: Int): Call<Categoria>
}