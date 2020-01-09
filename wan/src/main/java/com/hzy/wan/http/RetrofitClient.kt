package com.hzy.wan.http

import com.hzy.wan.http.interceptor.HttpLoggingInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 *
 * @author  Lai
 *
 * @time 2019/9/20 15:51
 * @describe describe
 *
 */

object RetrofitClient {

    val service2 by lazy { getService(ApiService::class.java, ApiService.BASE_URL) }

    private val client: OkHttpClient
        get() {
            val builder = OkHttpClient.Builder()
            val logging = HttpLoggingInterceptor("OkHttp");
            builder.addInterceptor(logging)
                .connectTimeout(10, TimeUnit.SECONDS)
            return builder.build()
        }


    private fun <S> getService(serviceClass: Class<S>, baseUrl: String): S {
        return Retrofit.Builder()
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .addCallAdapterFactory(CoroutineCallAdapterFactory.invoke())
            .baseUrl(baseUrl)
            .build().create(serviceClass)
    }



}

