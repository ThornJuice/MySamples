package com.hzy.wan.http


import com.hzy.wan.bean.WeatherInfo
import kotlinx.coroutines.Deferred
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    companion object {
       const val BASE_URL = "http://www.weather.com.cn/"
    }
    @GET("adat/sk/{cityId}.html")
    suspend  fun  getWeatherInfo3(@Path("cityId") cityId: String): WeatherInfo
    @GET("adat/sk/{cityId}.html")
    suspend  fun  getWeatherInfo1(@Path("cityId") cityId: String): ResponseBody
}
