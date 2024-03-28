package com.ehyundai.project.data.api

import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    const val BASE_URL = "https://server-gunskids.koyeb.app/api/v1/"
}


object RetrofitClient {
    private const val BASE_URL = "https://server-gunskids.koyeb.app/api/v1/"
    private var apiService: ApiInterface? = null

    fun getApiService(token: String): ApiInterface {

        val httpClient = OkHttpClient.Builder()

        // 토큰을 헤더에 추가하는 Interceptor 추가
        httpClient.addInterceptor { chain ->
            val original: Request = chain.request()
            val requestBuilder: Request.Builder = original.newBuilder()
                .header("Authorization", "Bearer $token") // 토큰 추가
            val request: Request = requestBuilder.build()
            val method = original.method()
            val body = original.body()
            // 메소드와 바디가 원본과 동일한지 확인 후 설정
            if (method != null && body != null) {
                requestBuilder.method(method, body)
            }

            chain.proceed(request)
        }

        if (apiService == null) {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build()
            apiService = retrofit.create(ApiInterface::class.java)
        }
        return apiService!!
    }
}