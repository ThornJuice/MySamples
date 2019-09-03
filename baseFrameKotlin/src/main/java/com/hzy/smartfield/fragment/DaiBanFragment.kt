package com.hzy.smartfield.fragment

import android.view.View
import com.hzy.smartfield.R
import com.hzy.smartfield.base.BaseFragment
import com.hzy.smartfield.base.showToast
import kotlinx.android.synthetic.main.base_title_bar.*

/**
 * 待办
 */
class DaiBanFragment : BaseFragment() {
    override fun attachLayoutRes(): Int = R.layout.fragment_dai_ban

    override fun initView(view: View) {
        ivLeftImage.setOnClickListener { showToast("left") }
        ivRightImage.setOnClickListener { showToast("right") }
    }

    override fun lazyLoad() {

    }

}
