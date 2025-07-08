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

public class fenlei3 extends AppCompatActivity {



    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_fenlei3);


        int tupian[]={R.drawable.qi1,R.drawable.qi2,R.drawable.qi3,R.drawable.qi4,R.drawable.qi5,
                R.drawable.qi6,R.drawable.qi7,R.drawable.qi8,R.drawable.qi9,R.drawable.qi10,R.drawable.qi11};
        final String name[]={"废弃砖瓦","动物排泄物","废弃卫生巾","废弃口罩","渣土","废弃瓦片","建筑垃圾","废土","废弃卫生纸","陶瓷碎片","医疗废物"};


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
                Toast.makeText(fenlei3.this,name[position],Toast.LENGTH_SHORT).show ();
            }
        });
    }
}