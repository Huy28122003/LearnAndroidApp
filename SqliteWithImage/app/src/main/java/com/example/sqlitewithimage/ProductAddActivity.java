package com.example.sqlitewithimage;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.ByteArrayOutputStream;

public class ProductAddActivity extends AppCompatActivity {

    final int REQUEST_CODE_CAMERA = 1;
    final int REQUEST_CODE_FOLDER = 2;
    Button btnCofirm;
    Button btnCancel;
    EditText edtName, edtStatus, edtPrice;
    ImageButton imgCamera, imgFolder;
    ImageView imgProduct;
    Bitmap bitmap;
    DataBase dataBase;

    ActivityResultLauncher launcherCamera = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult o) {
                    if(o.getResultCode() == RESULT_OK){
                        Intent intent = o.getData();

                        bitmap = (Bitmap) intent.getExtras().get("data");
                        imgProduct.setImageBitmap(bitmap);
                    }
                }
            }
    );

    ActivityResultLauncher launcherFolder = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult o) {
                    if(o.getResultCode() == RESULT_OK){
                        Intent intent = o.getData();
                        assert intent != null;
                        Uri uri = intent.getData();
                        try{
                            bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                            imgProduct.setImageBitmap(bitmap);
                        }
                        catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_add);
        dataBase = new DataBase(this,"Product.sqlite",null,1);

        mapping();
        imgCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(
                        ProductAddActivity.this,
                        new String[]{android.Manifest.permission.CAMERA},
                        REQUEST_CODE_CAMERA
                );

            }
        });

        imgFolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(
                        ProductAddActivity.this,
                        new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_FOLDER
                );
            }
        });

        btnCofirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(edtPrice.getText().toString().length()!=0 && edtStatus.getText().toString().length()!=0 && edtName.getText().toString().length()!=0){
                    String id = null;
                    String name = edtName.getText().toString();
                    String status = edtStatus.getText().toString();
                    double price = Double.parseDouble(edtPrice.getText().toString());
                    BitmapDrawable drawable = (BitmapDrawable) imgProduct.getDrawable();
                    Bitmap bitmap = drawable.getBitmap();
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                    byte[] image = byteArrayOutputStream.toByteArray();
                    dataBase.insertData(name,status,price,image);
                    startActivity(new Intent(ProductAddActivity.this,MainActivity.class));
                }
                else{
                    Toast.makeText(ProductAddActivity.this, "Have null field", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProductAddActivity.this,MainActivity.class));

            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case REQUEST_CODE_CAMERA:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    launcherCamera.launch(intent);
                }else{
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
                }
                break;
            case REQUEST_CODE_FOLDER:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setType("image/*");
                    launcherFolder.launch(intent);
                }
                else{
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
                }
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public void mapping(){
        edtName = findViewById(R.id.edtName);
        edtStatus = findViewById(R.id.edtStatus);
        edtPrice = findViewById(R.id.edtPrice);
        imgProduct = findViewById(R.id.imgProduct);
        imgCamera = findViewById(R.id.imageCamera);
        imgFolder = findViewById(R.id.imageFolder);
        btnCofirm = findViewById(R.id.btnCofirm);
        btnCancel = findViewById(R.id.btnCancel);
    }
}