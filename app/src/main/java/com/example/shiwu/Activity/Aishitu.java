package com.example.shiwu.Activity;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shiwu.Tu.BaiduAI;
import com.example.shiwu.Util.PictureCapture;
import com.example.shiwu.R;

import java.io.File;
import java.io.IOException;

public class Aishitu extends AppCompatActivity {
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_PERMISSIONS = 300;

//    private ImageView imageView;
    private TextView resultText;
    private File photoFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aishitu);

//        imageView = findViewById(R.id.imageView);
        resultText = findViewById(R.id.resultText);

        findViewById(R.id.shitu).setOnClickListener(v -> {
            if (checkPermissions()) {
                startCamera();
            }
        });
    }

    private boolean checkPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_PERMISSIONS);
            return false;
        }
        return true;
    }

    private void startCamera() {
        try {
            photoFile = new PictureCapture(
                    getExternalFilesDir(Environment.DIRECTORY_PICTURES),
                    this,
                    this
            ).createImageFile();

            startActivityForResult(
                    new PictureCapture(
                            getExternalFilesDir(Environment.DIRECTORY_PICTURES),
                            this,
                            this
                    ).dispatchTakePictureIntent(photoFile),
                    REQUEST_IMAGE_CAPTURE
            );
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "创建图片文件失败", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSIONS) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                startCamera();
            } else {
                Toast.makeText(this, "权限被拒绝，无法使用相机", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            if (photoFile != null) {
//                imageView.setImageURI(Uri.fromFile(photoFile));
                new BaiduAI(this, (name, type) -> {
                    runOnUiThread(() -> resultText.setText("识别结果: " + name + "\n类型: " + type));
                }).advancedGeneral(photoFile.getAbsolutePath(), photoFile);
            } else {
                Toast.makeText(this, "照片文件为空", Toast.LENGTH_SHORT).show();
            }
        }
    }
}