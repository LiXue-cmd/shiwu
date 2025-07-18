package com.example.shiwu.Tu;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

// 确保引入必要的工具类
import com.example.shiwu.Util.FileUtil;
import com.example.shiwu.Util.Base64Util;
import com.example.shiwu.Util.HttpUtil;

import android.widget.Toast;

// 函数式接口注解（关键修复）
//@FunctionalInterface
public class BaiduAI {
    public interface Callback {
        void onResult(String name, String type); // 唯一抽象方法
    }

    private Context context;
    private Callback callback;

    public BaiduAI(Context context, Callback callback) {
        this.context = context;
        this.callback = callback;
    }

    public void advancedGeneral(final String filePath, final File picPath) {
        new Thread(() -> {
            try {
                // 1. 读取图片文件并转Base64
                byte[] imgData = FileUtil.readFileByBytes(filePath);
                String imgStr = Base64Util.encode(imgData);
                String encode = URLEncoder.encode("data:image/jpeg;base64," + imgStr, "utf-8");

                // 2. 发送HTTP请求（使用HttpUtil工具类）
                String response = HttpUtil.sendPost(
                        "http://api.tianapi.com/txapi/imglajifenlei/index",
                        "key=6f133a1b56bdc2ae2e99e7b3c1e59840&img=" + encode
                );

                // 3. 解析响应结果
                if (response == null) {
                    throw new IOException("网络响应为空");
                }

                JSONObject result = JSON.parseObject(response);
                if (result.getIntValue("code") == 200 && result.containsKey("newslist")) {
                    List<JSONObject> newsList = result.getJSONArray("newslist").toJavaList(JSONObject.class);
                    if (!newsList.isEmpty()) {
                        JSONObject firstItem = newsList.get(0);
                        String name = firstItem.getString("name");
                        String type = firstItem.getString("lajitip");

                        // 回调结果到主线程
                        new Handler(Looper.getMainLooper()).post(() -> {
                            callback.onResult(name, type);
                        });
                    } else {
                        throw new IOException("未找到识别结果");
                    }
                } else {
                    throw new IOException("API错误: " + result.getString("msg"));
                }

            } catch (Exception e) {
                e.printStackTrace();
                // 错误处理（可选扩展onError方法）
                new Handler(Looper.getMainLooper()).post(() -> {
                    Toast.makeText(context, "识别失败: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
            }
        }).start();
    }
}