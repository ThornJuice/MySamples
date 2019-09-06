package com.hzy.mvpsample;



import android.view.View;
import android.support.v4.app.Fragment;
import com.example.mvpsample.R;
import com.ju.baselibrary.base.BaseFragment;
import com.ju.baselibrary.base.BaseLazyFragment;
import com.ju.baselibrary.http.BaseBean;
import com.ju.baselibrary.http.HttpObjectCallBack;
import com.ju.baselibrary.http.HttpStringCallBack;
import com.ju.baselibrary.http.OkGoRequest;
import com.ju.baselibrary.utils.ToastUtil;

/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends BaseFragment {


    public BlankFragment() {
        // Required empty public constructor
    }


    @Override
    protected void init() {

    }

    @Override
    protected void initView(View view) {
        view.findViewById(R.id.textView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    @Override
    protected void initData() {
     /*   OkGoRequest.get(getContext()).url("https://www.wanandroid.com/project/tree/json").doGet(new HttpStringCallBack() {
            @Override
            public void onSuccess(Object result) {
                ToastUtil.showToast(getActivity(),result.toString()+"");
            }

            @Override
            public void onFailure(int code, String msg) {
                ToastUtil.showToast(getActivity(),msg+"");
            }
        });*/
        OkGoRequest.get(getContext()).url("https://www.wanandroid.com/project/tree/json").doGet(new HttpObjectCallBack<Bean>(Bean.class) {
            @Override
            public void onSuccess(BaseBean<Bean> result) {
                ToastUtil.showToast(getActivity(),result.data.size()+"");
            }

            @Override
            public void onFailure(int code, String msg) {

            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_blank;
    }

}
