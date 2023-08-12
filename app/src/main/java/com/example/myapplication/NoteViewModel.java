package com.example.myapplication;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {


    private Notes_Repo notesRepo;
    private LiveData<List<notes_struc>> note_list;
    public NoteViewModel(@NonNull Application application) {
        super(application);

        notesRepo = new Notes_Repo(application);
        note_list=notesRepo.getAllData();
    }

    public void insert(notes_struc note){
        notesRepo.insertData(note);
    }
    public void delete(notes_struc note){
        notesRepo.deleteData(note);
    }
    public void update(notes_struc note){
        notesRepo.updateData(note);
    }

    public LiveData<List<notes_struc>> getAllData(){
        return note_list;
    }

}
