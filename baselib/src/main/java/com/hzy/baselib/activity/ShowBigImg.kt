package com.hzy.baselib.activity


import android.content.Context
import android.content.Intent
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.hzy.baselib.R
import com.hzy.baselib.base.BaseActivity
import com.hzy.baselib.util.GlideUtil
import com.hzy.baselib.util.MediaConstant
import com.hzy.baselib.widget.CancelOrSureDialog
import uk.co.senab.photoview.PhotoView
import uk.co.senab.photoview.PhotoViewAttacher
import java.util.*


/**
 * 查看大图
 */
class ShowBigImg : BaseActivity(), ViewPager.OnPageChangeListener, View.OnClickListener {
    override fun getLayoutId(): Int {
        return R.layout.activity_show_big_img
    }

    private var viewPager: ViewPager? = null
    private var positionTv: TextView? = null
    private var imgList: ArrayList<String>? = null
    private var mPosition = 0
    private var mAdapter: ViewPagerAdapter? = null
    private var mShowDlete = false
    private var delete_iv: ImageView? = null



    override fun initView() {
        removeTitleBar()
        try {
            imgList = intent.getStringArrayListExtra(MediaConstant.IMG_LIST)
            if (imgList == null) {
                imgList = ArrayList()
                val img = intent.getStringExtra(MediaConstant.PIC_PATH)
                imgList!!.add(img)
            }
            mPosition = intent.getIntExtra(MediaConstant.POSITION, 0)
            try {
                mShowDlete = intent.getBooleanExtra(MediaConstant.SHOWDELETE, false)
            } catch (e: Exception) {
                mShowDlete = false
            }

            viewPager = findViewById<View>(R.id.viewPager) as ViewPager
            positionTv = findViewById<View>(R.id.position_tv) as TextView
            findViewById<View>(R.id.back_iv).setOnClickListener(this)
            delete_iv = findViewById<View>(R.id.delete_iv) as ImageView
            delete_iv!!.setOnClickListener(this)

            if (mShowDlete) {
                delete_iv!!.visibility = View.VISIBLE
            } else {
                delete_iv!!.visibility = View.GONE
            }
            viewPager!!.addOnPageChangeListener(this)

            mAdapter = ViewPagerAdapter(this, imgList)
            viewPager!!.adapter = mAdapter
            positionTv!!.text = (mPosition + 1).toString() + "/" + imgList!!.size
            viewPager!!.currentItem = mPosition
        } catch (e: Exception) {
            finish()
        }

    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

    }

    override fun onPageSelected(position: Int) {
        mPosition = position
        positionTv!!.text = (position + 1).toString() + "/" + imgList!!.size
    }

    override fun onPageScrollStateChanged(state: Int) {

    }

    override fun onClick(v: View) {

        val i = v.id
        if (i == R.id.back_iv) {//返回
            back()
        } else if (i == R.id.delete_iv) {//删除图片
            deletePic()
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //按下了返回键
            back()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    //删除图片
    private fun deletePic() {
        val dialog = object : CancelOrSureDialog(this@ShowBigImg, "要删除这张图片吗?") {
            override fun ok() {
                super.ok()
                imgList!!.removeAt(mPosition)
                setPosition()
                dismiss()
            }
        }
        dialog.show()
    }

    //设置当前位置
    private fun setPosition() {
        positionTv!!.text = (mPosition + 1).toString() + "/" + imgList!!.size
        viewPager!!.currentItem = mPosition
        mAdapter!!.notifyDataSetChanged()
    }


    //返回上一个页面
    private fun back() {
        val intent = Intent()
        intent.putStringArrayListExtra(MediaConstant.IMG_LIST, imgList)
        setResult(MediaConstant.RESULT_CODE_VIEW_IMG, intent)
        finish()
    }

    override fun initData() {

    }


    internal inner class ViewPagerAdapter(private val context: Context, private val imgList: List<String>?) : PagerAdapter() {

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val view = getItemView(R.layout.view_pager_img)
             GlideUtil.displayImage(imgList!![position], view.findViewById<View>(R.id.img_iv) as ImageView)
            val photoView = view.findViewById<PhotoView>(R.id.img_iv)
            photoView.onPhotoTapListener = object : PhotoViewAttacher.OnPhotoTapListener {
                override fun onPhotoTap(view: View, x: Float, y: Float) {
                    back()
                }

                override fun onOutsidePhotoTap() {

                }
            }
            container.addView(view)
            return view
        }

        override fun getCount(): Int {
            return imgList?.size ?: 0
        }

        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            return view === `object`
        }

        private fun getItemView(layoutID: Int): View {
            return LayoutInflater.from(context).inflate(layoutID, null, false)

        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            //        super.destroyItem(container, position, object);这里调用了...
            container.removeView(`object` as View)
        }
    }

    companion object {

        fun build(context: Context, img: String) {
            val intent = Intent(context, ShowBigImg::class.java)
            intent.putExtra(MediaConstant.PIC_PATH, img)
            context.startActivity(intent)
        }

        fun build(context: Context, imgs: ArrayList<String>) {
            val intent = Intent(context, ShowBigImg::class.java)
            intent.putStringArrayListExtra(MediaConstant.IMG_LIST, imgs)
            context.startActivity(intent)
        }

        fun build(context: Context, imgs: ArrayList<String>, selectPage: Int) {
            val intent = Intent(context, ShowBigImg::class.java)
            intent.putStringArrayListExtra(MediaConstant.IMG_LIST, imgs)
            intent.putExtra(MediaConstant.POSITION, selectPage)
            context.startActivity(intent)
        }
    }

}
