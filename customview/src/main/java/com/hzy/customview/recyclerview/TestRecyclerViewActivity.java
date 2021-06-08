package com.hzy.customview.recyclerview;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hzy.customview.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestRecyclerViewActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
//    private DiffAdapter mAdapter;
    private DiffAdapter2 mAdapter;

    private List<User> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_recycler_view);

        mRecyclerView = findViewById(R.id.recyclerView);
        for (int i = 0; i < 50; i++) {
            User user = new User();
            user.setName("name" + i);
            mList.add(user);
        }

        mAdapter = new DiffAdapter2(mList);
        LinearLayoutManager linearManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRecyclerView.getItemAnimator().setChangeDuration(0);
//        ((SimpleItemAnimator)mRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        mRecyclerView.setAdapter(mAdapter);
//        mAdapter.setAdapterOnclick(new DiffAdapter.OnItemClickListener() {
//            @Override
//            public void onClick(View view, int position) {
//                Toast.makeText(TestRecyclerViewActivity.this, mList.get(position).getName(), Toast.LENGTH_SHORT).show();
//            }
//        });
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();
                List<User> oldList = mAdapter.getOldList();
                mList.get(0).setName("测试" + random.nextInt(1000));
                DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(
                        new AdapterDiffCallback(oldList, mList));
                diffResult.dispatchUpdatesTo(mAdapter);
                mAdapter.updateData(mList);
            }
        });
    }
}