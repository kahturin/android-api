package br.com.senac.pi.login.model

data class ProdutoPedido(
    var id_produto: Int,
    var nm_produto: String,
    var desc_produto: String,
    var img_produto: String?,
    var vl_produto: Float,
    var qtd_produto: Int,
    var vl_total: Float,
)
