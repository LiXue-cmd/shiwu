package com.example.shiwu.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

import com.example.shiwu.R;

public class StartActivity extends AppCompatActivity {


    private Button a1,a2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);


        a1=findViewById (R.id.newuser);
        a2=findViewById (R.id.login);

        a1.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (StartActivity.this,Register.class);
                startActivity (intent);
                finish ();
            }
        });

        a2.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (StartActivity.this,LoginActivity.class);
                startActivity (intent);
                finish ();
            }
        });

        int themeColor = getSharedPreferences("themeColor",MODE_PRIVATE).getInt("themeColor",getResources().getColor(R.color.white,null));
        getWindow().setStatusBarColor(themeColor);
    }
}
