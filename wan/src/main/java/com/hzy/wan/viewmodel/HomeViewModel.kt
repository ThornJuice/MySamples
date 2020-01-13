package com.hzy.wan.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hzy.wan.bean.BannerBean
import com.hzy.wan.bean.HomeArticleBean
import com.hzy.wan.http.RetrofitManager
import kotlinx.coroutines.*

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
                articleLd.value = data

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
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
}

