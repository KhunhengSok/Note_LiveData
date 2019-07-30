package com.example.note_livedata.Model;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {NoteModel.class}, version = 1, exportSchema = false)
public abstract class NoteDatabase extends RoomDatabase{
    private static NoteDatabase INSTANCE ;
    public abstract NoteDAO noteDAO();

    private String Tag = "NoteDatabase";

    public static NoteDatabase getInstance(Context context){
        synchronized (NoteDatabase.class){
            if( INSTANCE == null){
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(), NoteDatabase.class, "note_database")
                        .fallbackToDestructiveMigration()
                        .addCallback(callback)
                        .build();
            }
        }

        return INSTANCE;
    }

    private static RoomDatabase.Callback callback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new initialDatabaseAsync(INSTANCE).execute();
        }
    };

    static class initialDatabaseAsync extends AsyncTask<Void, Void, Void>{
        private NoteDAO noteDAO ;

        public initialDatabaseAsync(NoteDatabase database) {
            this.noteDAO = database.noteDAO() ;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            this.noteDAO.insertNote(new NoteModel("School", "At 7am", 1));
            this.noteDAO.insertNote(new NoteModel("Game", "At 11am", 2));
            this.noteDAO.insertNote(new NoteModel("Sleep", "At 1pm", 3));
            return null;
        }
    }
}
