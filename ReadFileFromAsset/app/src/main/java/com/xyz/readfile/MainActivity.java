package com.xyz.readfile;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private Button mBtnReadData;
    private RecyclerView recyclerView;
    private List<FormulaModel> formulaModelList = new ArrayList<>();
    private FormulaAdapter formulaAdapter;
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            loadJsonFromAsset();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViewsAndListeners();
        setRecyclerAdapter();

    }

    /**
     * Set the recycler adapter data initially with empty list as the data will load in background thread
     * and it takes some time
     */
    private void setRecyclerAdapter() {
        formulaAdapter = new FormulaAdapter(formulaModelList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(formulaAdapter);
    }

    private void initViewsAndListeners() {
        mBtnReadData = findViewById(R.id.btnReadData);
        recyclerView = findViewById(R.id.recyclerView);
        mBtnReadData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startBackgroundThread();
            }
        });
    }

    /**
     * Since reading a file can take some time, in order to prevent UI thread from blocking lets
     * create a background thread and read the json file
     */
    private void startBackgroundThread() {
        Thread thread = new Thread(runnable);
        thread.start();
    }

    /**
     * This method will load the json file from assets folder and read it
     */
    private void loadJsonFromAsset() {
        try {
            InputStream inputStream = getAssets().open("formula.json");
            int data = inputStream.read();
            StringBuilder stringBuilder = new StringBuilder();
            while (data != -1) {
                char ch = (char) data;
                stringBuilder.append(ch);
                data = inputStream.read();
            }
            buildDataFromJSON(stringBuilder.toString());

        } catch (Exception e) {
            Log.d(TAG, "Exception occurred with a message " +e.getLocalizedMessage());
        }
    }

    /**
     * Once the json file is read from file, parse it to POJO class
     *
     * @param json
     */
    private void buildDataFromJSON(String json) {
        Type type = new TypeToken<ResponseModel>() {
        }.getType();
        ResponseModel responseModel = new Gson().fromJson(json, type);
        formulaModelList = responseModel.getFormulaModelList();
        updateRecyclerView();
    }

    /**
     * Since all the reading operations happen in background thread, update the UI in the main thread
     */
    private void updateRecyclerView() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                formulaAdapter.updateData(formulaModelList);
            }
        });
    }
}