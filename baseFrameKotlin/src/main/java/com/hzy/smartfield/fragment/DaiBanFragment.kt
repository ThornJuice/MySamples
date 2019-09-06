package com.hzy.smartfield.fragment

import android.view.View
import com.hzy.smartfield.Bean
import com.hzy.smartfield.R
import com.hzy.smartfield.base.BaseFragment
import com.hzy.smartfield.base.showToast
import com.hzy.smartfield.http.BaseBean
import com.hzy.smartfield.http.HttpObjectCallBack
import com.hzy.smartfield.http.HttpStringCallBack
import com.hzy.smartfield.http.OkGoRequest
import com.hzy.smartfield.utils.ToastUtil
import kotlinx.android.synthetic.main.base_title_bar.*
import java.util.*

/**
 * 待办
 */
class DaiBanFragment : BaseFragment() {

    override fun attachLayoutRes(): Int = R.layout.fragment_dai_ban

    override fun initView(view: View) {
        ivLeftImage.setOnClickListener { showToast("left") }
        ivRightImage.setOnClickListener { showToast("right") }
    }

    override fun initData() {
       /* OkGoRequest.get(context).url("https://www.wanandroid.com/project/tree/json").doGet(object : HttpStringCallBack {
            override fun onSuccess(result: Any) {
                showToast(result.toString() + "")
            }
            override fun onFailure(code: Int, msg: String) {
                showToast(msg + "")
            }
        })*/
        OkGoRequest.get(context).url("https://www.wanandroid.com/project/tree/json").doGet(object: HttpObjectCallBack<Bean>(Bean::class.java){
            override fun onSuccess(result: BaseBean<Bean>?) {
                showToast(""+result?.data?.size)
            }

            override fun onFailure(code: Int, msg: String?) {
                showToast(msg + "")
            }
        })
    }

}
