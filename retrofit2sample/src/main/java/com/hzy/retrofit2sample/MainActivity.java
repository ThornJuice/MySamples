package com.hzy.retrofit2sample;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.View;

import com.hzy.retrofit2sample.bean.WeatherInfo;
import com.hzy.retrofit2sample.http.CustomResourceSubsciber;
import com.hzy.retrofit2sample.http.RetrofitManager;
import com.hzy.retrofit2sample.util.RxUtil;
import com.hzy.retrofit2sample.util.XLog;

import org.reactivestreams.Subscription;

import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

public class MainActivity extends BaseActivity {
    ProgressDialog pd;
    @Override
    public void init() {
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.getWeather:
                 pd = new ProgressDialog(this);
                pd.setCanceledOnTouchOutside(false);
                pd.setOnCancelListener(new DialogInterface.OnCancelListener() {

                    @Override
                    public void onCancel(DialogInterface dialog) {
                        Log.e("DialogInterface","dismiss");
                    }
                });

                pd.setMessage("loading");
                pd.show();
                getWeather();
                break;
        }
    }

    //tv_Weather.setText(weatherInfo.toString());
    private void getWeather() {
        RetrofitManager.getInstance().create().getWeatherInfo("101010100")
                .doOnSubscribe(new Consumer<Subscription>() {
                    @Override
                    public void accept(Subscription subscription) throws Exception {
                        XLog.e("doOnSubscribe");
                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        XLog.e("doFinally");
                    }
                }).compose(RxUtil.<WeatherInfo>rxSchedulerTransformer())
                .subscribe(new CustomResourceSubsciber<WeatherInfo>() {
                    @Override
                    public void onNext(WeatherInfo weatherInfo) {
                        XLog.e("onNext");
                        pd.cancel();
                    }

                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        /*if (mUnbinder != null && mUnbinder != Unbinder.EMPTY) {
            mUnbinder.unbind();
        }*/
    }
}
