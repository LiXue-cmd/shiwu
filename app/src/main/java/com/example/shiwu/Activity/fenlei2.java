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

public class fenlei2 extends AppCompatActivity {


    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_fenlei2);


        int tupian[]={R.drawable.ke1,R.drawable.ke2,R.drawable.ke3,R.drawable.ke4,R.drawable.ke5,
                R.drawable.ke6,R.drawable.ke7,R.drawable.ke8,R.drawable.ke9,R.drawable.ke10,R.drawable.ke11,
                R.drawable.ke12,R.drawable.ke13,R.drawable.ke14,R.drawable.ke15,R.drawable.ke16,R.drawable.ke17,
                R.drawable.ke18,R.drawable.ke19,R.drawable.ke20};
        final String name[]={"弄脏的报纸","牛奶盒子和吸管","紫金","牛奶塑料","玻璃管",
                "为洗干净的洗衣液瓶","弄湿的报纸","旧玻璃酒瓶","塑料叉","手机充电线","旧轮胎","化妆品瓶",
                "塑料冰杯","喝过的矿泉水","塑料管","装饰假花","午餐肉罐头","pla塑料","牛奶盒","塑料单片"};
//        String text[]={"为什么 你当时对我好","又为什么 现在变得冷淡了","我知道 爱要走难阻挠","反正不是我的 我也不该要",
//                "你和我 曾经有共同爱好","谁的耳边 总有绝句在萦绕","我们俩 用文言文对话真的很搞笑","还笑那曹操贪慕着小乔"};

        listView=findViewById (R.id.listview);
        final List<Map<String,Object>> listmap=new ArrayList<Map<String,Object>> ();
        for (int i=0;i<20;i++){
            Map<String,Object> map=new HashMap<String,Object> ();
            map.put ("logo",tupian[i]);
            map.put ("name",name[i]);
         //   map.put ("text",text[i]);
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
                Toast.makeText(fenlei2.this,name[position],Toast.LENGTH_SHORT).show ();
            }
        });

    }
}