package br.com.senac.pi.login.model

data class Produto (
    var id: String,
    var nm_produto: String,
    var desc_produto: String,
    var vl_produto: Float,
    var qtd_produto: Int,
    var id_categoria: Int,
    var img_produto: String?,
)
