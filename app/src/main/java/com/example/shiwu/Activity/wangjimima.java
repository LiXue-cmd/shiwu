package com.example.shiwu.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.shiwu.Sqlite.MydatabaseHelper;
import com.example.shiwu.R;

public class wangjimima extends AppCompatActivity {


    private EditText shuru;
    private TextView xianshi;
    private Button chazhao;
    private MydatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_wangjimima);

        shuru=findViewById (R.id.shuru);
        xianshi=findViewById (R.id.xianshi);
        chazhao=findViewById (R.id.chazhao);
        dbHelper=MydatabaseHelper.getInstance(this);

        int themeColor = getSharedPreferences("themeColor",MODE_PRIVATE).getInt("themeColor",getResources().getColor(R.color.white,null));
        getWindow().setStatusBarColor(themeColor);

    }
    public void login(View v){
        String name_info=shuru.getText().toString().trim();
       // String pass_info=manager_pass.getText().toString().trim();
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        //通过输入的用户名查询数据库中的密码
        Cursor cursor=db.rawQuery("select password from manager where name=?",new String[]{name_info});
        String pi=null;
        if (cursor.moveToNext()){
            //获取密码
            pi=cursor.getString(cursor.getColumnIndex("password"));
            System.out.println (pi);
            xianshi.setText (pi);
        }

    }

}