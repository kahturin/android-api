package br.com.senac.pi.login.servicos

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val url: String = "http://192.168.56.1:8000"

var usuario: String = ""
var token: String = ""
var urlImage: String = "$url/storage/"

