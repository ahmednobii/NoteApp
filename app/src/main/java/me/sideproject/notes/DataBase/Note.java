package me.sideproject.notes.DataBase;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

@Entity (tableName = "NotesTable")
public class Note {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id ;
    @ColumnInfo(name = "Text")
    private String text ;
    @ColumnInfo(name = "date")
    private Date date ;

    public Note(int id, String text, Date date) {
        this.id = id;
        this.text = text;
        this.date = date;
    }
@Ignore
    public Note(String text, Date date) {
        this.text = text;
        this.date = date;
    }
@Ignore
    public Note() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
