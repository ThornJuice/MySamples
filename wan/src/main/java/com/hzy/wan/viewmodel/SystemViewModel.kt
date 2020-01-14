package com.hzy.wan.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hzy.wan.bean.SystemArticleBean
import com.hzy.wan.bean.SystemBean
import com.hzy.wan.http.RetrofitManager
import kotlinx.coroutines.*

class SystemViewModel : ViewModel() {
    private val viewModelJob = SupervisorJob()
    private val coroutineScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    var systemBean = MutableLiveData<SystemBean>()
    var systemArticleBean = MutableLiveData<SystemArticleBean>()
    fun getSysType() {
        coroutineScope.launch {
            try {
                val data = withContext(Dispatchers.IO) {
                    RetrofitManager.getInstance().create().getSysType()
                }
                systemBean.value = data

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getArticle(page: Int, id: Int) {
        coroutineScope.launch {
            try {
                val data = withContext(Dispatchers.IO) {
                    RetrofitManager.getInstance().create().getSysArticle(page, id)
                }
                systemArticleBean.value = data

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

