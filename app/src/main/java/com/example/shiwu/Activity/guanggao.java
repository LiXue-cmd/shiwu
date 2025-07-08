package com.example.shiwu.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.example.shiwu.R;

public class guanggao extends AppCompatActivity {


    private TextView textView;
    //声明时间有多少;
    private int count = 5;
    private Animation animation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.guanggao);

        int themeColor = getSharedPreferences("themeColor",MODE_PRIVATE).getInt("themeColor",getResources().getColor(R.color.white,null));
        getWindow().setStatusBarColor(themeColor);

        // 初始化控件对象textView
        textView = (TextView) findViewById(R.id.textView);
        animation = AnimationUtils.loadAnimation(this, R.anim.wq);
        handler.sendEmptyMessageDelayed(0, 1000);
    }

    //咱在写一个计算Welcome界面的广告时间结束后进入主界面的方法
    private int getCount() {
        count--;
        if (count == 0) {
            Intent intent = new Intent(this, StartActivity.class);
            startActivity(intent);
            finish();
        }
        return count;
    }
    //进行一个消息的处理
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            if (msg.what == 0) {
                textView.setText(getCount()+"");
                handler.sendEmptyMessageDelayed(0, 1000);
                animation.reset();
                textView.startAnimation(animation);
            }
        };
    };
}