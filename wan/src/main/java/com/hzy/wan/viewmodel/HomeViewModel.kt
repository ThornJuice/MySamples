package com.hzy.wan.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.hzy.wan.bean.BannerBean
import com.hzy.wan.bean.HomeArticleBean
import com.hzy.wan.http.RetrofitManager
import com.just.agentweb.LogUtils
import kotlinx.coroutines.*
import java.lang.reflect.GenericDeclaration

class HomeViewModel : ViewModel() {

    private val viewModelJob = SupervisorJob()
    private val coroutineScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    var articleLd = MutableLiveData<HomeArticleBean>()
    var bannerLd = MutableLiveData<BannerBean>()
    fun getData(page: Int) {
        coroutineScope.launch {
            try {
                val data = withContext(Dispatchers.IO) {
                    RetrofitManager.getInstance().create().getHomeArticle(page)
                }
                //articleLd.value = data
                articleLd.postValue(data)

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

//        coroutineScope.launch(Dispatchers.IO) {
//            val data =   RetrofitManager.getInstance().create().getHomeArticle(page)
//            launch(Dispatchers.Main) {
//                articleLd.value = data
//            }
//        }
    }

    fun getBanner() {
        coroutineScope.launch {
            try {
                val data = withContext(Dispatchers.IO) {
                    RetrofitManager.getInstance().create().getHomeBanner()
                }
                bannerLd.value = data

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        coroutineScope.cancel()
    }

    fun <T> fill(array: Array<in T>, str: T) {
        array[0] = str
    }

    fun <T> copy(array: Array<out T>, array2: Array<in T>) {
        for (i in array.indices) {
            array2[i] = array[i]
        }
    }

}

