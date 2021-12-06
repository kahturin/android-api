package br.com.senac.pi.login.model

data class ProdutosCategoria (
    var categoria: Categoria = Categoria(0,""),
    var produtos: List<Produto>?,
)