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
import com.hzy.wan.activity.WebViewActivity
import com.hzy.wan.bean.SystemArticleBean
import com.hzy.wan.jump
import com.hzy.wan.viewmodel.SystemViewModel
import com.ju.baselibrary.base.BaseLazyFragment
import kotlinx.android.synthetic.main.fragment_sys_list.*


/**
 * 体系列表
 */
class SysListFragment : BaseLazyFragment() {


    lateinit var mViewModel: SystemViewModel
    lateinit var adapter: MyAdapter
    var mList = ArrayList<SystemArticleBean.DataBean.DatasBean>()
    var page = 0
    var mId: Int = 0
    override fun init() {
        mId = arguments!!.getInt("id")
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_sys_list
    }

    override fun initView(view: View?) {
        swipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(mContext, R.color.theme))
        mViewModel = ViewModelProvider(this)[SystemViewModel::class.java]
        recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        adapter = MyAdapter(null)
        recyclerView.adapter = adapter
        adapter?.setEnableLoadMore(true)
        adapter?.setOnLoadMoreListener({
            mViewModel.getArticle(page, mId)
        }, recyclerView)
        adapter?.disableLoadMoreIfNotFullPage()
        swipeRefreshLayout.setOnRefreshListener {
            page = 1
            mViewModel.getArticle(page, mId)
        }
        adapter?.setOnItemClickListener { _, view, position ->
            val bundle = Bundle()
            bundle.putString("url", adapter?.getItem(position)?.link)
            jump(WebViewActivity::class.java, bundle)

        }
    }

    override fun lazyLoad() {
        mViewModel.getArticle(page, mId)
        mViewModel.systemArticleBean.observe(this, Observer {
            swipeRefreshLayout.isRefreshing = false
            if (page == 0) {
                mList.clear()
                if (it.data.datas.size > 0) {
                    mList.addAll(it.data.datas)
                    adapter?.setNewData(mList)
                    page++
                    adapter?.loadMoreComplete()
                } else {
                    adapter?.setNewData(null)
                    adapter?.loadMoreEnd()
                }
            } else {
                if (it.data.datas.size > 0) {
                    mList.addAll(it.data.datas)
                    adapter?.setNewData(mList)
                    adapter?.loadMoreComplete()
                    page++
                } else {
                    adapter?.loadMoreEnd()
                }
            }
        })
    }

    class MyAdapter(var list: List<SystemArticleBean.DataBean.DatasBean>?) :
            BaseQuickAdapter<SystemArticleBean.DataBean.DatasBean, BaseViewHolder>(R.layout.item_official_accounts, list) {

        override fun convert(helper: BaseViewHolder, item: SystemArticleBean.DataBean.DatasBean?) {
            helper?.setText(R.id.tv_title, item?.title)
            helper?.setText(R.id.tv_date, item?.niceDate)
        }
    }

}