package com.example.shiwu.Tu;

import android.content.Context;

import com.alibaba.fastjson.JSONObject;
import com.example.shiwu.Util.Base64Util;
import com.example.shiwu.Util.FileUtil;
import com.example.shiwu.Util.HttpUtil;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class BaiduAI {
    private Context context;

    public static String mingzi;
    public static String lajileixing;
    public BaiduAI(Context context){
        this.context = context;
    }
    public String getss(){
        this.mingzi=mingzi;
        return mingzi;
    }
    public String getee(){
        return lajileixing;
    }

    public void advancedGeneral(final String filePath,final File picPath) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                // 请求url
                System.out.println ("请求天行数据");
                String key = "6f133a1b56bdc2ae2e99e7b3c1e59840";  //你的apikey，天行数据控制台个人中心

               String encode = null;
                try {
                    byte[] imgData = FileUtil.readFileByBytes(filePath);
                    String imgStr = Base64Util.encode(imgData);
                    System.out.println ("111111111111111111");
                    imgStr = "data:image/jpeg;base64,"+imgStr;
                    System.out.println (imgStr);
                    encode = URLEncoder.encode(imgStr, "utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace ();
                } catch (IOException e) {
                    e.printStackTrace ();
                }

                //发送 POST 请求
                String sr = HttpUtil.sendPost("http://api.tianapi.com/txapi/imglajifenlei/index", "key=" + key + "&img=" + encode);
                System.out.println(sr);
                resultjs resultjs = JSONObject.parseObject (sr, resultjs.class);
                System.out.println ("--------------");
                mingzi=resultjs.newslist.get (0).name;
                lajileixing=resultjs.newslist.get (0).getLajitip ();
                System.out.println (resultjs.newslist.get (0).name+"-----"+resultjs.newslist.get (0).getLajitip ());
            }
        }).start();
    }
}
