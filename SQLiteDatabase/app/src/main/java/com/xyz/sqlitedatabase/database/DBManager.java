package com.xyz.sqlitedatabase.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.xyz.sqlitedatabase.model.Student;

import java.util.ArrayList;
import java.util.List;

public class DBManager {
    private static DBManager dbManager;
    private DatabaseHelper dbHelper;
    private Context context;
    private SQLiteDatabase sqLiteDatabase;

    private DBManager(Context context) {
        this.context = context;
    }

    public static DBManager getInstance(Context context) {
        if (dbManager == null) {
            return new DBManager(context);
        }
        return dbManager;
    }

    public void open() {
        dbHelper = new DatabaseHelper(context);
        sqLiteDatabase = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public void insert(String name, String address) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.STUDENT_NAME, name);
        contentValue.put(DatabaseHelper.STUDENT_ADDRESS, address);
        sqLiteDatabase.insert(DatabaseHelper.TABLE_NAME, null, contentValue);
    }


    public List<Student> fetch() {
        List<Student> studentList = new ArrayList<>();
        String[] columns = new String[]{DatabaseHelper.ID, DatabaseHelper.STUDENT_NAME, DatabaseHelper.STUDENT_ADDRESS};
        Cursor cursor = sqLiteDatabase.query(DatabaseHelper.TABLE_NAME, columns, null, null, null, null, null);
        while (cursor.moveToNext()) {
            Student student = new Student(cursor.getString(cursor.getColumnIndex(DatabaseHelper.STUDENT_NAME)),
                    cursor.getString(cursor.getColumnIndex(DatabaseHelper.STUDENT_ADDRESS)),
                    cursor.getInt(cursor.getColumnIndex(DatabaseHelper.ID)));
            studentList.add(student);
        }
        return studentList;
    }
}
