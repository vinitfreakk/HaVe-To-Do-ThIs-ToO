package com.accidentaldeveloper.havetodothistoo;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;
//in this class we are binding every function
public class NotesRepo {
    private NotesDao notesdao;
    private LiveData<List<Notes>> noteslist;
    public NotesRepo(Application application)
    {
        NotesDatabase noteDatabase = NotesDatabase.getInstance(application);
        notesdao = noteDatabase.notesDao();
        noteslist = notesdao.getAllData();
    }
    public void insertData(Notes notes){new InsertTask(notesdao).execute(notes);}
    public void updateData(Notes notes){new UpdateTask(notesdao).execute(notes);}
    public void deleteData(Notes notes){new DeleteTask(notesdao).execute(notes);}



    public LiveData<List<Notes>> getAllData ()
    {
        return noteslist;
    }


    private static class InsertTask extends AsyncTask<Notes,Void,Void>
    {
        private NotesDao notesDao;

        public InsertTask(NotesDao notesDao) {
            this.notesDao = notesDao;
        }

        @Override
        protected Void doInBackground(Notes... notes){
            notesDao.insert(notes[0]);
            return null;
        }
    }

    private static class DeleteTask extends AsyncTask<Notes,Void,Void>
    {
        private NotesDao notesDao;

        public DeleteTask(NotesDao notesDao) {
            this.notesDao = notesDao;
        }

        @Override
        protected Void doInBackground(Notes... notes){
            notesDao.delete(notes[0]);
            return null;
        }
    }

      private static class UpdateTask extends AsyncTask<Notes,Void,Void>
    {
        private NotesDao notesDao;

        public UpdateTask(NotesDao notesDao) {
            this.notesDao = notesDao;
        }

        @Override
        protected Void doInBackground(Notes... notes){
            notesDao.update(notes[0]);
            return null;
        }
    }

}
