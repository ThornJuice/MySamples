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
import com.hzy.wan.bean.HomeArticleBean
import com.hzy.wan.jump
import com.hzy.wan.viewmodel.HomeViewModel
import com.ju.baselibrary.base.BaseFragment
import com.ju.baselibrary.utils.XLog
import kotlinx.android.synthetic.main.fragment_home.*


/**
 * 首页
 *
 */
class HomeFragment : BaseFragment() {

    lateinit var mViewModel: HomeViewModel
    lateinit var adapter: MyAdapter
    var mList = ArrayList<HomeArticleBean.DataBean.DatasBean>()
    var page = 1
    override fun init() {
        title_bar.setPageTitle("首页")
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun initView(view: View?) {
        swipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(mContext, R.color.theme))
        mViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        adapter = MyAdapter(null)
        recyclerView.adapter = adapter
        adapter?.setEnableLoadMore(true)
        adapter?.setOnLoadMoreListener({
            mViewModel.getData(page)
        }, recyclerView)
        adapter?.disableLoadMoreIfNotFullPage()
        swipeRefreshLayout.setOnRefreshListener {
            page = 1
            mViewModel.getData(page)
        }
        adapter?.setOnItemClickListener { _, view, position ->
            val bundle = Bundle()
            bundle.putString("url", adapter?.getItem(position)?.link)
            jump(WebViewActivity::class.java, bundle)

        }
    }

    override fun initData() {
        mViewModel.getData(page)
        mViewModel.getBanner()
        mViewModel.articleLd.observe(this, Observer {
            swipeRefreshLayout.isRefreshing = false
            if (page == 1) {
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
        mViewModel.bannerLd.observe(this, Observer {
            XLog.e("size" + it.data.size)
        })
    }


    class MyAdapter(var list: List<HomeArticleBean.DataBean.DatasBean>?) :
            BaseQuickAdapter<HomeArticleBean.DataBean.DatasBean, BaseViewHolder>(R.layout.item_home_article, list) {

        override fun convert(helper: BaseViewHolder?, item: HomeArticleBean.DataBean.DatasBean?) {
            helper?.setText(R.id.tv_title, item?.title)
            if (!item?.shareUser.isNullOrBlank()) {
                helper?.setText(R.id.tv_share_name, "分享者：" + item?.shareUser)
            } else {
                helper?.setText(R.id.tv_share_name, "作者：" + item?.author)
            }
            helper?.setText(R.id.tv_type, "分类：" + item?.superChapterName)
            helper?.setText(R.id.tv_date, item?.niceShareDate)
        }
    }
}
