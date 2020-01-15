package com.ju.baselibrary.utils;

import android.app.Activity;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ju.baselibrary.R;
import com.ju.baselibrary.base.BaseApp;


public class ToastUtil {
    public static void showToast(Context context, @NonNull String toastStr) {
        try {
            Toast toast = new Toast(context);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            LinearLayout toastLayout = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.toast_view, null);
            TextView txtToast = (TextView) toastLayout.findViewById(R.id.txt_toast);
            txtToast.setText(toastStr + "");
            toast.setView(toastLayout);
            toast.show();
        } catch (Exception e) {

        }

    }

    public static void showToast(String toastStr) {
        try {
            Toast toast = new Toast(BaseApp.getAppInsatnce());
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            LinearLayout toastLayout = (LinearLayout) LayoutInflater.from(BaseApp.getAppInsatnce()).inflate(R.layout.toast_view, null);
            TextView txtToast = (TextView) toastLayout.findViewById(R.id.txt_toast);
            txtToast.setText(toastStr + "");
            toast.setView(toastLayout);
            toast.show();
        } catch (Exception e) {

        }

    }

    public static void showToast(Activity activity, @NonNull String toastStr) {
        try {
            Toast toast = new Toast(activity);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            LayoutInflater inflater = activity.getLayoutInflater();
            LinearLayout toastLayout = (LinearLayout) inflater.inflate(R.layout.toast_view, null);
            TextView txtToast = (TextView) toastLayout.findViewById(R.id.txt_toast);
            txtToast.setText(toastStr + "");
            toast.setView(toastLayout);
            toast.show();
        } catch (Exception e) {
        }

    }

    public static void showToast(Activity activity, @StringRes int toastStr) {
        try {
            Toast toast = new Toast(activity);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            LayoutInflater inflater = activity.getLayoutInflater();
            LinearLayout toastLayout = (LinearLayout) inflater.inflate(R.layout.toast_view, null);
            TextView txtToast = (TextView) toastLayout.findViewById(R.id.txt_toast);
            txtToast.setText(toastStr);
            toast.setView(toastLayout);
            toast.show();
        } catch (Exception e) {

        }
    }


}
