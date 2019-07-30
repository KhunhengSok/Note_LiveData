package com.example.note_livedata.ViewModel;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.note_livedata.Model.NoteModel;
import com.example.note_livedata.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<NoteModel> allNotes = new ArrayList<>();


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NoteModel mNoteModel = allNotes.get(position);

        holder.content.setText(mNoteModel.getContent());
        holder.title.setText(mNoteModel.getTitle());
        holder.priority.setText(String.valueOf(mNoteModel.getPriority())); //convert from int to String since priority is integer
    }


    public void setNotes(List<NoteModel> notes){
        this.allNotes = notes;
    }

    @Override
    public int getItemCount() {
        return allNotes.size();
    }

    public static  class ViewHolder extends RecyclerView.ViewHolder{
        public TextView title;
        public TextView content;
        public TextView priority;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            content = itemView.findViewById(R.id.content);
            title = itemView.findViewById(R.id.title);
            priority = itemView.findViewById(R.id.priority);
        }
    }
}
