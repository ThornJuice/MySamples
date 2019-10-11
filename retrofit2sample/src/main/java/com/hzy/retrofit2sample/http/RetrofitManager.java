package com.hzy.retrofit2sample.http;

import com.hzy.retrofit2sample.http.interceptor.HttpLoggingInterceptor;
import com.hzy.retrofit2sample.http.interceptor.LoggingInterceptor;
import com.hzy.retrofit2sample.http.interceptor.RewriteCacheControlInterceptor;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {
    private static final long DEFAULT_TIME_OUT = 30;
    private static volatile OkHttpClient mOkHttpClient;
    private RewriteCacheControlInterceptor mRewriteCacheControlInterceptor;
    private LoggingInterceptor mLoggingInterceptor;
    private RetrofitService mService;
    private static RetrofitManager instance;
    private HttpLoggingInterceptor loggingInterceptor;
    /**
     * 配置OKHttpClient
     *
     * @return
     */
    private OkHttpClient getOkHttpClient() {
        if (mOkHttpClient == null) {
            synchronized (RetrofitManager.class) {
                if (mOkHttpClient == null) {
                    //指定缓存路径
                    //XLog.e("----------"+App.getApp().getCacheDir());
                    //File file = new File(App.getApp().getCacheDir().getAbsolutePath(), "HttpCache");
                    //Cache cache = new Cache(file, 1024 * 1024 * 10);
                    mOkHttpClient = new OkHttpClient.Builder()
                            //.cache(cache)
                            //.addInterceptor(loggingInterceptor)
                            //.addInterceptor(mRewriteCacheControlInterceptor)
                            .addInterceptor(mLoggingInterceptor)
                            .retryOnConnectionFailure(true)
                            .connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
                            .build();
                }
            }
        }
        return mOkHttpClient;
    }

    private RetrofitManager() {
        mRewriteCacheControlInterceptor = RewriteCacheControlInterceptor.getInstance();
        mLoggingInterceptor = LoggingInterceptor.getInstance();
        loggingInterceptor = new HttpLoggingInterceptor("OkGo");
        loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY);        //log打印级别，决定了log显示的详细程度
        loggingInterceptor.setColorLevel(Level.INFO);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitService.BASE_URL)
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        mService = retrofit.create(RetrofitService.class);
    }

    public RetrofitService create() {
        return mService;
    }

    public static RetrofitManager getInstance() {
        if (instance == null) {
            instance = new RetrofitManager();
        }
        return instance;
    }
}
