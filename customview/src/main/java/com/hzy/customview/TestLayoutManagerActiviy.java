package com.hzy.customview;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.hzy.customview.layoutmanager.FlowLayoutManager;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class TestLayoutManagerActiviy extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layoutmanager);
        FlowLayoutManager customLayoutManager = new FlowLayoutManager();
        RecyclerView rv = findViewById(R.id.recyclerView);
        rv.setLayoutManager(customLayoutManager);
        rv.setAdapter(new RecyclerView.Adapter<VH>() {
            @NonNull
            @Override
            public VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_text,viewGroup,false);
                Log.d("LayoutManagerActivity","onCreateViewHolder " + i);
                return new VH(view);
            }

            @Override
            public void onBindViewHolder(@NonNull VH viewHolder, int i) {
                viewHolder.tv.setText(i+"");
                if (i%2 == 0){
                    viewHolder.tv.setBackgroundColor(Color.RED);
                }else {
                    viewHolder.tv.setBackgroundColor(Color.YELLOW);
                }
                Log.d("LayoutManagerActivity","onBindViewHolder " + i);
            }

            @Override
            public int getItemCount() {
                return 40;
            }
        });
    }

    private static class VH extends RecyclerView.ViewHolder{

        TextView tv ;
        public VH(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.textView);
        }
    }

}

