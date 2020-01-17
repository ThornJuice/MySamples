package com.hzy.baselib.widget

import android.content.Context
import android.content.res.TypedArray
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView

import com.hzy.baselib.R


/**
 * title bar
 *
 */
class BaseTitleBar : RelativeLayout {

    lateinit var leftLayout: RelativeLayout
        protected set
    protected lateinit var leftImage: ImageView
    lateinit var rightLayout: RelativeLayout
        protected set
    protected lateinit var rightImage: ImageView
    protected lateinit var titleView: TextView
    protected lateinit var titleLayout: RelativeLayout

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : this(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context) : super(context) {
        init(context, null)
    }

    private fun init(context: Context, attrs: AttributeSet?) {
        LayoutInflater.from(context).inflate(R.layout.base_widget_title_bar, this)
        leftLayout = findViewById(R.id.left_layout)
        leftImage = findViewById(R.id.left_image)
        rightLayout = findViewById(R.id.right_layout)
        rightImage = findViewById(R.id.right_image)
        titleView = findViewById(R.id.title)
        titleLayout = findViewById(R.id.root)

        parseStyle(context, attrs)
    }

    private fun parseStyle(context: Context, attrs: AttributeSet?) {
        if (attrs != null) {
            val ta = context.obtainStyledAttributes(attrs, R.styleable.BaseTitleBar)
            val title = ta.getString(R.styleable.BaseTitleBar_titleBarTitle)
            titleView.text = title

            val leftDrawable = ta.getDrawable(R.styleable.BaseTitleBar_titleBarLeftImage)
            if (null != leftDrawable) {
                leftImage.setImageDrawable(leftDrawable)
            }
            val rightDrawable = ta.getDrawable(R.styleable.BaseTitleBar_titleBarRightImage)
            if (null != rightDrawable) {
                rightImage.setImageDrawable(rightDrawable)
            }

            val background = ta.getDrawable(R.styleable.BaseTitleBar_titleBarBackground)
            if (null != background) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    titleLayout.background = background
                } else {
                    titleLayout.setBackgroundDrawable(background)
                }
            }

            ta.recycle()
        }
    }

    fun setLeftImageResource(resId: Int) {
        leftImage.setImageResource(resId)
    }

    fun setRightImageResource(resId: Int) {
        rightImage.setImageResource(resId)
    }

    fun setLeftLayoutClickListener(listener: View.OnClickListener) {
        leftLayout.setOnClickListener(listener)
    }

    fun setRightLayoutClickListener(listener: View.OnClickListener) {
        rightLayout.setOnClickListener(listener)
    }

    fun setLeftLayoutVisibility(visibility: Int) {
        leftLayout.visibility = visibility
    }

    fun setRightLayoutVisibility(visibility: Int) {
        rightLayout.visibility = visibility
    }

    fun setPageTitle(title: String) {
        titleView.text = title
    }

    override fun setBackgroundColor(color: Int) {
        titleLayout.setBackgroundColor(color)
    }
}
