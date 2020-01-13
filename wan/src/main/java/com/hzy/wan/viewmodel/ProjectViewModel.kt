package com.hzy.wan.viewmodel

import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hzy.wan.bean.BannerBean
import com.hzy.wan.bean.HomeArticleBean
import com.hzy.wan.bean.ProjectBean
import com.hzy.wan.bean.ProjectTypeBean
import com.hzy.wan.http.RetrofitManager
import kotlinx.coroutines.*

class ProjectViewModel : ViewModel() {
    private val viewModelJob = SupervisorJob()
    private val coroutineScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    var projectTypeBean = MutableLiveData<ProjectTypeBean>()
    var projectBean = MutableLiveData<ProjectBean>()
    fun getProjectType() {
        coroutineScope.launch {
            try {
                val data = withContext(Dispatchers.IO) {
                    RetrofitManager.getInstance().create().getProjectType()
                }
                projectTypeBean.value = data

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getProject(page: Int,id: Int) {
        coroutineScope.launch {
            try {
                val data = withContext(Dispatchers.IO) {
                    RetrofitManager.getInstance().create().getProjectList(page,id)
                }
                projectBean.value = data

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

