package com.hzy.navigatin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // Navigation.findNavController(MainActivity.this,R.id.main_navigation).navigate(R.id.twoFragment);
    }
}
