package com.example.note_livedata.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.note_livedata.Model.NoteModel;
import com.example.note_livedata.Model.NoteRepository;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {
    private NoteRepository noteRepository;
    private LiveData<List<NoteModel>> allNotes;


    public NoteViewModel(@NonNull Application application) {
        super(application);
        noteRepository = new NoteRepository(application);
        allNotes = noteRepository.getAllNotes();
    }

    public LiveData<List<NoteModel>> getAllNotes(){
        return this.allNotes;
    }

    public void insertNote(NoteModel note){
        noteRepository.insertNote(note);
    }

    public void updateNote(NoteModel note){
        noteRepository.updateNote(note);
    }

    public void deleteNote(NoteModel note){
        noteRepository.deleteNote(note);
    }

    public void deleteAllNote(){
        noteRepository.deleteAllNote();
    }



}
