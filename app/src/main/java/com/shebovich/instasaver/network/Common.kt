package com.shebovich.instasaver.network

object Common {
    private val BASE_URL = "https://api.instagram.com/v1/"
    val retrofitService: RetrofitServices
        get() = RetrofitClient.getClient(BASE_URL).create(RetrofitServices::class.java)
}