package  com.example.note_livedata.View;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelStoreOwner;

import com.example.note_livedata.Model.NoteModel;
import com.example.note_livedata.R;
import com.example.note_livedata.ViewModel.AddNoteViewModel;

public class AddNoteActivity extends AppCompatActivity {
    private EditText editText_title;
    private EditText editText_content;
    private NumberPicker numberPicker_priority;

    private AddNoteViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
    }

    private void ui_init(){
        editText_title = findViewById(R.id.edit_text_title);
        editText_content = findViewById(R.id.edit_text_content);
        numberPicker_priority = findViewById(R.id.number_picker_priority);
        numberPicker_priority.setMinValue(1);
        numberPicker_priority.setMaxValue(10);
        numberPicker_priority.setValue(1);

        viewModel = new AddNoteViewModel(getApplication());

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_white);
        setTitle("Add Note");
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId() ){
            case R.id.save_note:
                saveNote();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void saveNote(){
        String title = editText_title.getText().toString();
        String content = editText_content.getText().toString();
        int priority = numberPicker_priority.getValue();

        //if title or content is empty, show toast and return
        if (title.trim().isEmpty() || content.trim().isEmpty()){
            Toast.makeText(this, "Please insert title and description.", Toast.LENGTH_SHORT).show();
            return ;
        }

        //Insert the current to database
        NoteModel note = new NoteModel(title, content, priority);
        viewModel.addNote(note);
        Toast.makeText(this, "Note Saved", Toast.LENGTH_SHORT).show();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu, menu);

        return true; //Return true = display the menu, otherwise it won't display it
    }
}