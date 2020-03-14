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
import com.hzy.wan.adapter.BannerViewHolder
import com.hzy.wan.bean.BannerBean
import com.hzy.wan.bean.HomeArticleBean
import com.hzy.wan.dismissLoadDialog
import com.hzy.wan.initLoadDialog
import com.hzy.wan.jump
import com.hzy.wan.viewmodel.HomeViewModel
import com.ju.baselibrary.base.BaseFragment
import com.ju.baselibrary.callback.RetryClickListener
import com.zhpan.bannerview.BannerViewPager
import com.zhpan.bannerview.adapter.OnPageChangeListenerAdapter
import com.zhpan.bannerview.constants.IndicatorSlideMode
import com.zhpan.bannerview.constants.IndicatorStyle
import com.zhpan.bannerview.constants.PageStyle
import kotlinx.android.synthetic.main.fragment_home.*
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get


/**
 * 首页
 *
 */
class HomeFragment : BaseFragment() {
    private var  bannerViewPager: BannerViewPager<BannerBean.DataBean, BannerViewHolder>?=null
    lateinit var mViewModel: HomeViewModel
    lateinit var adapter: MyAdapter
    var bannerView:View?=null
    var mList = ArrayList<HomeArticleBean.DataBean.DatasBean>()
    var page = 1
    override fun init() {
        title_bar.setPageTitle("首页")
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun initView(view: View?) {
        mLoadHolder = swipeRefreshLayout.initLoadDialog(RetryClickListener { mViewModel.getData(page) })
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
        initBanner()
        adapter?.addHeaderView(bannerView)

    }

    override fun initData() {
        //showLoadDialog()
        mLoadHolder.showLoading()
        mViewModel.getData(page)
        mViewModel.getBanner()
        mViewModel.articleLd.observe(viewLifecycleOwner, Observer {
            swipeRefreshLayout.isRefreshing = false
            dismissLoadDialog()
            mLoadHolder.showLoadSuccess()
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
//        mViewModel.bannerLd.observe(this, Observer {
//            bannerViewPager?.create(it.data)
//        })
        mViewModel.bannerLd.observe(this,object : Observer<BannerBean>{
            override fun onChanged(t: BannerBean?) {
                bannerViewPager?.create(t?.data)
            }
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
    private fun initBanner(){
        bannerView = layoutInflater.inflate(R.layout.layout_home_banner,null)
        bannerViewPager = bannerView?.findViewById(R.id.bannerViewPager)
        bannerViewPager?.run {
            setAutoPlay(true)
                    .setPageStyle(PageStyle.MULTI_PAGE_SCALE)
                    .setIndicatorSlideMode(IndicatorSlideMode.SMOOTH)
                    .setIndicatorStyle(IndicatorStyle.ROUND_RECT)
                    .setInterval(5000)
                    .setScrollDuration(1200)
                    .setIndicatorColor(ContextCompat.getColor(mContext,R.color.white7f),ContextCompat.getColor(mContext,R.color.white))
                    .setHolderCreator { BannerViewHolder() }
                    .setOnPageChangeListener(object : OnPageChangeListenerAdapter() {
                        override fun onPageSelected(position: Int) {
                            super.onPageSelected(position)

                        }
                    })
                    .setOnPageClickListener { position ->
                        val bundle = Bundle()
                        bundle.putString("url", bannerViewPager?.list?.get(position)?.url)
                        jump(WebViewActivity::class.java, bundle)
                    }
        }
    }
}
