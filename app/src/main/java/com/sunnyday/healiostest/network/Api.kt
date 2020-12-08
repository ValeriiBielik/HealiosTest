package com.sunnyday.healiostest.network

import com.sunnyday.healiostest.network.util.LiveDataCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Api {
    companion object {
        private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

        fun create(): ApiService {

            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(LiveDataCallAdapterFactory())
                .addConverterFactory(
                    GsonConverterFactory.create()
                )
                .baseUrl(BASE_URL)
                .build()

            return retrofit.create(ApiService::class.java)
        }
    }
}