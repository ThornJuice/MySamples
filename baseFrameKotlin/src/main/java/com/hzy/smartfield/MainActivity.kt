package com.hzy.smartfield


import android.view.View
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import com.hzy.smartfield.base.BaseActivity
import com.hzy.smartfield.fragment.CustomerFragment
import com.hzy.smartfield.fragment.DaiBanFragment
import com.hzy.smartfield.fragment.HousingFragment
import com.hzy.smartfield.fragment.MineFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.base_title_bar.*

class MainActivity : BaseActivity(), BottomNavigationBar.OnTabSelectedListener {


    private var fragmentManager: FragmentManager? = null
    private var transaction: FragmentTransaction? = null
    private var daiBanFragment: DaiBanFragment? = null
    private var customerFragment: CustomerFragment? = null
    private var housingFragment: HousingFragment? = null
    private var mineFragment: MineFragment? = null
    override fun setLayoutResourceID(): Int = R.layout.activity_main

    override fun init() {}

    override fun initView() {
        ll_title_bar.visibility = View.GONE;
        fragmentManager = supportFragmentManager
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED)
        bottomNavigationBar
            .addItem(BottomNavigationItem(R.mipmap.icon_daiban, "待办"))
            .addItem(BottomNavigationItem(R.mipmap.icon_customer, "客户"))
            .addItem(BottomNavigationItem(R.mipmap.icon_housing, "房源"))
            .addItem(BottomNavigationItem(R.mipmap.icon_mine, "我的"))
            .setActiveColor("#2D82FF")
            .setFirstSelectedPosition(0)
            .setBarBackgroundColor(R.color.white)
            .initialise()
        bottomNavigationBar.setTabSelectedListener(this)
        initDaiBanFragment()
    }


    override fun initData() {
    }

    private fun initDaiBanFragment() {
        transaction = fragmentManager!!.beginTransaction()
        if (daiBanFragment == null) {
            daiBanFragment = DaiBanFragment()
            transaction!!.add(R.id.container, daiBanFragment!!)
        }
        //隐藏所有fragment
        hideFragment(transaction!!)
        //显示待办fragment
        transaction!!.show(daiBanFragment!!)
        transaction!!.commit()
    }

    private fun initCustomerFragment() {
        transaction = fragmentManager!!.beginTransaction()
        if (customerFragment == null) {
            customerFragment = CustomerFragment()
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
            housingFragment = HousingFragment()
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
            mineFragment = MineFragment()
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
        if (daiBanFragment != null) {
            transaction.hide(daiBanFragment!!)
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
            0 -> initDaiBanFragment()
            1 -> initCustomerFragment()
            2 -> initHousingFragment()
            3 -> initMineFragment()
            else -> {

            }
        }
    }
}
