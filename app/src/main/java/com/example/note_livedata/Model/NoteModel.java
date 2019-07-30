package com.example.note_livedata.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "note_table")
public class NoteModel {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String title ;
    private String content;
    private int priority;

    public NoteModel(String title, String content, int priority1 ) {
        this.title = title;
        this.content = content;
        this.priority = priority;
    }

    public NoteModel(String title, String content){
        this(title,content, 1);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setId(int id){this.id = id;}

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public int getPriority() {
        return priority;
    }

    public int getId(){return id;}

}
