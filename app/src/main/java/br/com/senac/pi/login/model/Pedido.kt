package br.com.senac.pi.login.model

data class Pedido(
    var id_pedido: Int,
    var vl_total: Float,
    var produtos: List<ProdutoPedido>,
)