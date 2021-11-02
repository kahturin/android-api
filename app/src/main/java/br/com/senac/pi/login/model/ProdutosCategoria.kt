package br.com.senac.pi.login.model

data class ProdutosCategoria (
    var categoria: String = "",
    var produtos: List<Produto>?,
)