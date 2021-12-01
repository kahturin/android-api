package br.com.senac.pi.login.model

data class ProdutoPedido(
    var id_produto: Int = 0,
    var nm_produto: String = "",
    var desc_produto: String = "",
    var img_produto: String? = "",
    var vl_produto: Float = 0f,
    var qtd_produto: Int = 0,
    var vl_total: Float = 0f,
)
