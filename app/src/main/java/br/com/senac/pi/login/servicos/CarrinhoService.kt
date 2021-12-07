package br.com.senac.pi.login.servicos

import br.com.senac.pi.login.model.MsgRetorno
import br.com.senac.pi.login.model.Produto
import br.com.senac.pi.login.model.ProdutoCarrinho
import retrofit2.Call
import retrofit2.http.*

interface CarrinhoService {
    @Headers("Content-Type: application/json;charset=UTF-8", "Accept: application/json")
    @GET("/api/produtos/carrinho")
    fun getCarrinho(@Header("Autorization") authorization: String): Call<List<ProdutoCarrinho>?>

    @Headers("Content-Type: application/json;charset=UTF-8", "Accept: application/json")
    @DELETE("/api/produto/carrinho/:id")
    fun removerProdutoCarrinho(@Path("id") idProduto: Int, @Header("Autorization") authorization: String): Call<MsgRetorno>

    @Headers("Content-Type: application/json;charset=UTF-8", "Accept: application/json")
    @DELETE("/api/produtos/carrinho")
    fun removerProdutosCarrinho(@Header("Autorization") authorization: String): Call<MsgRetorno>

    @Headers("Content-Type: application/json;charset=UTF-8", "Accept: application/json")
    @POST("/api/carrinho")
    fun addProdutoCarrinho(@Header("Autorization") authorization: String, @Body id_produto:Int, @Body qtd_produto:Int ): Call<MsgRetorno>

}