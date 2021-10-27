package br.com.senac.pi.login.model

data class ProdutoCarrinho(
    var id_produto: Int,
    var nm_produto: String,
    var vl_produto: Float,
    var img_produto: String?,
    var qtd_produto: Int,
)