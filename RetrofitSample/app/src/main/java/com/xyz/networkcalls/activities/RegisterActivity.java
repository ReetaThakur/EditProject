package com.xyz.networkcalls.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.xyz.networkcalls.R;
import com.xyz.networkcalls.model.RegisterResponseModel;
import com.xyz.networkcalls.model.RegisterUserRequestModel;
import com.xyz.networkcalls.network.ApiService;
import com.xyz.networkcalls.network.Network;

import java.net.HttpURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private EditText mEtUserName;
    private EditText mEtPassword;
    private Button mBtnRegister;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initViews();
    }

    private void initViews() {
        mEtUserName = findViewById(R.id.etUsername);
        mEtPassword = findViewById(R.id.etPassword);
        mBtnRegister = findViewById(R.id.btnRegister);
        apiService = Network.getRetrofitInstance().create(ApiService.class);
        mBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegisterUserRequestModel registerUserRequestModel =
                        new RegisterUserRequestModel(mEtUserName.getText().toString(), mEtPassword.getText().toString());
                Call<RegisterResponseModel> call = apiService.getRegisteredUserDetails(registerUserRequestModel);
                call.enqueue(new Callback<RegisterResponseModel>() {
                    @Override
                    public void onResponse(Call<RegisterResponseModel> call, Response<RegisterResponseModel> response) {
                        if (response.code() == HttpURLConnection.HTTP_CREATED && response.body() != null)
                            Toast.makeText(RegisterActivity.this, "Success " +
                                    response.body().getEmail(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<RegisterResponseModel> call, Throwable t) {
                        Toast.makeText(RegisterActivity.this, "Failure " +
                                t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });
    }
}