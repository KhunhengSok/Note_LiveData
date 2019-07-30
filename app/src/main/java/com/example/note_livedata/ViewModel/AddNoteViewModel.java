package com.example.note_livedata.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.note_livedata.Model.NoteModel;
import com.example.note_livedata.Model.NoteRepository;

public class AddNoteViewModel extends AndroidViewModel {
    private NoteRepository noteRepository;


    public AddNoteViewModel(@NonNull Application application) {
        super(application);
        noteRepository = new NoteRepository(application);
    }

    public void addNote(NoteModel note){
        noteRepository.insertNote(note);
    }

    public void updateNote(NoteModel note){
        noteRepository.updateNote(note);
    }
}
