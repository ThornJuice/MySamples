package com.hzy.customview.materialedittext;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hzy.customview.R;

public class TestMaterialActivity extends AppCompatActivity {
    MaterialEditText materialEdit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_material);
        materialEdit =findViewById(R.id.materialEdit);
//        materialEdit.setUseFloatLabel(true);
    }

}