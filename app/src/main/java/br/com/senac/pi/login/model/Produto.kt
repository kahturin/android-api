package br.com.senac.pi.login.model

import android.widget.ImageView

data class Produto(
    var id: Int = 0,
    var nm_produto: String  = "",
    var desc_produto: String = "",
    var vl_produto: Float = 0F,
    var qtd_produto: Int = 0,
    var id_categoria: Int = 0,
    var img_produto: String? = "",
)
