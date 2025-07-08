package com.example.shiwu.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shiwu.R;

public class xinwne extends AppCompatActivity {

    private TextView biaoti,zhengwen;
    private ImageView tupian;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.xinwne);
        init();

        Intent intent =getIntent ();
        String tp=intent.getStringExtra ("logo");
        String name=intent.getStringExtra ("biaoti");
        String text=intent.getStringExtra ("zhengwen");
        System.out.println (name);
        System.out.println (text);
        int i=Integer.parseInt (tp);
        biaoti.setText (name);
       // tupian.setImageResource (i);
        tupian.setBackgroundResource (i);
        zhengwen.setText (text);
    }
    public void init(){

        biaoti=findViewById (R.id.biaoti);
        zhengwen=findViewById (R.id.zhengwen);
        tupian=findViewById (R.id.tupian);

    }

}