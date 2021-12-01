package br.com.senac.pi.login.servicos

import br.com.senac.pi.login.model.Produto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface ProdutoService {
    @Headers("Content-Type: application/json;charset=UTF-8", "Accept: application/json")
    @GET("/api/produtos")
    fun getProdutos(): Call<List<Produto>>

    @Headers("Content-Type: application/json;charset=UTF-8", "Accept: application/json")
    @GET("/api/produto/:id")
    fun showProduto(@Path("id") idProduto: Int): Call<Produto>

    @Headers("Content-Type: application/json;charset=UTF-8", "Accept: application/json")
    @GET("/api/produto/:s")
    fun produrarProduto(@Path("s") trecho: String): Call<List<Produto>>

    @Headers("Content-Type: application/json;charset=UTF-8", "Accept: application/json")
    @GET("api/produtos/categoria/:id")
    fun produtosCategoria(@Path("id") idCategoria: Int): Call<List<Produto>>
}