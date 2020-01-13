package com.hzy.wan.fragment


import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.hzy.wan.activity.WebViewActivity
import com.hzy.wan.bean.HomeArticleBean
import com.hzy.wan.bean.SystemBean
import com.hzy.wan.jump
import com.hzy.wan.viewmodel.HomeViewModel
import com.hzy.wan.viewmodel.SystemViewModel
import com.ju.baselibrary.base.BaseFragment
import com.ju.baselibrary.utils.XLog
import kotlinx.android.synthetic.main.fragment_system.*


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
        return com.hzy.wan.R.layout.fragment_system
    }

    override fun initView(view: View?) {
        mViewModel = ViewModelProvider(this)[SystemViewModel::class.java]
        recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        adapter = MyAdapter(null)
        recyclerView.adapter = adapter
        swipeRefreshLayout.setOnRefreshListener {
            mViewModel.getSysType()
        }
        adapter?.setOnItemClickListener { _, view, position ->
            val bundle = Bundle()
            jump(WebViewActivity::class.java, bundle)

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
            BaseQuickAdapter<SystemBean.DataBean, BaseViewHolder>(com.hzy.wan.R.layout.item_official_accounts, list) {

        override fun convert(helper: BaseViewHolder?, item: SystemBean.DataBean?) {
            helper?.setText(com.hzy.wan.R.id.tv_title, item?.name)
            var string=""
            item?.children?.forEach {
                string += it.name+"   "
            }
            helper?.setText(com.hzy.wan.R.id.tv_date, string)
        }
    }
}
