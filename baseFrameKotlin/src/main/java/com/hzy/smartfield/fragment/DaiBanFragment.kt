package com.hzy.smartfield.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.MergeAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hzy.smartfield.Bean
import com.hzy.smartfield.R
import com.hzy.smartfield.base.BaseFragment
import com.hzy.smartfield.base.showToast
import com.hzy.smartfield.http.BaseBean
import com.hzy.smartfield.http.HttpObjectCallBack
import com.hzy.smartfield.http.OkGoRequest
import kotlinx.android.synthetic.main.base_title_bar.*
import kotlinx.android.synthetic.main.base_title_bar.ivLeftImage
import kotlinx.android.synthetic.main.base_title_bar.ivRightImage
import kotlinx.android.synthetic.main.fragment_dai_ban.*
import kotlinx.android.synthetic.main.item_header.view.*


class DaiBanFragment : BaseFragment() {
    private val mAdapter = MyAdapter()
    private lateinit var mergeAdapter: MergeAdapter;
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
//        OkGoRequest.get(context).url("https://www.wanandroid.com/project/tree/json").doGet(object: HttpObjectCallBack<Bean>(Bean::class.java){
//            override fun onSuccess(result: BaseBean<Bean>?) {
//                showToast(""+result?.data?.size)
//            }
//
//            override fun onFailure(code: Int, msg: String?) {
//                showToast(msg + "")
//            }
//        })
        setData()
    }

    fun setData() {
        val list = listOf(
                "user1", "user2", "user3",
                "user4", "user5", "user6",
                "user7", "user8", "user9",
                "user10", "user11", "user12"
        )
        val header = HeaderAdapter()
        val footer = FooterAdapter()
        mergeAdapter = MergeAdapter(mAdapter)
        recyclerView.adapter = mergeAdapter
        mAdapter.submitList(list)
        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = false
            mergeAdapter.addAdapter(0,header)
            mergeAdapter.addAdapter(footer)

        }
    }

    class HeaderAdapter : RecyclerView.Adapter<HeaderAdapter.MyViewHolder>() {
        var mValues = ArrayList<String>()
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder =
                MyViewHolder(
                        LayoutInflater.from(parent.context).inflate(R.layout.item_header, parent, false)
                )

        override fun getItemCount(): Int = 1

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            holder.mContentView.text = "header" + position
        }

        class MyViewHolder(item: View) : RecyclerView.ViewHolder(item) {
            val mContentView: TextView = item.text
        }
    }

    class FooterAdapter : RecyclerView.Adapter<FooterAdapter.MyViewHolder>() {
        var mValues = ArrayList<String>()
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder =
                MyViewHolder(
                        LayoutInflater.from(parent.context).inflate(R.layout.item_header, parent, false)
                )

        override fun getItemCount(): Int = 1

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            holder.mContentView.text = "footer" + position
        }

        class MyViewHolder(item: View) : RecyclerView.ViewHolder(item) {
            val mContentView: TextView = item.text
        }
    }

    class MyAdapter : ListAdapter<String, MyAdapter.MyViewHolder>(DiffCallBack()) {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder =
                MyViewHolder(
                        LayoutInflater.from(parent.context).inflate(R.layout.item_username, parent, false)
                )

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            holder.bind(getItem(position))
        }

        class MyViewHolder(item: View) : RecyclerView.ViewHolder(item) {
            private val username: AppCompatTextView = item.findViewById(R.id.text)

            fun bind(item: String) {
                username.text = item
            }
        }

        class DiffCallBack : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(oldItem: String, newItem: String): Boolean =
                    oldItem == newItem

            override fun areContentsTheSame(oldItem: String, newItem: String): Boolean =
                    oldItem == newItem
        }
    }
}
