package com.hzy.greendao.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hzy.greendao.App;
import com.hzy.greendao.R;
import com.hzy.greendao.db.CityHelper;
import com.hzy.greendao.db.DbHelperUtil;
import com.hzy.greendao.ui.NewTaskActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class BlankFragment2 extends Fragment {
    CityHelper cityHelper;
    public BlankFragment2() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blank_fragment2, container, false);
        view.findViewById(R.id.btn_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //   Toast.makeText(getActivity().getApplicationContext(),"ok",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getActivity(), NewTaskActivity.class));
            }
        });

        cityHelper = DbHelperUtil.getCityHelper();
        view.findViewById(R.id.btn_local).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Log.e("MainActivity",cityHelper.queryAll().size()+"");
            }
        });
        return view;

    }

}
