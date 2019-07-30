package com.example.note_livedata.Model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NoteDAO{
    @Insert
    public void insertNote(NoteModel note);

    @Update
    public void updateNote(NoteModel note);

    @Delete
    public void deleteNote(NoteModel note);

    @Query("Delete from note_table")
    public void deleteAllNotes();

    @Query("Select * from note_table ORDER BY priority DESC ")
    public LiveData<List<NoteModel>> getAllNotes();
}