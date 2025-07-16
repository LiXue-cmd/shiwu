package com.example.shiwu;

import android.graphics.Bitmap;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.text.Text;
import com.google.mlkit.vision.text.TextRecognition;
import com.google.mlkit.vision.text.TextRecognizer;
import com.google.mlkit.vision.text.chinese.ChineseTextRecognizerOptions;

public class TextRecognitionManager {
    private static final String TAG = "TextRecognitionManager";

    public interface TextRecognitionCallback {
        void onTextRecognitionSuccess(String recognizedText);
        void onTextRecognitionError(String errorMessage);
    }

    private TextRecognizer textRecognizer;
    private TextRecognitionCallback callback;

    public TextRecognitionManager(TextRecognitionCallback callback) {
        this.callback = callback;
        initTextRecognizer();
    }

    private void initTextRecognizer() {
        // 初始化中文文本识别器
        textRecognizer = TextRecognition.getClient(new ChineseTextRecognizerOptions.Builder().build());
    }

    public void recognizeText(Bitmap bitmap) {
        if (bitmap == null) {
            callback.onTextRecognitionError("图片为空");
            return;
        }

        InputImage image = InputImage.fromBitmap(bitmap, 0);
        textRecognizer.process(image)
                .addOnSuccessListener(new OnSuccessListener<Text>() {
                    @Override
                    public void onSuccess(Text visionText) {
                        String recognizedText = visionText.getText();

                        // 可以进一步处理识别结果
                        StringBuilder formattedText = new StringBuilder();
                        for (Text.TextBlock block : visionText.getTextBlocks()) {
                            for (Text.Line line : block.getLines()) {
                                formattedText.append(line.getText()).append("\n");
                            }
                            formattedText.append("\n");
                        }

                        callback.onTextRecognitionSuccess(formattedText.toString().trim());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e(TAG, "Text recognition failed", e);
                        callback.onTextRecognitionError(e.getMessage());
                    }
                });
    }

    public void cleanup() {
        if (textRecognizer != null) {
            textRecognizer.close();
        }
    }
}