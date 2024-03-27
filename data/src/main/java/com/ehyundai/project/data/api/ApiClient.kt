package com.ehyundai.project.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    const val BASE_URL = "https://server-gunskids.koyeb.app/api/v1/"
}


object RetrofitClient {
    private const val BASE_URL = "https://server-gunskids.koyeb.app/api/v1/"
    private var apiService: ApiInterface? = null

    fun getApiService(): ApiInterface {
        if (apiService == null) {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            apiService = retrofit.create(ApiInterface::class.java)
        }
        return apiService!!
    }
}