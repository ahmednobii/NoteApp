package me.sideproject.notes.DataBase;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void inser(Note note);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void inser(List<Note> note);

    @Query("Select * From NotesTable Order by date Desc")
    public LiveData<List<Note>> getAll();

    @Query("Select * From NotesTable Where id = :id")
    public Note getById(int id);

    @Query("Delete From NotesTable")
    void deleteAll();

    @Query("Select Count(*) from notestable")
    int getCount();
@Delete
    void delete(Note note) ;
}
