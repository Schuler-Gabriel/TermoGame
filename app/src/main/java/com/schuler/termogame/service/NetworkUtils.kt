package com.schuler.termogame.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkUtils {
    companion object {
        fun getRetrofitInstance(path: String): Retrofit{
            return Retrofit.Builder()
                .baseUrl("https://significado.herokuapp.com/v2/$path")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}