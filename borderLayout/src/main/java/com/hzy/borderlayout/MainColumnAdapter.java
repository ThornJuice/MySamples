package com.hzy.borderlayout;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainColumnAdapter extends RecyclerView.Adapter<MainColumnAdapter.UnLockViewHolder> {

    /**
     * 表格文字大小
     */
    private int mTextSize = 12;
    /**
     * 上下文
     */
    private Context mContext;
    /**
     * 表格数据
     */
    private ArrayList<ArrayList<String>> mTableDatas;

    /**
     * 构造方法
     *
     * @param mContext
     * @param mTableDatas
     */
    public MainColumnAdapter(Context mContext, ArrayList<ArrayList<String>> mTableDatas) {
        this.mContext = mContext;
        this.mTableDatas = mTableDatas;
    }


    @Override
    public int getItemCount() {
        return mTableDatas.size();
    }

    @Override
    public UnLockViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        UnLockViewHolder holder = new UnLockViewHolder(LayoutInflater.from(mContext).inflate(R.layout.table_main_column_item, null));
        return holder;
    }

    @Override
    public void onBindViewHolder(UnLockViewHolder holder, final int position) {
        ArrayList<String> datas = mTableDatas.get(position);
        createRowView(holder.mLinearLayout, datas);

    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    class UnLockViewHolder extends RecyclerView.ViewHolder {
        LinearLayout mLinearLayout;
        public UnLockViewHolder(View itemView) {
            super(itemView);
            mLinearLayout = (LinearLayout) itemView.findViewById(R.id.unlock_linearlayout);
        }
    }

    /**
     * 构造每行数据视图
     *
     * @param linearLayout
     * @param datas
     */
    private void createRowView(LinearLayout linearLayout, List<String> datas) {
        //设置LinearLayout
        linearLayout.removeAllViews();//首先清空LinearLayout,复用会造成重复绘制，使内容超出预期长度
        for (int i = 0; i < datas.size(); i++) {
            //构造单元格
            TextView textView = new TextView(mContext);
            textView.setTextColor(ContextCompat.getColor(mContext, R.color.black));
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, mTextSize);
            textView.setGravity(Gravity.CENTER);
            //设置布局
            LinearLayout.LayoutParams textViewParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            //int padding = DisplayUtil.dip2px(mContext,MyLockTableView.mPadding);
            //textViewParams.setMargins(padding,padding,padding,padding);
            textViewParams.height = DisplayUtil.dip2px(mContext, MyLockTableView.mRowDataheight+10);
            textViewParams.width = DisplayUtil.dip2px(mContext, MyLockTableView.mRowDataWidth+10);
            textView.setLayoutParams(textViewParams);
            setListener(textView,datas.get(i));
            setBackGround(textView,datas.get(i));
            linearLayout.addView(textView);
            //画分隔线
            if (i != datas.size() - 1) {
                View splitView = new View(mContext);
                ViewGroup.LayoutParams splitViewParmas = new ViewGroup.LayoutParams(DisplayUtil.dip2px(mContext, 0.2f),
                        ViewGroup.LayoutParams.MATCH_PARENT);
                splitView.setLayoutParams(splitViewParmas);
                splitView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                linearLayout.addView(splitView);
            }
        }
    }

    private void setBackGround(TextView textView, String s) {
        if(TextUtils.equals(s,"2")){
            textView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.orange));
            textView.setTextColor(ContextCompat.getColor(mContext, R.color.white));
           // textView.setText(s);
        }
    }

    public  void setListener(View view, final String str){
         view.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Toast.makeText(mContext,str,Toast.LENGTH_SHORT).show();
             }
         });
    }
}
