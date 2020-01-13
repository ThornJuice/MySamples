package com.hzy.wan

import android.view.View
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import com.hzy.wan.fragment.HomeFragment
import com.hzy.wan.fragment.OfficialAccountsFragment
import com.hzy.wan.fragment.ProjectsFragment
import com.hzy.wan.fragment.SystemFragment
import com.ju.baselibrary.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), BottomNavigationBar.OnTabSelectedListener {
    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    private var fragmentManager: FragmentManager? = null
    private var transaction: FragmentTransaction? = null
    private var HomeFragment: HomeFragment? = null
    private var customerFragment: OfficialAccountsFragment? = null
    private var housingFragment: ProjectsFragment? = null
    private var mineFragment: SystemFragment? = null


    override fun initView() {
        baseTitleBar.visibility = View.GONE
        fragmentManager = supportFragmentManager
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED)
        bottomNavigationBar
                .addItem(BottomNavigationItem(R.mipmap.icon_nav_01, "首页"))
                .addItem(BottomNavigationItem(R.mipmap.icon_nav_02, "公众号"))
                .addItem(BottomNavigationItem(R.mipmap.icon_nav_03, "体系"))
                .addItem(BottomNavigationItem(R.mipmap.icon_nav_04, "项目"))
                .setActiveColor(R.color.theme)
                .setFirstSelectedPosition(0)
                .setBarBackgroundColor(R.color.white)
                .initialise()
        bottomNavigationBar.setTabSelectedListener(this)
        initHomeFragment()
    }


    override fun initData() {
    }

    private fun initHomeFragment() {
        transaction = fragmentManager!!.beginTransaction()
        if (HomeFragment == null) {
            HomeFragment = HomeFragment()
            transaction!!.add(R.id.container, HomeFragment!!)
        }
        //隐藏所有fragment
        hideFragment(transaction!!)
        //显示待办fragment
        transaction!!.show(HomeFragment!!)
        transaction!!.commit()
    }

    private fun initCustomerFragment() {
        transaction = fragmentManager!!.beginTransaction()
        if (customerFragment == null) {
            customerFragment = OfficialAccountsFragment()
            transaction!!.add(R.id.container, customerFragment!!)
        }
        //隐藏所有fragment
        hideFragment(transaction!!)
        //显示客户fragment
        transaction!!.show(customerFragment!!)
        transaction!!.commit()
    }

    private fun initHousingFragment() {
        transaction = fragmentManager!!.beginTransaction()
        if (housingFragment == null) {
            housingFragment = ProjectsFragment()
            transaction!!.add(R.id.container, housingFragment!!)
        }
        //隐藏所有fragment
        hideFragment(transaction!!)
        //显示房源fragment
        transaction!!.show(housingFragment!!)
        transaction!!.commit()
    }

    private fun initMineFragment() {
        transaction = fragmentManager!!.beginTransaction()
        if (mineFragment == null) {
            mineFragment = SystemFragment()
            transaction!!.add(R.id.container, mineFragment!!)
        }
        //隐藏所有fragment
        hideFragment(transaction!!)
        //显示我的fragment
        transaction!!.show(mineFragment!!)
        transaction!!.commit()
    }

    //隐藏所有的fragment
    private fun hideFragment(transaction: FragmentTransaction) {
        if (HomeFragment != null) {
            transaction.hide(HomeFragment!!)
        }
        if (customerFragment != null) {
            transaction.hide(customerFragment!!)
        }
        if (housingFragment != null) {
            transaction.hide(housingFragment!!)
        }
        if (mineFragment != null) {
            transaction.hide(mineFragment!!)
        }
    }

    override fun onTabReselected(position: Int) {
    }

    override fun onTabUnselected(position: Int) {
    }


    override fun onTabSelected(position: Int) {
        when (position) {
            0 -> initHomeFragment()
            1 -> initCustomerFragment()
            2 -> initMineFragment()
            3 -> initHousingFragment()
            else -> {

            }
        }
    }
}
