package com.example.shiwu.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shiwu.Tu.BaiduAI;
import com.example.shiwu.Util.PictureCapture;
import com.example.shiwu.R;

import java.io.File;
import java.io.IOException;

public class Aishitu extends AppCompatActivity {

    private Button shitu,xc,jg;
    private File photoFile = null;
    private Button shibie;
    private TextView xianshi;
    String a1,a2;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int REQUEST_SELECT_PHOTO = 2;
    static final int REQUEST_IMAGE_CUTTING = 3;
    static final int REQUEST_IMAGE_CUTTING_ = 4;
    static final int REQUEST_IMAGE_CUTTING_1 = 5;

    BaiduAI baidu = new BaiduAI (this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_aishitu);


        xianshi=findViewById (R.id.xianshi);
        xc=findViewById (R.id.xiangche);
        jg=findViewById (R.id.jieguo);

        shitu=findViewById (R.id.shitu);
        shitu.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                if(ContextCompat.checkSelfPermission(Aishitu.this,
                        Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                        ||ContextCompat.checkSelfPermission(Aishitu.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(Aishitu.this,new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE},300);
                }else {
                    try {
                        System.out.println ("你你你你你牛你i妮妮ininin");
                        photoFile = new PictureCapture (getExternalFilesDir(Environment.DIRECTORY_PICTURES), Aishitu.this, Aishitu.this).createImageFile();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    startActivityForResult(new PictureCapture(getExternalFilesDir(Environment.DIRECTORY_PICTURES), Aishitu.this, Aishitu.this)
                            .dispatchTakePictureIntent(photoFile), REQUEST_IMAGE_CAPTURE);
                }
            }
        });
        xc.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {

                System.out.println ("你你你你你牛你i妮妮ininin");
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(intent,REQUEST_SELECT_PHOTO);
                } else {
                    Toast.makeText(Aishitu.this, "未找到图片查看器", Toast.LENGTH_SHORT).show();
                }
            }
        });
        jg.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                zhuanhuan ();
            }
        });

        int themeColor = getSharedPreferences("themeColor",MODE_PRIVATE).getInt("themeColor",getResources().getColor(R.color.white,null));
        getWindow().setStatusBarColor(themeColor);
    }

    public  void innt(){


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if(resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_IMAGE_CAPTURE:
                    Toast.makeText(Aishitu.this, "相机成功拍摄", Toast.LENGTH_SHORT).show();
                    System.out.println(photoFile);
                    startActivityForResult(new PictureCapture(getExternalFilesDir(Environment.DIRECTORY_PICTURES),this,this)
                            .imageZoom(FileProvider.getUriForFile(this,"com.example.shiwu.fileprovider",photoFile)),REQUEST_IMAGE_CUTTING);
                    break;

                case REQUEST_SELECT_PHOTO:
                    Toast.makeText(Aishitu.this, "相册成功获取", Toast.LENGTH_SHORT).show();
                    startActivityForResult(new PictureCapture(getExternalFilesDir(Environment.DIRECTORY_PICTURES),this,this)
                            .imageZoom(new PictureCapture(getExternalFilesDir(Environment.DIRECTORY_PICTURES),this,this).getImageUri(data)),REQUEST_IMAGE_CUTTING_);
                    break;

                case REQUEST_IMAGE_CUTTING:
                    //上传11提供识别
                    Toast.makeText(Aishitu.this, "获取成功80%", Toast.LENGTH_SHORT).show();
                    new BaiduAI (this).advancedGeneral(getExternalFilesDir(Environment.DIRECTORY_PICTURES) + File.separator + "11.jpg",photoFile);
                    System.out.println ("+++++++++++++++++++++++++++++++++++");
                    break;

                case REQUEST_IMAGE_CUTTING_:
                    //上传11提供识别
                    Toast.makeText(Aishitu.this, "success2", Toast.LENGTH_SHORT).show();
                    new BaiduAI(this).advancedGeneral(getExternalFilesDir(Environment.DIRECTORY_PICTURES) + File.separator + "11.jpg",null);
                    System.out.println ("========================================");
                    break;
                case REQUEST_IMAGE_CUTTING_1:
                    //上传11提供识别
                    System.out.println ("你好牛你好牛你好牛你好牛你好牛");
                    break;

            }
        }
    }
    public void zhuanhuan(){
       a1=baidu.getee ();
       a2=baidu.getss ();
       Toast toast=Toast.makeText (Aishitu.this,a2+"======"+a1,Toast.LENGTH_SHORT);
       toast.setGravity (Gravity.TOP,0,300);
       toast.show ();
    }
}