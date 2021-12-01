package br.com.senac.pi.login.model

data class User(
    var id: Int = 0,
    var name: String = "",
    var email: String = "",
    var nivel: String = "",
    var info: InfoUser?,
)