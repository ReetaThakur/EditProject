package com.xyz.sqlitedatabase.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xyz.sqlitedatabase.R;
import com.xyz.sqlitedatabase.interfaces.RecyclerItemClickListener;
import com.xyz.sqlitedatabase.model.Student;
import com.xyz.sqlitedatabase.adapter.StudentListAdapter;

import java.util.List;

public class StudentListActivity extends AppCompatActivity implements RecyclerItemClickListener {

    private List<Student> studentList;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);
        initViews();
        getDataFromIntent();
        setRecyclerAdapter();
    }

    private void initViews() {
        recyclerView = findViewById(R.id.recyclerView);
    }

    private void setRecyclerAdapter() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        StudentListAdapter adapter = new StudentListAdapter(studentList, this);
        recyclerView.setAdapter(adapter);
    }

    private void getDataFromIntent() {
        if (getIntent() != null && getIntent().getSerializableExtra("studentList") != null) {
            studentList = (List<Student>) getIntent().getSerializableExtra("studentList");
        }
    }

    @Override
    public void onItemClicked(int position, Student student) {

    }
}