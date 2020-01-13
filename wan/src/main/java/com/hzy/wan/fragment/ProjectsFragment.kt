package com.hzy.wan.fragment


import android.os.Bundle
import android.view.View
import androidx.annotation.Nullable
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayout
import com.hzy.wan.R
import com.hzy.wan.adapter.ViewPageAdapter
import com.hzy.wan.bean.ProjectTypeBean
import com.hzy.wan.viewmodel.ProjectViewModel
import com.ju.baselibrary.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_projects.*


/**
 * 项目
 *
 */
class ProjectsFragment : BaseFragment() {
    lateinit var mViewModel: ProjectViewModel
    var fragmentsList = ArrayList<ProjectListFragment>()
    override fun init() {
        title_bar.setPageTitle("项目")
    }

    override fun initView(view: View?) {
    }

    override fun initData() {
        mViewModel = ViewModelProvider(this)[ProjectViewModel::class.java]
        getData()
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_projects
    }

    private fun setVpData(list: List<ProjectTypeBean.DataBean>) {
        val tabs = ArrayList<String>()
        for (i in list.indices) {
            tabs.add(list[i].name)
            val fragment = ProjectListFragment()
            val bundle = Bundle()
            bundle.putInt("id", list[i].id)
            fragment.arguments = bundle
            fragmentsList.add(fragment)
        }
        val adapter = object : ViewPageAdapter(childFragmentManager, fragmentsList) {
            @Nullable
            override fun getPageTitle(position: Int): CharSequence {
                return tabs[position]
            }
        }
        viewPager.adapter = adapter
//        viewPager.offscreenPageLimit = tabs.size
        tabLayout.setupWithViewPager(viewPager)

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })
    }

    private fun getData() {
        mViewModel.getProjectType()
        mViewModel.projectTypeBean.observe(this, Observer {
            setVpData(it.data)
        })
    }
}
