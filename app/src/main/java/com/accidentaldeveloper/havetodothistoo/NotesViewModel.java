package com.accidentaldeveloper.havetodothistoo;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class NotesViewModel extends AndroidViewModel {
private NotesRepo notesRepo;
private LiveData<List<Notes>> noteslist;
    public NotesViewModel(@NonNull Application application) {
        super(application);
        notesRepo = new NotesRepo(application);
        noteslist = notesRepo.getAllData();
    }
    public void insert(Notes notes){
        notesRepo.insertData(notes);
    }
    public void delete(Notes notes){
        notesRepo.deleteData(notes);;
    }
    public void update(Notes notes){
        notesRepo.updateData(notes);
    }
    public LiveData<List<Notes>> getAllNotes(){
        return noteslist;
    }
}
