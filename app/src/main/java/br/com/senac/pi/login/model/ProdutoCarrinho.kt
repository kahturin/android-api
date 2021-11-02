package br.com.senac.pi.login.model

data class ProdutoCarrinho(
    var id_produto: Int = 0,
    var nm_produto: String = "",
    var vl_produto: Float = 0f,
    var img_produto: String? = "",
    var qtd_produto: Int = 0,
)