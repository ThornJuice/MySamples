package com.hzy.customview;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.hzy.customview.doodleboard.ColorPickerView;

public class TestColorPickerActivity extends AppCompatActivity {
    int height = 800;
    int width = 800;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_color_picker);
        FrameLayout container = findViewById(R.id.color_container);
        final ColorPickerView colorPickerView = new ColorPickerView(this, Color.BLACK, height, width, new ColorPickerView.OnSelectedColorListener() {
            @Override
            public void onSelected(int color) {
                Toast.makeText(getApplicationContext(),"Color"+color,Toast.LENGTH_SHORT).show();
            }
        });
        container.addView(colorPickerView, 0, new ViewGroup.LayoutParams(height, width));
    }
}
