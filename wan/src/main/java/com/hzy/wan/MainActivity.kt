package com.hzy.wan

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.hzy.wan.viewmodel.NetViewModel
import com.ju.baselibrary.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {


    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initView() {

        val mViewModel = ViewModelProvider(this)[NetViewModel::class.java]
        mViewModel.getData()
        mViewModel.liveData.observe(this, Observer {
            tv_content.text = it.toString()
        })
    }

    override fun initData() {

    }
}
