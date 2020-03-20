package com.hzy.wan.fragment


import android.os.Bundle
import android.view.View
import androidx.annotation.Nullable
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayout
import com.hzy.wan.R
import com.hzy.wan.adapter.ViewPageAdapter
import com.hzy.wan.bean.OfficialAccountBean
import com.hzy.wan.viewmodel.OfficialViewModel
import com.ju.baselibrary.base.BaseFragment
import fragment.OfficialArticleFragment
import kotlinx.android.synthetic.main.fragment_official_accounts.*


/**
 * 公众号
 *
 */
class OfficialAccountsFragment : BaseFragment() {
    lateinit var mViewModel: OfficialViewModel
    var fragmentsList = ArrayList<OfficialArticleFragment>()
    override fun init() {
        title_bar.setPageTitle("公众号")
    }

    override fun initView(view: View?) {
    }

    override fun initData() {
        mViewModel = ViewModelProvider(this)[OfficialViewModel::class.java]
        getData()
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_official_accounts
    }

    private fun setVpData(list: List<OfficialAccountBean.DataBean>) {
        val tabs = ArrayList<String>()
        for (i in list.indices) {
            tabs.add(list[i].name)
            val fragment = OfficialArticleFragment()
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
        viewPager.offscreenPageLimit = tabs.size
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
        mViewModel.getAccount()
        mViewModel.accountBean.observe(this, Observer {
            setVpData(it.data)
        })
    }
}
