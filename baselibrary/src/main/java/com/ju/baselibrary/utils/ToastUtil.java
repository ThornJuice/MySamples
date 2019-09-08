package com.ju.baselibrary.utils;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ju.baselibrary.R;


public class ToastUtil {
    private static Toast toast;

    public static void showToast(Context context, String toastStr) {
        try {
            if (toast == null) {
                toast = new Toast(context);
            }
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            LinearLayout toastLayout = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.toast_view, null);
            TextView txtToast = (TextView) toastLayout.findViewById(R.id.txt_toast);
            txtToast.setText(toastStr);
            toast.setView(toastLayout);
        } catch (Exception e) {

        }
        try {
            toast.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showToast(Activity activity, String toastStr) {
        try {
            if (toast == null) {
                toast = new Toast(activity);
            }
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            LayoutInflater inflater = activity.getLayoutInflater();
            LinearLayout toastLayout = (LinearLayout) inflater.inflate(R.layout.toast_view, null);
            TextView txtToast = (TextView) toastLayout.findViewById(R.id.txt_toast);
            txtToast.setText(toastStr);
            toast.setView(toastLayout);
        } catch (Exception e) {

        }
        try {
            toast.show();
        } catch (Exception e) { }
    }


}
