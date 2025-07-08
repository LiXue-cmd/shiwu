package com.example.shiwu.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.shiwu.R;
import com.example.shiwu.Sqlite.Adapterpaihang;
import com.example.shiwu.Sqlite.chengji;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class paihangbang extends AppCompatActivity {


    private ListView listView;
    private List<chengji> list_sp=new ArrayList<> ();//存数据
    TextView chengji1,tvbut_num;
    Adapterpaihang adapter;
    String tp,name;
    public static int flag=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_paihangbang);

        listView=findViewById(R.id.list_viewsp);
        chengji1=findViewById(R.id.chengji1);

        Intent intent =getIntent ();
         tp=intent.getStringExtra ("fenshu");
         name=intent.getStringExtra ("xiayici");
       // String text=intent.getStringExtra ("zhengwen");
          //  list_sp.add(new chengji ("5",name,tp));

        into ();

        adapter=new Adapterpaihang (paihangbang.this,R.layout.paihang,list_sp);//初始化适配器
        listView.setAdapter(adapter);
        setClick();//设置textview的点击事件，然后排序
    }
    private  void into(){
        list_sp.add(new chengji("1","张三","93"));
        list_sp.add(new chengji("2","李四","43"));
        list_sp.add(new chengji("3","王五","11"));
        list_sp.add(new chengji("4","赵一","90"));
        list_sp.add(new chengji ("5",name,tp));

    }
    private void setClick(){

        chengji1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag==1){
                    Collections.sort(list_sp,new Numsort());
                    adapter.notifyDataSetChanged();
                    flag++;
                }
                else {
                    Collections.sort(list_sp,new Pricesort());
                    adapter.notifyDataSetChanged();
                    flag--;
                }

            }
        });
//        chengji1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Collections.sort(list_sp,new Pricesort());
//                adapter.notifyDataSetChanged();
//            }
//        });

    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent sEIntent = new Intent (paihangbang.this, MainActivity.class);
            startActivity (sEIntent);
            finish ();
         /*R.anim.enter:新的Activity进入时的动画
        R.anim.edit：旧的Activity出去时的动画*/
            // overridePendingTransition(R.anim.enter, R.anim.edit);
        }
        return true;
    }

}