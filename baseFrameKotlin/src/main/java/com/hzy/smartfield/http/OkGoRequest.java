package com.hzy.smartfield.http;

import android.content.Context;
import androidx.annotation.NonNull;
import android.text.TextUtils;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

public class OkGoRequest {
    public static OkGoRequest okGoRequest;
    private Context context;
    private String url;
    private HttpParams params;
    private HttpHeaders headers;
    private OkGoRequest(Context context) {
        this.context = context;
    }
    public static OkGoRequest get(Context context) {
        if(okGoRequest ==null){
            okGoRequest = new OkGoRequest(context);
        }
        return okGoRequest;
    }
    public OkGoRequest url(String url) {
        this.url = url;
        return this;
    }
    /**
     * 传参
     *
     * @param key   键值
     * @param value
     * @return
     */
    public OkGoRequest params(String key, String value) {
        if (params == null) {
            params = new HttpParams();
        }
        if (!TextUtils.isEmpty(value))
            params.put(key, value);
        return this;
    }
    /**
     * get请求 返回 string
     *
     * @param
     * @param mCallBack 回调结果
     */

    public void doGet(final HttpStringCallBack mCallBack) {
        try {
            if (params == null) {
                params = new HttpParams();
            }
            if (headers == null) {
                headers = new HttpHeaders();
            }
            // headers.put("Cookie", aCache.getAsString("cookie"));
            OkGo.<String>get(url).tag(context)
                    .cacheKey(url + params.toString())
                    .params(params).headers(headers).execute(new StringCallback() {
                @Override
                public void onSuccess(Response<String> response) {
                    String body = response.body();
                    if (mCallBack != null)
                        mCallBack.onSuccess(body);
                }

                @Override
                public void onCacheSuccess(Response<String> response) {
                    super.onCacheSuccess(response);
                    String body = response.body();
                    if (mCallBack != null)
                        mCallBack.onSuccess(body);
                }

                @Override
                public void onError(Response<String> response) {
                    super.onError(response);
                    if (mCallBack != null)
                        mCallBack.onFailure(response.code(), "" + response.message());
                }
            });
        } catch (Exception e) {
            mCallBack.onFailure(0, "" + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * post 请求 返回string
     *
     * @param mCallBack
     */
    public void doPost(@NonNull final HttpStringCallBack mCallBack) {
        try {
            if (params == null) {
                params = new HttpParams();
            }
            if (headers == null) {
                headers = new HttpHeaders();
            }
            // headers.put("Cookie", aCache.getAsString("cookie"));
            OkGo.<String>post(url).tag(context)
                    .cacheKey(url + params.toString())
                    .params(params).headers(headers).execute(new StringCallback() {
                @Override
                public void onSuccess(Response<String> response) {
                    String body = response.body();
                    if (mCallBack != null)
                        mCallBack.onSuccess(body);
                }

                @Override
                public void onCacheSuccess(Response<String> response) {
                    super.onCacheSuccess(response);
                    String body = response.body();
                    if (mCallBack != null)
                        mCallBack.onSuccess(body);
                }

                @Override
                public void onError(Response<String> response) {
                    super.onError(response);
                    if (mCallBack != null)
                        mCallBack.onFailure(response.code(), "" + response.message());
                }
            });
        } catch (Exception e) {
            mCallBack.onFailure(0, e.getMessage());
            e.printStackTrace();
        }
    }
    /**
     * get请求 返回 bean
     * @param
     * @param mCallBack 回调结果
     */

    public <T> void doGet(@NonNull final HttpObjectCallBack<T> mCallBack) {
        try {
            if (params == null) {
                params = new HttpParams();
            }
            if (headers == null) {
                headers = new HttpHeaders();
            }
           // headers.put("Cookie", aCache.getAsString("cookie"));
            OkGo.<String>get(url).tag(context)
                    .cacheKey(url + params.toString())
                    .params(params).headers(headers).execute(new StringCallback() {
                @Override
                public void onSuccess(Response<String> response) {
                    String body = response.body();
                    if (mCallBack != null)
                        mCallBack.JsonToBean(body);
                }

                @Override
                public void onCacheSuccess(Response<String> response) {
                    super.onCacheSuccess(response);
                    String body = response.body();
                    if (mCallBack != null)
                        mCallBack.JsonToBean(body);
                }

                @Override
                public void onError(Response<String> response) {
                    super.onError(response);
                    if (mCallBack != null)
                        mCallBack.onFailure(response.code(), "" + response.message());

                }
            });
        } catch (Exception e) {
            mCallBack.onFailure(0, "" + e.getMessage());
            e.printStackTrace();
        }
    }
    public <T> void doPost(@NonNull final HttpObjectCallBack<T> mCallBack) {
        try {
            if (params == null) {
                params = new HttpParams();
            }
            if (headers == null) {
                headers = new HttpHeaders();
            }
            // headers.put("Cookie", aCache.getAsString("cookie"));
            OkGo.<String>post(url).tag(context)
                    .cacheKey(url + params.toString())
                    .params(params).headers(headers).execute(new StringCallback() {
                @Override
                public void onSuccess(Response<String> response) {
                    String body = response.body();
                    if (mCallBack != null)
                        mCallBack.JsonToBean(body);
                }

                @Override
                public void onCacheSuccess(Response<String> response) {
                    super.onCacheSuccess(response);
                    String body = response.body();
                    if (mCallBack != null)
                        mCallBack.JsonToBean(body);
                }

                @Override
                public void onError(Response<String> response) {
                    super.onError(response);
                    if (mCallBack != null)
                        mCallBack.onFailure(response.code(), "" + response.message());

                }
            });
        } catch (Exception e) {
            mCallBack.onFailure(0, "" + e.getMessage());
            e.printStackTrace();
        }
    }
}
