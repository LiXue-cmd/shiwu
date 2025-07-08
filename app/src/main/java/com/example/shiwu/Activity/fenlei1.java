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

public class fenlei1 extends AppCompatActivity {



    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_fenlei1);

        int tupian[]={R.drawable.c1,R.drawable.c2,R.drawable.c3,R.drawable.c4,R.drawable.c5,
                R.drawable.c6,R.drawable.c7,R.drawable.c8,R.drawable.c9,R.drawable.c10,R.drawable.c11,
                R.drawable.c12};
        final String name[]={"果壳","菜叶","剩菜剩饭","蛋壳","鱼骨","茶渣","骨头","动物内脏","落叶枯叶","废弃食用油","枯萎的花瓣","汉堡包"};

        listView=findViewById (R.id.listview);
        final List<Map<String,Object>> listmap=new ArrayList<Map<String,Object>> ();
        for (int i=0;i<12;i++){
            Map<String,Object> map=new HashMap<String,Object> ();
            map.put ("logo",tupian[i]);
            map.put ("name",name[i]);
          //  map.put ("text",text[i]);
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
                Toast.makeText(fenlei1.this,name[position],Toast.LENGTH_SHORT).show ();
            }
        });
    }
}