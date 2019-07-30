package com.example.note_livedata.View;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.note_livedata.Model.NoteModel;
import com.example.note_livedata.R;
import com.example.note_livedata.ViewModel.NoteViewModel;
import com.example.note_livedata.ViewModel.RecyclerViewAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private String TAG = "note_livedata";
    private NoteViewModel noteViewModel;
    private RecyclerView recyclerView ;
    private RecyclerViewAdapter recyclerViewAdapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager((this)));
        recyclerView.setHasFixedSize(true);

        recyclerViewAdapter = new RecyclerViewAdapter();
        recyclerView.setAdapter(recyclerViewAdapter);

        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);
        noteViewModel.getAllNotes().observe(this, new Observer<List<NoteModel>>() {
            @Override
            public void onChanged(List<NoteModel> noteModels) {
                //Update the recyclerview
                recyclerViewAdapter.setNotes(noteModels);
                Toast.makeText(MainActivity.this, String.valueOf(noteModels.size()), Toast.LENGTH_SHORT).show();
            }
        });

    }



}
