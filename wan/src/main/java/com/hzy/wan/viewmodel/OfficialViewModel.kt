package com.hzy.wan.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hzy.wan.bean.OfficialAccountBean
import com.hzy.wan.bean.OfficialArticleBean
import com.hzy.wan.http.RetrofitManager
import kotlinx.coroutines.*

class OfficialViewModel : ViewModel() {
    private val viewModelJob = SupervisorJob()
    private val coroutineScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    var accountBean = MutableLiveData<OfficialAccountBean>()
    var articleBean = MutableLiveData<OfficialArticleBean>()
    fun getAccount() {
        coroutineScope.launch {
            try {
                val data = withContext(Dispatchers.IO) {
                    RetrofitManager.getInstance().create().getWxarticle()
                }
                accountBean.value = data

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getArticle(id: Int, page: Int) {
        coroutineScope.launch {
            try {
                val data = withContext(Dispatchers.IO) {
                    RetrofitManager.getInstance().create().getWxarticleList(id, page)
                }
                articleBean.value = data

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

