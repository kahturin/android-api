package br.com.senac.pi.login.model

data class User(
    var id: Int,
    var name: String,
    var email: String,
    var nivel: String,
    var info: InfoUser?,
)