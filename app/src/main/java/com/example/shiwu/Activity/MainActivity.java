package com.example.shiwu;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.FileNotFoundException;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements TextRecognitionManager.TextRecognitionCallback {

    private static final String TAG = "MainActivity";
    private static final int REQUEST_CAMERA_PERMISSION = 200;
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_IMAGE_PICK = 2;

    private ImageView imageView;
    private TextView resultTextView;
    private Button cameraButton;
    private Button galleryButton;
    private Button recognizeButton;
    private ProgressBar progressBar;

    private Bitmap currentBitmap;
    private CameraUtils cameraUtils;
    private TextRecognitionManager textRecognitionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initComponents();
        checkPermissions();
    }

    private void initViews() {
        imageView = findViewById(R.id.imageView);
        resultTextView = findViewById(R.id.resultTextView);
        cameraButton = findViewById(R.id.cameraButton);
        galleryButton = findViewById(R.id.galleryButton);
        recognizeButton = findViewById(R.id.recognizeButton);
        progressBar = findViewById(R.id.progressBar);

        cameraButton.setOnClickListener(v -> {
            if (checkPermissions()) {
                openCamera();
            }
        });

        galleryButton.setOnClickListener(v -> openGallery());
        recognizeButton.setOnClickListener(v -> recognizeText());
    }

    private void initComponents() {
        cameraUtils = new CameraUtils(this);
        textRecognitionManager = new TextRecognitionManager(this);
    }

    private boolean checkPermissions() {
        String[] permissions = {
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
        };

        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, permissions, REQUEST_CAMERA_PERMISSION);
                return false;
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CAMERA_PERMISSION) {
            boolean allPermissionsGranted = true;
            for (int result : grantResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    allPermissionsGranted = false;
                    break;
                }
            }

            if (allPermissionsGranted) {
                Toast.makeText(this, "权限已授予", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "需要权限才能使用相机功能", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void openCamera() {
        Intent takePictureIntent = cameraUtils.createCameraIntent();

        if (takePictureIntent != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        } else {
            showCameraNotAvailableDialog();
        }
    }

    private void showCameraNotAvailableDialog() {
        new AlertDialog.Builder(this)
                .setTitle("相机不可用")
                .setMessage("当前设备没有可用的相机应用。\n" +
                        "这通常发生在Android模拟器中。\n" +
                        "建议：\n" +
                        "1. 在真实设备上测试\n" +
                        "2. 或者从相册选择图片")
                .setPositiveButton("从相册选择", (dialog, which) -> openGallery())
                .setNegativeButton("取消", null)
                .show();
    }

    private void openGallery() {
        Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickPhoto, REQUEST_IMAGE_PICK);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            try {
                if (requestCode == REQUEST_IMAGE_CAPTURE) {
                    handleCameraResult();
                } else if (requestCode == REQUEST_IMAGE_PICK && data != null) {
                    handleGalleryResult(data);
                }
            } catch (Exception e) {
                Log.e(TAG, "Error processing image", e);
                Toast.makeText(this, "处理图片时出错: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void handleCameraResult() throws IOException {
        String photoPath = cameraUtils.getCurrentPhotoPath();
        if (photoPath != null) {
            currentBitmap = loadAndRotateImage(photoPath);
            if (currentBitmap != null) {
                displayImage();
            }
        }
    }

    private void handleGalleryResult(Intent data) throws IOException {
        Uri selectedImage = data.getData();
        if (selectedImage != null) {
            currentBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
            if (currentBitmap != null) {
                displayImage();
            }
        }
    }

    private Bitmap loadAndRotateImage(String imagePath) throws IOException {
        // 获取图片的旋转信息
        ExifInterface exif = new ExifInterface(imagePath);
        int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);

        // 加载图片
        Bitmap bitmap = BitmapFactory.decodeFile(imagePath);

        // 根据EXIF信息旋转图片
        Matrix matrix = new Matrix();
        switch (orientation) {
            case ExifInterface.ORIENTATION_ROTATE_90:
                matrix.postRotate(90);
                break;
            case ExifInterface.ORIENTATION_ROTATE_180:
                matrix.postRotate(180);
                break;
            case ExifInterface.ORIENTATION_ROTATE_270:
                matrix.postRotate(270);
                break;
            default:
                return bitmap;
        }

        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    private void displayImage() {
        imageView.setImageBitmap(currentBitmap);
        recognizeButton.setEnabled(true);
        resultTextView.setText("请点击'识别文本'按钮开始识别");
    }

    private void recognizeText() {
        if (currentBitmap == null) {
            Toast.makeText(this, "请先选择或拍摄图片", Toast.LENGTH_SHORT).show();
            return;
        }

        showProgress(true);
        textRecognitionManager.recognizeText(currentBitmap);
    }

    private void showProgress(boolean show) {
        progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
        recognizeButton.setEnabled(!show);
    }

    @Override
    public void onTextRecognitionSuccess(String recognizedText) {
        showProgress(false);
        if (recognizedText.isEmpty()) {
            resultTextView.setText("未识别到文本内容\n\n建议：\n1. 确保图片清晰\n2. 文字足够大\n3. 光线充足");
        } else {
            resultTextView.setText("识别结果：\n\n" + recognizedText);
        }
    }

    @Override
    public void onTextRecognitionError(String errorMessage) {
        showProgress(false);
        resultTextView.setText("识别失败：" + errorMessage);
        Toast.makeText(this, "识别失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (textRecognitionManager != null) {
            textRecognitionManager.cleanup();
        }
    }
}