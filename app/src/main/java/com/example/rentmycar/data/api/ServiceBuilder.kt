package com.example.rentmycar.data.api


import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ServiceBuilder {
    companion object {
        val BASE_URL: String = "http://10.0.2.2:8080/"

        val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }


        val client: OkHttpClient = OkHttpClient.Builder().apply {
            this.addInterceptor(interceptor)
        }.build()

        private fun getRetrofitInstance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        private val retrofit = getRetrofitInstance();


        fun <T> buildService(serviceProvider: Class<T>): T {
            return retrofit.create(serviceProvider)
        }
    }

}