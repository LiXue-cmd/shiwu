package com.example.shiwu.Util;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.widget.Toast;

import androidx.core.content.FileProvider;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class PictureCapture {
    private Context context;
    private Activity activity;
    private File storageDir;

    public PictureCapture(File storageDir, Context context, Activity activity) {
        this.storageDir = storageDir;
        this.context = context;
        this.activity = activity;
    }

    public File createImageFile() throws IOException {
        if (!storageDir.exists() && !storageDir.mkdirs()) {
            throw new IOException("无法创建存储目录");
        }
        String timeStamp = new SimpleDateFormat("MMddHHmmSS", Locale.getDefault()).format(new Date());
        return new File(storageDir, timeStamp + ".jpg");
    }

    public Intent dispatchTakePictureIntent(File photoFile) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(
                        context,
                        "com.example.shiwu.fileprovider",
                        photoFile
                );
                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);

                // 授予URI权限
                List<ResolveInfo> resInfoList = context.getPackageManager()
                        .queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
                for (ResolveInfo resolveInfo : resInfoList) {
                    String packageName = resolveInfo.activityInfo.packageName;
                    context.grantUriPermission(packageName, photoURI,
                            Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
                }
            }
        }
        return intent;
    }

    public Uri getImageUri(Intent data) {
        try {
            Uri uri = data.getData();
            if (uri == null) return null;

            String imagePath = null;
            if (DocumentsContract.isDocumentUri(context, uri)) {
                String docId = DocumentsContract.getDocumentId(uri);
                if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                    String id = docId.split(":")[1];
                    String selection = MediaStore.Images.Media._ID + "=" + id;
                    imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
                } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                    Uri contentUri = ContentUris.withAppendedId(
                            Uri.parse("content://downloads/public_downloads"),
                            Long.valueOf(docId)
                    );
                    imagePath = getImagePath(contentUri, null);
                }
            } else if ("content".equalsIgnoreCase(uri.getScheme())) {
                imagePath = getImagePath(uri, null);
            } else if ("file".equalsIgnoreCase(uri.getScheme())) {
                imagePath = uri.getPath();
            }

            if (imagePath == null) {
                Toast.makeText(context, "未找到图片路径", Toast.LENGTH_SHORT).show();
                return null;
            }

            return FileProvider.getUriForFile(
                    context,
                    "com.example.shiwu.fileprovider",
                    new File(imagePath)
            );
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "获取图片路径失败: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            return null;
        }
    }

    private String getImagePath(Uri uri, String selection) {
        String path = null;
        ContentResolver resolver = context.getContentResolver();
        Cursor cursor = resolver.query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }
}