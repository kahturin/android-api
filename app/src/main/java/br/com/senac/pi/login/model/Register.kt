package br.com.senac.pi.login.model

data class Register(
    var name: String = "",
    var email: String = "",
    var password: String = "",
    var password_confirmation: String = ""
)
