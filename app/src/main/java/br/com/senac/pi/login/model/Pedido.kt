package br.com.senac.pi.login.model

data class Pedido(
    var id_pedido: Int = 0,
    var vl_total: Float = 0f,
    var status: String = "",
    var produtos: List<ProdutoPedido>,
)