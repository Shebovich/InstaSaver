package com.shebovich.instasaver.network

import com.shebovich.instasaver.Profile
import retrofit2.Call

import retrofit2.http.GET

interface RetrofitServices {
    @GET("marvel")
    fun getMovieList(): Call<MutableList<Profile>>
}