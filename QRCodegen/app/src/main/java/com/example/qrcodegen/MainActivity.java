package com.example.qrcodegen;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import static android.graphics.Color.WHITE;

public class MainActivity extends AppCompatActivity {

    EditText content;
    Button button;
    ImageView qrImg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        content = findViewById(R.id.idName);
        button = findViewById(R.id.idBtngen);
        qrImg = findViewById(R.id.idQrimg);


        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String data1 = content.getText().toString();
                String data = content.getText().toString();
                if (data.isEmpty()) {
                    content.setError("Value Required");
                }

                QRCodeWriter writer = new QRCodeWriter();
                try {
                    BitMatrix bitMatrix = writer.encode(data1, BarcodeFormat.QR_CODE, 600, 600);
                    int width = bitMatrix.getWidth();
                    int height = bitMatrix.getHeight();
                    Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
                    for (int x = 0; x < width; x++) {
                        for (int y = 0; y < height; y++) {
                            bmp.setPixel(x, y, bitMatrix.get(x, y) ? Color.rgb(61,113,136) : Color.rgb(178,211,163));
                        }
                    }
                    ((ImageView) findViewById(R.id.idQrimg)).setImageBitmap(bmp);

                } catch (WriterException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}