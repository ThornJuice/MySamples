package com.hzy.wan.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.hzy.wan.bean.WeatherInfo
import com.hzy.wan.http.RetrofitManager
import kotlinx.coroutines.*


class NetViewModel : ViewModel() {
    val viewModelJob = SupervisorJob()
    val coroutineScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    var liveData = MutableLiveData<WeatherInfo>()
      fun getData() {
        coroutineScope.launch {
            try {
                val data = withContext(Dispatchers.IO) {
                    RetrofitManager.getInstance().create().getWeatherInfo1("101010100")
                }
                liveData.value = Gson().fromJson(data.string(),WeatherInfo::class.java)

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
