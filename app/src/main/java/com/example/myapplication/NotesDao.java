package com.example.myapplication;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NotesDao {
@Insert
    public void insert(notes_struc notes);
@Update
    public void update(notes_struc notes);
@Delete
    public void delete(notes_struc notes);
@Query("select * from techTribe_Task order by id desc")
    public LiveData<List<notes_struc>> getAllData();

}
