package com.hzy.customview;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.hzy.customview.doodleboard.ColorPickerView;
import com.hzy.customview.doodleboard.ColorPickerView2;

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
        FrameLayout container2 = findViewById(R.id.color_container2);
        final ColorPickerView2 colorPickerView2 = new ColorPickerView2(this, Color.BLACK, height, width, new ColorPickerView2.OnSelectedColorListener() {
            @Override
            public void onSelected(int color) {
                Toast.makeText(getApplicationContext(),"Color"+color,Toast.LENGTH_SHORT).show();
            }
        });
        container2.addView(colorPickerView2, 0, new ViewGroup.LayoutParams(height, width));
    }
}
