package br.com.senac.pi.login.servicos

import br.com.senac.pi.login.model.MsgPedido
import br.com.senac.pi.login.model.Pedido
import retrofit2.Call
import retrofit2.http.*

interface PedidosService {
    @Headers("Content-Type: application/json;charset=UTF-8", "Accept: application/json")
    @GET("/api/pedidos")
    fun getPedidos(@Header("Authorization") authorization: String): Call<List<Pedido>>

    @Headers("Content-Type: application/json;charset=UTF-8", "Accept: application/json")
    @POST("/api/pedido")
    fun gerarPedido(@Header("Authorization") authorization: String): Call<MsgPedido>
}