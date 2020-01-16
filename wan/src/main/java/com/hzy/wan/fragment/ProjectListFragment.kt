package com.hzy.wan.fragment


import android.media.Image
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.hzy.wan.*
import com.hzy.wan.activity.WebViewActivity
import com.hzy.wan.bean.ProjectBean
import com.hzy.wan.util.GlideUtil
import com.hzy.wan.util.GlideUtil2
import com.hzy.wan.viewmodel.ProjectViewModel
import com.ju.baselibrary.base.BaseLazyFragment
import com.ju.baselibrary.callback.RetryClickListener
import com.ju.baselibrary.utils.LoadingUtil
import kotlinx.android.synthetic.main.fragment_project_list.*

/**
 * 项目列表
 *
 */
class ProjectListFragment : BaseLazyFragment() {
    override fun lazyLoad() {
//        showLoadDialog()
        mLoadHolder.showLoading()
        mViewModel.getProject(page, mId)
        mViewModel.projectBean.observe(this, Observer {
            swipeRefreshLayout.isRefreshing = false
//            dismissLoadDialog()
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
    }

    lateinit var mViewModel: ProjectViewModel
    lateinit var adapter: MyAdapter
    var mList = ArrayList<ProjectBean.DataBean.DatasBean>()
    var page = 1
    var mId: Int = 0
    override fun init() {
        mId = arguments?.getInt("id") ?: 0
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_project_list
    }

    override fun initView(view: View?) {
        mLoadHolder = swipeRefreshLayout.initLoadDialog(RetryClickListener { mViewModel.getProject(page, mId) })
        swipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(mContext, R.color.theme))
        mViewModel = ViewModelProvider(this)[ProjectViewModel::class.java]
        recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        adapter = MyAdapter(null)
        recyclerView.adapter = adapter
        adapter?.setEnableLoadMore(true)
        adapter?.setOnLoadMoreListener({
            mViewModel.getProject(page, mId)
        }, recyclerView)
        adapter?.disableLoadMoreIfNotFullPage()
        swipeRefreshLayout.setOnRefreshListener {
            page = 1
            mViewModel.getProject(page, mId)
        }
        adapter?.setOnItemClickListener { _, view, position ->
            val bundle = Bundle()
            bundle.putString("url", adapter?.getItem(position)?.link)
            jump(WebViewActivity::class.java, bundle)

        }
    }


    class MyAdapter(var list: List<ProjectBean.DataBean.DatasBean>?) :
            BaseQuickAdapter<ProjectBean.DataBean.DatasBean, BaseViewHolder>(R.layout.item_project, list) {

        override fun convert(helper: BaseViewHolder, item: ProjectBean.DataBean.DatasBean?) {
            helper?.setText(R.id.tv_title, item?.title)
            if (!item?.shareUser.isNullOrBlank()) {
                helper?.setText(R.id.tv_author, "分享者：" + item?.shareUser)
            } else {
                helper?.setText(R.id.tv_author, "作者：" + item?.author)
            }
            val image = helper?.getView<ImageView>(R.id.iv_image)
            GlideUtil.instance.displayImage(item?.envelopePic, image)
            GlideUtil2.displayImage(item?.envelopePic, image)
            helper?.setText(R.id.tv_content, item?.desc)
            helper?.setText(R.id.tv_date, item?.niceShareDate)
        }
    }

}