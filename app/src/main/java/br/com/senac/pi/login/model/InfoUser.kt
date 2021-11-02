package br.com.senac.pi.login.model

data class InfoUser (
    var id: Int? = 0,
    var sobrenome: String? = "",
    var cep: String? = "",
    var endereco: String? = "",
    var numero: String? = "",
    var complemento: String? = "",
    var bairro: String? = "",
    var cidade: String? = "",
    var estado: String? = "",
    var img_user: String? = "",
    var id_user: Int? = 0,
)