package com.example.shiwu.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.shiwu.R;

public class weikaifa extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_weikaifa);

        int themeColor = getSharedPreferences("themeColor",MODE_PRIVATE).getInt("themeColor",getResources().getColor(R.color.white,null));
        getWindow().setStatusBarColor(themeColor);
    }
}