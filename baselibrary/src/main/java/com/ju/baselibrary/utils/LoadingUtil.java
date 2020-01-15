package com.ju.baselibrary.utils;

import android.app.Activity;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ju.baselibrary.R;


/**
 * loadingDialog
 */

public class LoadingUtil {

    public static Dialog loadingDialog;

    public static void show(final Activity context, boolean flag, String str) {

        if (context == null)
            return;
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.view_loading_dialog, null);// 得到加载view
        RelativeLayout layout = (RelativeLayout) v.findViewById(R.id.dialog_view);// 加载布局
        TextView tvText = (TextView) v.findViewById(R.id.tv_text);
        tvText.setText(str);
        if (loadingDialog != null && loadingDialog.isShowing()) return;

        if (context.getParent() != null)
            loadingDialog = new Dialog(context.getParent(), R.style.loading_dialog);// 创建自定义样式dialog
        else {
            loadingDialog = new Dialog(context, R.style.loading_dialog);// 创建自定义样式dialog
        }

        loadingDialog.setCancelable(true);// 可以用“返回键”取消
        loadingDialog.setCanceledOnTouchOutside(false);
        loadingDialog.setContentView(layout, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));// 设置布局
        if (!context.isFinishing())
            loadingDialog.show();
    }

    public static void show(Activity context, String str) {
        show(context, true, str);
    }

    public static void dismiss() {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
    }

}
