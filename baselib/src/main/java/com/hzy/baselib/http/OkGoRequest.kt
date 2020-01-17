package com.hzy.baselib.http

import android.content.Context
import android.text.TextUtils

import com.alibaba.fastjson.JSON
import com.hzy.baselib.listener.SingletonHolder
import com.lzy.okgo.OkGo
import com.lzy.okgo.callback.StringCallback
import com.lzy.okgo.model.HttpHeaders
import com.lzy.okgo.model.HttpParams
import com.lzy.okgo.model.Response

import java.io.File
import java.util.HashMap

import okhttp3.MediaType
import okhttp3.RequestBody

class OkGoRequest private constructor(private val context: Context) {
    companion object : SingletonHolder<OkGoRequest, Context>(::OkGoRequest)

    private var url: String? = null
    private var params: HttpParams? = null
    private var headers: HttpHeaders? = null

    private var body: String? = null

    fun url(url: String): OkGoRequest {
        this.url = url
        return this
    }

    /**
     * 传参
     *
     * @param key   键值
     * @param value
     * @return
     */
    fun params(key: String, value: String): OkGoRequest {
        if (params == null) {
            params = HttpParams()
        }
        if (!TextUtils.isEmpty(value))
            params!!.put(key, value)
        return this
    }

    fun params(key: String, value: Int): OkGoRequest {
        if (params == null) {
            params = HttpParams()
        }
        params!!.put(key, value)
        return this
    }

    fun params(key: String, value: Float): OkGoRequest {
        if (params == null) {
            params = HttpParams()
        }
        params!!.put(key, value)
        return this
    }

    fun params(key: String, value: Long): OkGoRequest {
        if (params == null) {
            params = HttpParams()
        }
        params!!.put(key, value)
        return this
    }

    fun params(key: String, value: Double): OkGoRequest {
        if (params == null) {
            params = HttpParams()
        }
        params!!.put(key, value)
        return this
    }

    fun params(key: String, value: Boolean): OkGoRequest {
        if (params == null) {
            params = HttpParams()
        }
        params!!.put(key, value)
        return this
    }

    fun params(map: Map<String, String>): OkGoRequest {
        if (params == null) {
            params = HttpParams()
        }
        params!!.put(map)
        return this
    }

    fun params(key: String, files: List<File>): OkGoRequest {
        if (params == null) {
            params = HttpParams()
        }
        params!!.putFileParams(key, files)
        return this
    }

    /**
     * get请求 返回 string
     *
     * @param
     * @param mCallBack 回调结果
     */

    fun doGet(mCallBack: HttpStringCallBack?) {
        try {
            if (params == null) {
                params = HttpParams()
            }
            if (headers == null) {
                headers = HttpHeaders()
            }
            // headers.put("Cookie", aCache.getAsString("cookie"));
            OkGo.get<String>(url).tag(context)
                .cacheKey(url!! + params!!.toString())
                .params(params).headers(headers).execute(object : StringCallback() {
                    override fun onSuccess(response: Response<String>) {
                        val body = response.body()
                        mCallBack?.onSuccess(body)
                    }

                    override fun onCacheSuccess(response: Response<String>?) {
                        super.onCacheSuccess(response)
                        val body = response!!.body()
                        mCallBack?.onSuccess(body)
                    }

                    override fun onError(response: Response<String>) {
                        super.onError(response)
                        mCallBack?.onFailure(response.code(), "" + response.message())
                    }
                })
        } catch (e: Exception) {
            mCallBack!!.onFailure(0, "" + e.message)
            e.printStackTrace()
        }

    }

    /**
     * post 请求 返回string
     *
     * @param mCallBack
     */
    fun doPost(mCallBack: HttpStringCallBack) {
        try {
            if (params == null) {
                params = HttpParams()
            }
            if (headers == null) {
                headers = HttpHeaders()
            }
            // headers.put("Cookie", aCache.getAsString("cookie"));
            OkGo.post<String>(url).tag(context)
                .cacheKey(url!! + params!!.toString())
                .params(params).headers(headers).execute(object : StringCallback() {
                    override fun onSuccess(response: Response<String>) {
                        val body = response.body()
                        mCallBack?.onSuccess(body)
                    }

                    override fun onCacheSuccess(response: Response<String>?) {
                        super.onCacheSuccess(response)
                        val body = response!!.body()
                        mCallBack?.onSuccess(body)
                    }

                    override fun onError(response: Response<String>) {
                        super.onError(response)
                        mCallBack?.onFailure(response.code(), "" + response.message())
                    }
                })
        } catch (e: Exception) {
            mCallBack.onFailure(0, e.message)
            e.printStackTrace()
        }

    }

    fun doPostWithBody(mCallBack: HttpStringCallBack) {
        try {
            if (params == null) {
                params = HttpParams()
            }
            if (headers == null) {
                headers = HttpHeaders()
            }
            if (params != null) {
                val map = hashMapOf<String, String>()
                for ((key, value) in params!!.urlParamsMap) {
                    try {
                        if (value is List<*>) {
                            map.put(key.toString(), value[0])
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }

                }
                body = JSON.toJSONString(map)
            } else {
                body = ""
            }
            // headers.put("Cookie", aCache.getAsString("cookie"));
            headers!!.put("platform", "caseManager")
            OkGo.post<String>(url).tag(context)
                //                    .cacheKey(url + params.toString())

                .upRequestBody(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), body!!))
                .params(params).headers(headers).execute(object : StringCallback() {
                    override fun onSuccess(response: Response<String>) {
                        val body = response.body()
                        mCallBack?.onSuccess(body)
                    }

                    override fun onCacheSuccess(response: Response<String>?) {
                        super.onCacheSuccess(response)
                        val body = response!!.body()
                        mCallBack?.onSuccess(body)
                    }

                    override fun onError(response: Response<String>) {
                        super.onError(response)
                        mCallBack?.onFailure(response.code(), "" + response.message())
                    }
                })
        } catch (e: Exception) {
            mCallBack.onFailure(0, e.message)
            e.printStackTrace()
        }

    }


}
