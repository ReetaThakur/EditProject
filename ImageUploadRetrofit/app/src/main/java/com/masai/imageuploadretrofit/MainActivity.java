package com.masai.imageuploadretrofit;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.io.File;
import java.io.InputStream;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static int RESULT_LOAD_IMAGE = 1;
    private ImageView mIvGallery;
    private Button mBtnOpenGallery;
    private Button mBtnUploadImage;
    private Uri uri = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        mIvGallery = findViewById(R.id.imageView);
        mBtnOpenGallery = findViewById(R.id.btnGallery);
        mBtnUploadImage = findViewById(R.id.btnUpload);
        mBtnOpenGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPermissionGranted()) {
                    openGallery();
                } else {
                    requestStoragePermissions();
                }
            }
        });

        mBtnUploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (uri == null) return;
                uploadImage(new File(uri.getPath()));
            }
        });
    }

    private void openGallery() {
        Intent i = new Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(i, RESULT_LOAD_IMAGE);
    }

    @SuppressLint("NewApi")
    private void requestStoragePermissions() {
        requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            try {
                InputStream imageStream = getContentResolver().openInputStream(selectedImage);
                mIvGallery.setImageBitmap(BitmapFactory.decodeStream(imageStream));
                uri = selectedImage;

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    private void uploadImage(File file) {
        ApiService apiService = Network.getInstance().create(ApiService.class);
        RequestBody imageBody = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part multipartBody = MultipartBody.Part.createFormData("image", "sad", imageBody);
        apiService.uploadImage(multipartBody,"siddharth hulk").enqueue(
                new Callback<ResponseDTO>() {
                    @Override
                    public void onResponse(Call<ResponseDTO> call, Response<ResponseDTO> response) {

                    }

                    @Override
                    public void onFailure(Call<ResponseDTO> call, Throwable t) {

                    }
                }
        );

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 2 && grantResults.length > 0) {

            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openGallery();
            } else {
                Toast.makeText(MainActivity.this, "Permission Denied ", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean isPermissionGranted() {
        return ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
    }

}