package com.example.shiwu.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import interfaces.heweather.com.interfacesmodule.bean.Lang;
import interfaces.heweather.com.interfacesmodule.bean.Unit;
import interfaces.heweather.com.interfacesmodule.bean.air.now.AirNow;
import interfaces.heweather.com.interfacesmodule.bean.weather.now.Now;
import interfaces.heweather.com.interfacesmodule.view.HeConfig;
import interfaces.heweather.com.interfacesmodule.view.HeWeather;

import com.example.shiwu.R;

public class Tianqi extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private TextView tv_airqlty,tv_tianqi,tv_kongqi,tv_city;
    private EditText shuru;
    private Button shangchuan;
    String a1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_tianqi);

       initWeather ();
       initViews ();
        shangchuan.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                a1=shuru.getText ().toString ();
                getWeather(a1);
            }
        });
    }

    private void initViews() {
        tv_tianqi =(TextView) findViewById(R.id.tv_tianqi);
        tv_kongqi =(TextView) findViewById(R.id.tv_kongqi);
        tv_airqlty =(TextView) findViewById(R.id.tv_airqlty);
        tv_city =(TextView) findViewById(R.id.tv_city);

        shuru=findViewById (R.id.shuru);
        shangchuan=findViewById (R.id.shangchuan);

    }

   //
   //HE2012111915421694
    //23d662fad7b949d4a0638cd285045a95
    private void initWeather() {
        HeConfig.init("HE2012111915421694","23d662fad7b949d4a0638cd285045a95");   // b4037e804b434545905abeaa7affc4c4
        HeConfig.switchToFreeServerNode();
    }

    @Override
    protected void onStart() {
        super.onStart();
        //   getWeather();
    }

    private void getWeather(String a1) {

        HeWeather.getWeatherNow(Tianqi.this, a1/*CN+中国城市代码*/,
                Lang.CHINESE_SIMPLIFIED , Unit.METRIC ,
                new HeWeather.OnResultWeatherNowBeanListener() {
                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "Weather Now onError: ", e);
                    }

                    @Override
                    public void onSuccess(Now dataObject) {
                        String jsonData = new Gson().toJson(dataObject);
                        Log.i(TAG, " Weather Now onSuccess: " + jsonData);
                        String tianqi = null,wendu = null,city = null;
                        if (dataObject.getStatus().equals("ok")){
                            String JsonNow = new Gson().toJson(dataObject.getNow());
                            String JsonBasic = new Gson().toJson(dataObject.getBasic());
                            JSONObject jsonObject = null;
                            JSONObject jsonObject2 = null;
                            try {
                                jsonObject = new JSONObject(JsonNow);
                                jsonObject2 = new JSONObject(JsonBasic);
                                tianqi = jsonObject.getString("cond_txt");
                                wendu = jsonObject.getString("tmp");
                                city = jsonObject2.getString("location");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }else {
                            Toast.makeText(Tianqi.this,
                                    "有错误",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        String wendu2 = wendu +"℃";
                        tv_city.setText("城市:"+city);
                        tv_tianqi.setText("当前天气:"+tianqi);
                        tv_kongqi.setText("当前温度:"+wendu2);
                    }
                });

        HeWeather.getAirNow(Tianqi.this, a1/*CN+中国城市代码*/,
                Lang.CHINESE_SIMPLIFIED, Unit.METRIC, new HeWeather.OnResultAirNowBeansListener() {
                    @Override
                    public void onError(Throwable throwable) {
                        Log.i(TAG,"ERROR IS:",throwable);
                    }
                    @Override
                    public void onSuccess(AirNow airNow) {
                        Log.i(TAG,"Air Now onSuccess:"+new Gson().toJson(airNow));
                        String airStatus = airNow.getStatus();
                        if (airStatus.equals("ok")){
                            String jsonData = new Gson().toJson(airNow.getAir_now_city());
                            String aqi = null,qlty = null;
                            JSONObject objectAir = null;
                            try {
                                objectAir = new JSONObject(jsonData);
                                aqi = objectAir.getString("aqi");
                                qlty = objectAir.getString("qlty");
                                tv_airqlty.setText("天气状况:"+qlty+" "+"空气质量:"+"("+aqi+")");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }else {
                            Toast.makeText(Tianqi.this,
                                    "有错误",Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                });



    }
}