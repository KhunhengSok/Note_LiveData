package com.example.note_livedata.Model;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.lang.ref.WeakReference;
import java.util.List;


public class NoteRepository {
    private NoteDatabase db ;
    private NoteDAO noteDAO;
    private LiveData<List<NoteModel>> allNotes ;
    private static String TAG = "NoteRepository";

    public NoteRepository(Application application){

        db = NoteDatabase.getInstance(application.getApplicationContext());
        noteDAO = db.noteDAO();
        allNotes = noteDAO.getAllNotes();
        new getAllNotesAsyncTask(noteDAO, allNotes).execute();

    }

    public LiveData<List<NoteModel>> getAllNotes(){
        return this.allNotes;
    }

    public void updateNote(NoteModel note){
        new updateNoteAsyncTask(noteDAO).execute(note);
    }

    public void insertNote(NoteModel note){
        new insertNoteAsyncTask(noteDAO).execute(note);
    }

    public void deleteNote(NoteModel note){
        new deleteNoteAsyncTask(noteDAO).execute(note);

    }

    public void deleteAllNote(){
        new deleteAllNotesAsyncTask(noteDAO).execute();

    }

    private class insertNoteAsyncTask extends AsyncTask<NoteModel, Void, Void>{
        private NoteDAO noteDAO ;

        private insertNoteAsyncTask(NoteDAO noteDAO) {
            this.noteDAO = noteDAO;
        }

        @Override
        protected Void doInBackground(NoteModel... noteModels) {
            noteDAO.insertNote(noteModels[0]);
            return null;
        }
    }

    private class deleteNoteAsyncTask extends AsyncTask<NoteModel, Void, Void>{
        private NoteDAO noteDAO ;

        private deleteNoteAsyncTask(NoteDAO noteDAO) {
            this.noteDAO = noteDAO;
        }

        @Override
        protected Void doInBackground(NoteModel... noteModels) {
            noteDAO.deleteNote(noteModels[0]);
            return null;
        }
    }

    private class updateNoteAsyncTask extends AsyncTask<NoteModel, Void, Void>{
        private NoteDAO noteDAO ;

        private updateNoteAsyncTask(NoteDAO noteDAO) {
            this.noteDAO = noteDAO;
        }

        @Override
        protected Void doInBackground(NoteModel... noteModels) {
            noteDAO.updateNote(noteModels[0]);
            return null;
        }
    }

    private class deleteAllNotesAsyncTask extends AsyncTask<Void, Void, Void>{
        private NoteDAO noteDAO ;

        private deleteAllNotesAsyncTask(NoteDAO noteDAO) {
            this.noteDAO = noteDAO;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDAO.deleteAllNotes();
            return null;
        }
    }


    private static class getAllNotesAsyncTask extends AsyncTask<Void, Void, LiveData<List<NoteModel>>>{
        private NoteDAO noteDAO ;
        private LiveData<List<NoteModel >> allNote;

        public getAllNotesAsyncTask(NoteDAO noteDAO, LiveData<List<NoteModel>> initial_notes) {
            this.noteDAO = noteDAO;
            this.allNote = initial_notes;
        }


        @Override
        protected LiveData<List<NoteModel>> doInBackground(Void... voids) {
            return noteDAO.getAllNotes() ;
        }

        @Override
        protected void onPostExecute(LiveData<List<NoteModel>> listLiveData) {
            super.onPostExecute(listLiveData);
            this.allNote = listLiveData;
            Log.d(TAG, "onPostExecute: " + ( this.allNote == null)  );

        }
    }

}
