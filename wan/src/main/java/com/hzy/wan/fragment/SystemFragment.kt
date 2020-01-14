package com.hzy.wan.fragment


import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder

import com.hzy.wan.R
import com.hzy.wan.activity.SysActivity
import com.hzy.wan.activity.WebViewActivity
import com.hzy.wan.bean.SystemBean
import com.hzy.wan.jump
import com.hzy.wan.viewmodel.SystemViewModel

import com.ju.baselibrary.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_system.*
import java.io.Serializable


/**
 * 体系
 */
class SystemFragment : BaseFragment() {

    lateinit var mViewModel: SystemViewModel
    lateinit var adapter: MyAdapter
    var mList = ArrayList<SystemBean.DataBean>()
    override fun init() {
        title_bar.setPageTitle("体系")
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_system
    }

    override fun initView(view: View?) {
        swipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(mContext, R.color.theme))
        mViewModel = ViewModelProvider(this)[SystemViewModel::class.java]
        recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        adapter = MyAdapter(null)
        recyclerView.adapter = adapter
        swipeRefreshLayout.setOnRefreshListener {
            mViewModel.getSysType()
        }
        adapter?.setOnItemClickListener { _, view, position ->
            val bundle = Bundle()
            bundle.putString("title", adapter?.getItem(position)?.name)
            bundle.putSerializable("child", adapter?.getItem(position)?.children as Serializable)
            jump(SysActivity::class.java, bundle)

        }
    }

    override fun initData() {
        mViewModel.getSysType()
        mViewModel.systemBean.observe(this, Observer {
            swipeRefreshLayout.isRefreshing = false
            if (it.data.size > 0) {
                mList.addAll(it.data)
                adapter?.setNewData(mList)
                adapter?.loadMoreComplete()
            } else {
            }
        })
    }


    class MyAdapter(var list: List<SystemBean.DataBean>?) :
            BaseQuickAdapter<SystemBean.DataBean, BaseViewHolder>(R.layout.item_official_accounts, list) {

        override fun convert(helper: BaseViewHolder?, item: SystemBean.DataBean?) {
            helper?.setText(R.id.tv_title, item?.name)
            var sb = StringBuilder()
            item?.children?.forEach {
                sb.append(it.name)
                sb.append("   ")
            }
            helper?.setText(R.id.tv_date, sb.toString())
        }
    }
}
