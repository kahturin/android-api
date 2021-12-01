package br.com.senac.pi.services

import br.com.senac.pi.login.model.Produto
import retrofit2.Call
import retrofit2.http.*

interface ProdutoService {
    @GET("/android/rest/produto")
    fun listar(): Call<List<Produto>>

    //parametro de url
    @GET("/android/rest/produto/{nome}")
    fun pesquisar(nome: String): Call<List<Produto>>

    //Parametro de consulta (query ?nome=Nome)
    @GET("/android/rest/produto/{nome}")
    fun pesquisar2(@Query("nome")nome: String): Call<List<Produto>>

    @POST("/android/rest/produto")
    fun inserir(@Body produto: Produto): Call<Produto>

    @PUT("/android/rest/produto")
    fun atualizar(@Body produto: Produto, @Query ("id") id: Int): Call<Produto>

    @DELETE("/android/rest/produto")
    fun excluir(@Query("id") id: Int): Call<Produto>
}