package com.hzy.borderlayout;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MyLockTableView {
    /**
     * 表格内容宽度
     */
    public static int mRowDataWidth = 22;
    /**
     * 表格第一列宽度
     */
    public static int mFirstColumnWidth = 60;
    /**
     * 表格内容高度
     */
    public static int mRowDataheight = 20;
    /**
     * 表格内容边距
     */
    public static int mPadding = 5;
    /**
     * 上下文
     */
    private Context mContext;
    /**
     * 表格父视图
     */
    private ViewGroup mContentView;
    /**
     * 表格数据，每一行为一条数据，从表头计算
     */
    private ArrayList<ArrayList<String>> mTableDatas = new ArrayList<ArrayList<String>>();
    /**
     * 表格每一行数据，不包括第一行和第一列
     */
    private ArrayList<ArrayList<String>> mTableRowDatas = new ArrayList<ArrayList<String>>();
    /**
     * 把所有的滚动视图放图列表，后面实现联动效果
     */
    private ArrayList<HorizontalScrollView> mScrollViews = new ArrayList<HorizontalScrollView>();
    /**
     * 表格视图
     */
    private View mTableView;
    /**
     * 表格横向滚动监听事件
     */
    private OnTableViewListener mTableViewListener;
    /**
     * 表格左上角视图
     */
    private TextView mColumnTitleView;
    /**
     * 表格左上角数据
     */
    private String mColumnTitle;
    /**
     * 表格第一行数据,不包括第一个元素
     */
    private ArrayList<String> mTableFristData = new ArrayList<>();
    /**
     * 表格第一列数据，不包括第一个元素
     */
    private ArrayList<String> mTableColumnDatas = new ArrayList<>();

    /**
     * 第一行滚动视图
     */
    private MyHorizontalScrollView mLockScrollView;
    //表格主视图
    RecyclerView mTableScrollView ;
    MyTableViewAdapter mMyTableViewAdapter;
    public MyLockTableView(Context context, ViewGroup contentView, ArrayList<ArrayList<String>> tableDatas){
        this.mContext = context;
        this.mContentView = contentView;
        this.mTableDatas = tableDatas;
        mTableView = LayoutInflater.from(mContext).inflate(R.layout.lock_head_table_view, null);
    }
    /**
     * 展现视图
     */
    public void show() {
        initData();
        initView();
        mContentView.removeAllViews();//清空视图
        mContentView.addView(mTableView);
    }
    /**
     * 初始化表格数据
     */
    private void initData() {
        if (mTableDatas != null && mTableDatas.size() > 0) {
            //检查数据，如果有一行数据长度不一致，以最长为标准填"N/A"字符串,如果有null也替换
            int maxLength = 0;
            for (int i = 0; i < mTableDatas.size(); i++) {
                if (mTableDatas.get(i).size() >= maxLength) {
                    maxLength = mTableDatas.get(i).size();
                }
                ArrayList<String> rowDatas = mTableDatas.get(i);
                for (int j = 0; j < rowDatas.size(); j++) {
                    if (rowDatas.get(j) == null || rowDatas.get(j).equals("")) {
                        rowDatas.set(j, "");
                    }
                }
                mTableDatas.set(i, rowDatas);
            }
//            Log.e("每行最多个数",maxLength+"");
            for (int i = 0; i < mTableDatas.size(); i++) {
                ArrayList<String> rowDatas = mTableDatas.get(i);
                if (rowDatas.size() < maxLength) {
                    int size = maxLength - rowDatas.size();
                    for (int j = 0; j < size; j++) {
                        rowDatas.add("");
                    }
                    mTableDatas.set(i, rowDatas);
                }
            }
                ArrayList<String> fristRowDatas = (ArrayList<String>) mTableDatas.get(0).clone();
                    mColumnTitle = fristRowDatas.get(0);
                    fristRowDatas.remove(0);
                    mTableFristData.addAll(fristRowDatas);
                    //构造第一列数据,并且构造表格每行数据
                    for (int i = 1; i < mTableDatas.size(); i++) {
                        ArrayList<String> rowDatas = (ArrayList<String>) mTableDatas.get(i).clone();
                        mTableColumnDatas.add(rowDatas.get(0));
                        rowDatas.remove(0);
                        mTableRowDatas.add(rowDatas);
                    }
        } else {
            Toast.makeText(mContext, "表格数据为空！", Toast.LENGTH_SHORT).show();
        }
    }
    /**
     * 初始化表格视图
     */
    private void initView() {

        mColumnTitleView = (TextView) mTableView.findViewById(R.id.lockHeadView_Text);
        mLockScrollView = (MyHorizontalScrollView) mTableView.findViewById(R.id.lockHeadView_ScrollView);
        //表格主视图
        mTableScrollView = (RecyclerView) mTableView.findViewById(R.id.table_scrollView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mTableScrollView.setLayoutManager(layoutManager);
        mMyTableViewAdapter = new MyTableViewAdapter(mContext, mTableColumnDatas, mTableRowDatas);
        //将滑动视图添加到mScrollViews
        mMyTableViewAdapter.setOnTableViewCreatedListener(new MyTableViewAdapter.OnTableViewCreatedListener() {
            @Override
            public void onTableViewCreatedCompleted(MyHorizontalScrollView scrollView) {
                mScrollViews.add(scrollView);
            }
        });
        //设置横向滑动监听
        mMyTableViewAdapter.setHorizontalScrollView(new OnTableViewListener() {

            @Override
            public void onTableViewScrollChange(int x, int y) {
                changeAllScrollView(x, y);
            }
        });
        mTableScrollView.setAdapter(mMyTableViewAdapter);
        creatHeadView();
    }
    /**
     * 创建头部视图
     */
    private void creatHeadView() {
            mColumnTitleView.setTextColor(ContextCompat.getColor(mContext, R.color.black));
            mColumnTitleView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
            mColumnTitleView.setText(mColumnTitle);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) mColumnTitleView.getLayoutParams();
            layoutParams.width = DisplayUtil.dip2px(mContext, mFirstColumnWidth);
            //layoutParams.height = DisplayUtil.dip2px(mContext, 120);
            int padding =  DisplayUtil.dip2px(mContext, mPadding);
            layoutParams.setMargins(padding, padding, padding, padding);
            mColumnTitleView.setLayoutParams(layoutParams);
            //构造第一行滚动视图
            createScollview(mLockScrollView, mTableFristData);
            mLockScrollView.setOnScrollChangeListener(new MyHorizontalScrollView.onScrollChangeListener() {
                @Override
                public void onScrollChanged(HorizontalScrollView scrollView, int x, int y) {
                    changeAllScrollView(x, y);
                }

                @Override
                public void onScrollFarLeft(HorizontalScrollView scrollView) {

                }

                @Override
                public void onScrollFarRight(HorizontalScrollView scrollView) {

                }
            });
            mScrollViews.add(mLockScrollView);


    }
    private void createScollview(HorizontalScrollView scrollView, List<String> datas) {
        //设置LinearLayout
        LinearLayout linearLayout = new LinearLayout(mContext);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.setGravity(Gravity.CENTER);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        for (int i = 0; i < datas.size(); i++) {
            //构造单元格
            TextView textView = new TextView(mContext);
            textView.setTextColor(ContextCompat.getColor(mContext, R.color.black));
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
            textView.setGravity(Gravity.CENTER);
            textView.setText(datas.get(i));
            //设置布局
            LinearLayout.LayoutParams textViewParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            int padding =  DisplayUtil.dip2px(mContext, mPadding);
            textViewParams.setMargins(padding, padding, padding, padding);
            textView.setLayoutParams(textViewParams);
            ViewGroup.LayoutParams textViewParamsCopy = textView.getLayoutParams();
            textViewParamsCopy.width = DisplayUtil.dip2px(mContext, mRowDataWidth);
            linearLayout.addView(textView);
            //画分隔线
            if (i != datas.size() - 1) {
                View splitView = new View(mContext);
                ViewGroup.LayoutParams splitViewParmas = new ViewGroup.LayoutParams(DisplayUtil.dip2px(mContext, 0.2f),
                        ViewGroup.LayoutParams.MATCH_PARENT);
                splitView.setLayoutParams(splitViewParmas);
                splitView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.border_gray));
                linearLayout.addView(splitView);
            }
        }
        scrollView.addView(linearLayout);
    }

    /**
     * 表格横向滑动监听
     * */
    public  interface  OnTableViewListener{
        void onTableViewScrollChange(int x, int y);
    }
    /**
     * 改变所有滚动视图位置
     *
     * @param x
     * @param y
     */
    private void changeAllScrollView(int x, int y) {
        if (mScrollViews.size() > 0) {
            for (int i = 0; i < mScrollViews.size(); i++) {
                HorizontalScrollView scrollView = mScrollViews.get(i);
                scrollView.scrollTo(x, y);
            }
        }
    }
}
