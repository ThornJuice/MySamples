package com.ju.baselibrary.utils;

import android.app.Activity;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.ju.baselibrary.R;


/**
 * Created by admin on 2017/9/29.
 */

public class ViewUtils {

    public static Dialog loadingDialog;
    public static void createLoadingDialog(final Activity context, boolean flag, String str) {

        if (context == null)
            return;
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.view_loading_dialog, null);// 得到加载view
        RelativeLayout layout = (RelativeLayout) v.findViewById(R.id.dialog_view);// 加载布局
        TextView tvText = (TextView) v.findViewById(R.id.tv_text);
        tvText.setText(str);
        // 加载动画
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

    /**
     * @param context
     * @return
     */
    public static void createLoadingDialog(Activity context, String str) {
        createLoadingDialog(context, true, str);
    }


    /**
     * 取消 ProgressBar
     */
    public static void cancelLoadingDialog() {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
    }

}
