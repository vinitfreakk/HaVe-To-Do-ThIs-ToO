package com.accidentaldeveloper.havetodothistoo;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
//Dao(data access object) cannot be class it can be either interface or abstract
public interface NotesDao {
    @Insert
    public void insert (Notes note);
    @Update
    public void update (Notes note);
    @Delete
    public void delete (Notes note);
    @Query("SELECT * FROM my_notes")
    public LiveData<List<Notes>> getAllData ();




}
