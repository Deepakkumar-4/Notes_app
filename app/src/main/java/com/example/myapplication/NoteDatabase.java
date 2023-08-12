package com.example.myapplication;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

@androidx.room.Database(entities = notes_struc.class,version = 1)
public abstract class NoteDatabase extends RoomDatabase {

    private  static NoteDatabase instance;
    public abstract NotesDao noteDao();
    public static  synchronized NoteDatabase getInstance(Context context){

        if(instance== null){

            instance = Room.databaseBuilder(context.getApplicationContext(),
                    NoteDatabase.class,"note_database").fallbackToDestructiveMigration()
                    .build();
        }

        return instance;

    }
}
