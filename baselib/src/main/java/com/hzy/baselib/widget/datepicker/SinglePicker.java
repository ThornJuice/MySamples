package com.hzy.baselib.widget.datepicker;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import com.hzy.baselib.R;

import java.util.List;

public class SinglePicker implements View.OnClickListener, PickerView.OnSelectListener {
    private Context mContext;
    private Callback mCallback;
    private boolean mCanDialogShow;
    private Dialog mPickerDialog;
    private PickerView mPickerView;
    private String mSelected;
    private List<String> mData;

    public SinglePicker(Context context, Callback callback, List<String> data) {
        if (context == null || callback == null) {
            mCanDialogShow = false;
            return;
        }
        mContext = context;
        mCallback = callback;
        mData = data;
        initView();
        initData();
        mCanDialogShow = true;
    }


    private void initView() {
        mPickerDialog = new Dialog(mContext, R.style.date_picker_dialog);
        mPickerDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mPickerDialog.setContentView(R.layout.dialog_single_picker);

        Window window = mPickerDialog.getWindow();
        if (window != null) {
            WindowManager.LayoutParams lp = window.getAttributes();
            lp.gravity = Gravity.BOTTOM;
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            window.setAttributes(lp);
        }

        mPickerDialog.findViewById(R.id.iv_cancel).setOnClickListener(this);
        mPickerDialog.findViewById(R.id.iv_confirm).setOnClickListener(this);
        mPickerView = mPickerDialog.findViewById(R.id.pickerView);
        mPickerView.setOnSelectListener(this);
    }

    private void initData() {
        mPickerView.setDataList(mData);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.iv_cancel) {
        } else if (i == R.id.iv_confirm) {
            if (mCallback != null) {
                mCallback.onSelected(mSelected);
            }
        }

        if (mPickerDialog != null && mPickerDialog.isShowing()) {
            mPickerDialog.dismiss();
        }
    }

    @Override
    public void onSelect(View view, String selected) {
        if (view == null || TextUtils.isEmpty(selected)) return;
        mSelected = selected;
    }

    /**
     * 选择结果回调接口
     */
    public interface Callback {
        void onSelected(String selected);
    }

    /**
     * 设置是否允许点击屏幕或物理返回键关闭
     */
    public void setCancelable(boolean cancelable) {
        if (!canShow()) return;

        mPickerDialog.setCancelable(cancelable);
    }

    private boolean canShow() {
        return mCanDialogShow && mPickerDialog != null;
    }

    public void show() {
        if (!canShow()||mData==null||mData.size()==0) return;
        mPickerDialog.show();
    }
}
