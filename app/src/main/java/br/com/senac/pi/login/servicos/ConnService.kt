package br.com.senac.pi.login.servicos

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val url: String = "http://192.168.1.61:8000"

val client: OkHttpClient = OkHttpClient.Builder()
    .connectTimeout(30, TimeUnit.SECONDS)
    .readTimeout(30, TimeUnit.SECONDS)
    .build()

val retrofit: Retrofit? =  Retrofit.Builder().baseUrl(url).addConverterFactory(
    GsonConverterFactory.create()).client(client).build()
