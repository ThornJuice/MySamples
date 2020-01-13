package com.hzy.wan.fragment


import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.hzy.wan.R
import com.hzy.wan.activity.WebViewActivity
import com.hzy.wan.bean.ProjectBean
import com.hzy.wan.jump
import com.hzy.wan.viewmodel.ProjectViewModel
import com.ju.baselibrary.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_project_list.*

/**
 * 项目列表
 *
 */
class ProjectListFragment : BaseFragment() {
    lateinit var mViewModel: ProjectViewModel
    lateinit var adapter: MyAdapter
    var mList = ArrayList<ProjectBean.DataBean.DatasBean>()
    var page = 1
    var mId: Int = 0
    override fun init() {
        mId = arguments!!.getInt("id")
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_project_list
    }

    override fun initView(view: View?) {
        mViewModel = ViewModelProvider(this)[ProjectViewModel::class.java]
        recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        adapter = MyAdapter(null)
        recyclerView.adapter = adapter
        adapter?.setEnableLoadMore(true)
        adapter?.setOnLoadMoreListener({
            mViewModel.getProject(page,mId)
        }, recyclerView)
        adapter?.disableLoadMoreIfNotFullPage()
        swipeRefreshLayout.setOnRefreshListener {
            page = 1
            mViewModel.getProject(page,mId)
        }
        adapter?.setOnItemClickListener { _, view, position ->
            val bundle = Bundle()
            bundle.putString("url", adapter?.getItem(position)?.link)
            jump(WebViewActivity::class.java, bundle)

        }
    }

    override fun initData() {
        mViewModel.getProject(page,mId)
        mViewModel.projectBean.observe(this, Observer {
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
    }


    class MyAdapter(var list: List<ProjectBean.DataBean.DatasBean>?) :
            BaseQuickAdapter<ProjectBean.DataBean.DatasBean, BaseViewHolder>(R.layout.item_home_article, list) {

        override fun convert(helper: BaseViewHolder?, item: ProjectBean.DataBean.DatasBean?) {
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