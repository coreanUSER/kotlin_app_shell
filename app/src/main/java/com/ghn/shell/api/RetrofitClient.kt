package com.ghn.shell.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    val logger = HttpLoggingInterceptor().apply { level = Level.BASIC }

    val okHttpClient = OkHttpClient.Builder().addInterceptor(logger).build()

    private var instance : Retrofit? = null

    fun getInstance(url: String): Retrofit {
        if(instance == null) {
            instance = Retrofit.Builder()
                .baseUrl(url)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        return instance!!
    }
}