package com.example.myapplication;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class Notes_Repo {

    private NotesDao notes_dao;
    private LiveData<List<notes_struc>> note_list;

    public Notes_Repo(Application application) {
        NoteDatabase noteDatabase = NoteDatabase.getInstance(application);
        notes_dao = noteDatabase.noteDao();
        note_list = notes_dao.getAllData();
    }

    public void insertData( notes_struc note){ new InsertTask(notes_dao).execute(note);}
    public void updateData( notes_struc note){new UpdateTask(notes_dao).execute(note);}
    public void deleteData( notes_struc note){new DeleteTask(notes_dao).execute(note);}
    public LiveData<List<notes_struc>> getAllData(){
        return note_list;
    }



    private static  class  InsertTask extends AsyncTask<notes_struc,Void, Void>{
        private  NotesDao notesDao;
        public InsertTask(NotesDao notesDao) {
            this.notesDao = notesDao;
        }
        @Override
        protected Void doInBackground(notes_struc... notesStrucs) {
            notesDao.insert(notesStrucs[0]);
            return null;
        }


    }
    private static  class  DeleteTask extends AsyncTask<notes_struc,Void, Void>{
        private  NotesDao notesDao;
        public DeleteTask(NotesDao notesDao) {
            this.notesDao = notesDao;
        }
        @Override
        protected Void doInBackground(notes_struc... notesStrucs) {
            notesDao.delete(notesStrucs[0]);
            return null;
        }

    }
    private static  class  UpdateTask extends AsyncTask<notes_struc,Void, Void> {
        private NotesDao notesDao;

        public UpdateTask(NotesDao notesDao) {
            this.notesDao = notesDao;
        }

        @Override
        protected Void doInBackground(notes_struc... notesStrucs) {
            notesDao.update(notesStrucs[0]);
            return null;
        }

    }
}
