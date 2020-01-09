package com.hzy.greendao.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.hzy.greendao.Constant;
import com.hzy.greendao.R;
import com.hzy.greendao.db.DbHelper;
import com.hzy.greendao.greendao.CityDao;
import com.hzy.greendao.ui.NewTaskActivity;


public class BlankFragment2 extends Fragment {
    CityDao cityDao;
    public BlankFragment2() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blank_fragment2, container, false);
        view.findViewById(R.id.btn_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), NewTaskActivity.class));
            }
        });

        cityDao = DbHelper.getInstance(Constant.DB_CITY).getDaoSession().getCityDao();
        view.findViewById(R.id.btn_local).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Toast.makeText(getActivity(),cityDao.queryBuilder().list().size()+"",Toast.LENGTH_SHORT).show();
            }
        });
        return view;

    }

}
