package com.example.shiwu.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.shiwu.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class fenlei4 extends AppCompatActivity {


    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_fenlei4);

        int tupian[]={R.drawable.you1,R.drawable.you2,R.drawable.you3,R.drawable.you4,R.drawable.you5,
                R.drawable.you6,R.drawable.you7,R.drawable.you8,R.drawable.you9,R.drawable.you10,R.drawable.you11};
        final String name[]={"电灯泡","废电池","农药瓶","水银体温计","废餐盒","废药品","废胶卷","废化妆品","电路板","过期药剂","废墨盒"};

        listView=findViewById (R.id.listview);
        final List<Map<String,Object>> listmap=new ArrayList<Map<String,Object>> ();
        for (int i=0;i<11;i++){
            Map<String,Object> map=new HashMap<String,Object> ();
            map.put ("logo",tupian[i]);
            map.put ("name",name[i]);
           // map.put ("text",text[i]);
            listmap.add (map);
        }
        SimpleAdapter simpleAdapter=new SimpleAdapter (this,listmap,R.layout.lajifenlei,
                new String[]{"logo","name","text"},
                new int[]{R.id.logo,R.id.name,R.id.text});
        listView.setAdapter (simpleAdapter);
        listView.setOnItemClickListener (new AdapterView.OnItemClickListener () {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Map listView=listmap.get (position);
                Toast.makeText(fenlei4.this,name[position],Toast.LENGTH_SHORT).show ();
            }
        });
    }
}