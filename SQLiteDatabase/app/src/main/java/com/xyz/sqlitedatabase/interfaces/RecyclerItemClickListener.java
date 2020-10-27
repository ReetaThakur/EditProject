package com.xyz.sqlitedatabase.interfaces;

import com.xyz.sqlitedatabase.model.Student;

public interface RecyclerItemClickListener {

    void onItemClicked(int position, Student student);
}
