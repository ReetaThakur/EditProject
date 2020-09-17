package com.xyz.recyclerview;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Menu> menuList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.rvMenu);
        buildRecyclerData();
        setRecyclerAdapter();
    }

    private void setRecyclerAdapter() {
        MenuAdapter menuAdapter = new MenuAdapter(menuList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        /*
        Pass the data to the adapter class
         */
        recyclerView.setAdapter(menuAdapter);
    }

    private void buildRecyclerData() {
        menuList = new ArrayList<>();
        menuList.add(new Menu("Masala Dosa", 60, "Anand Bhavan"));
        menuList.add(new Menu("Idli Vada", 40, "Anand Bhavan"));
        menuList.add(new Menu("Paneer Masala Dosa", 90, "A2B"));
        menuList.add(new Menu("Masala Dosa", 60, "Anand Bhavan"));
        menuList.add(new Menu("Masala Dosa", 60, "Anand Bhavan"));
        menuList.add(new Menu("Masala Dosa", 60, "Anand Bhavan"));
        menuList.add(new Menu("Masala Dosa", 60, "Anand Bhavan"));
        menuList.add(new Menu("Masala Dosa", 60, "Anand Bhavan"));
        menuList.add(new Menu("Masala Dosa", 60, "Anand Bhavan"));
        menuList.add(new Menu("Masala Dosa", 60, "Anand Bhavan"));
        menuList.add(new Menu("Idli Vada", 40, "Anand Bhavan"));
        menuList.add(new Menu("Paneer Masala Dosa", 90, "A2B"));
        menuList.add(new Menu("Masala Dosa", 60, "Anand Bhavan"));
        menuList.add(new Menu("Masala Dosa", 60, "Anand Bhavan"));
        menuList.add(new Menu("Masala Dosa", 60, "Anand Bhavan"));
        menuList.add(new Menu("Masala Dosa", 60, "Anand Bhavan"));
        menuList.add(new Menu("Masala Dosa", 60, "Anand Bhavan"));
        menuList.add(new Menu("Masala Dosa", 60, "Anand Bhavan"));

        /*
        comment the lines from 35 to 52 and uncomment this for loop
         */
//        for (int i = 0; i < 100;i++){
//            menuList.add(new Menu("Masala Dosa", 60, "Anand Bhavan"));
//        }
    }
}