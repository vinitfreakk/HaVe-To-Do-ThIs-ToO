package com.accidentaldeveloper.havetodothistoo;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
@Database(entities = Notes.class,version = 1)
public abstract class NotesDatabase extends RoomDatabase {
    private static NotesDatabase instance;
    public abstract NotesDao notesDao();
    public static synchronized NotesDatabase getInstance(Context context){
        if(instance==null){
            instance = Room.databaseBuilder(context.getApplicationContext(),NotesDatabase.class,"notes_database").fallbackToDestructiveMigration().build();
        }
        return instance;
    }
}
