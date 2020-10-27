package com.xyz.sqlitedatabase.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.xyz.sqlitedatabase.DBManager;
import com.xyz.sqlitedatabase.R;
import com.xyz.sqlitedatabase.Student;

import java.io.Serializable;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private DBManager dbManager;
    private EditText mEtName;
    private EditText mEtAddress;
    private Button mBtnSave;
    private Button mBtnFetch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViewsAndListeners();
        dbManager = DBManager.getInstance(this);
        dbManager.open();
    }

    private void initViewsAndListeners() {
        mEtAddress = findViewById(R.id.etAddress);
        mEtName = findViewById(R.id.etName);
        mBtnSave = findViewById(R.id.btnSave);
        mBtnFetch = findViewById(R.id.btnFetch);
        mBtnSave.setOnClickListener(this);
        mBtnFetch.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btnSave:
                if (isDataValid()) {
                    Thread thread = new Thread(insertTaskToDB());
                    thread.start();
                }
                break;
            case R.id.btnFetch:
                Thread thread = new Thread(fetchFromDB());
                thread.start();
                break;
        }
    }

    private boolean isDataValid() {
        if (mEtName.getText().toString().isEmpty()) {
            mEtName.setError("Name Field is empty");
            return false;
        }
        if (mEtAddress.getText().toString().isEmpty()) {
            mEtAddress.setError("Address Field is empty");
            return false;
        }
        return true;
    }

    private Runnable insertTaskToDB() {
        return new Runnable() {
            @Override
            public void run() {
                dbManager.insert(mEtName.getText().toString(), mEtAddress.getText().toString());
            }
        };
    }

    private Runnable fetchFromDB() {
        return new Runnable() {
            @Override
            public void run() {
                final List<Student> studentList = dbManager.fetch();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(MainActivity.this, StudentListActivity.class);
                        intent.putExtra("studentList", (Serializable) studentList);
                        startActivity(intent);
                    }
                });
            }
        };
    }
}