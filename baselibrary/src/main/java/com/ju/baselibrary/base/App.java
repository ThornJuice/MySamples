package com.ju.baselibrary.base;

import android.content.Context;
import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.cookie.CookieJarImpl;
import com.lzy.okgo.cookie.store.SPCookieStore;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import okhttp3.OkHttpClient;

public class App extends MultiDexApplication {
    private static App instance;
    private static Context applicationContext;
    public static App getAppInsatnce(){
        return instance;
    }
    public static Context getAppContext(){
        return applicationContext;
    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        applicationContext = getApplicationContext();
        //initNetWork();
    }
    /**
     * 配置全局的网络连接
     */
    private void initNetWork() {
        //创建请求头对象
        //HttpHeaders headers = new HttpHeaders();
        try {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            //log相关配置
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor("OkGo");
            loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY);        //log打印级别，决定了log显示的详细程度
            loggingInterceptor.setColorLevel(Level.INFO);                               //log颜色级别，决定了log在控制台显示的颜色
            builder.addInterceptor(loggingInterceptor);//添加OkGo默认debug日志
            //全局的读取超时时间  基于前面的通道建立完成后，客户端终于可以向服务端发送数据了
            builder.readTimeout(10000, TimeUnit.MILLISECONDS);
            //全局的写入超时时间  服务器发回消息，可是客户端出问题接受不到了
            builder.writeTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);
            //全局的连接超时时间  http建立通道的时间
            builder.connectTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);
            //使用sp保持cookie，如果cookie不过期，则一直有效
            builder.cookieJar(new CookieJarImpl(new SPCookieStore(this)));
            OkGo.getInstance()
                    //如果使用默认的 60秒,以下三行也不需要传
//                    .setConnectTimeout(OkGo.DEFAULT_MILLISECONDS)  //全局的连接超时时间
//                    .setReadTimeOut(OkGo.DEFAULT_MILLISECONDS)     //全局的读取超时时间
                    //.setWriteTimeOut(OkGo.DEFAULT_MILLISECONDS)    //全局的写入超时时间

//                    .setOkHttpClient(builder.build())
                    //可以全局统一设置缓存模式,默认是不使用缓存,可以不传,
                    .setCacheMode(CacheMode.REQUEST_FAILED_READ_CACHE)
                    //可以全局统一设置缓存时间,默认永不过期
                    .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)
                    //可以全局统一设置超时重连次数,默认为三次,那么最差的情况会请求4次(一次原始请求,三次重连请求),不需要可以设置为0
                    .setRetryCount(0)
                    //可以设置https的证书,

//                    .setCertificates();  //方法一：信任所有证书,不安全有风险

                    //这两行同上，不需要就不要加入
                    //.addCommonHeaders(headers); //设置全局公共头
                    .init(this); //必须调用初始化
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
