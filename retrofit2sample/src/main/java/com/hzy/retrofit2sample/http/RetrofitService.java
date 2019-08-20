package com.hzy.retrofit2sample.http;



import com.hzy.retrofit2sample.bean.WeatherInfo;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RetrofitService {
    String BASE_URL = "http://www.weather.com.cn/";
    @GET("adat/sk/{cityId}.html")
    Flowable<WeatherInfo> getWeatherInfo(@Path("cityId") String cityId);
    @GET("adat/sk/{cityId}.html")
    Flowable<ResponseBody> getWeatherInfo2(@Path("cityId") String cityId);
}
