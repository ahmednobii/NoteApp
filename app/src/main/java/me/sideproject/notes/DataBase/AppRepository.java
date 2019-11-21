package me.sideproject.notes.DataBase;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import me.sideproject.notes.Utilities.Samples;

public class AppRepository {
    private static AppRepository instance;
    LiveData<List<Note>> noteList;
    private AppDataBase dataBase;
private Executor executor = Executors.newSingleThreadExecutor() ;
    static AppRepository getInstance(Context context) {
        return instance == null ? new AppRepository(context) : instance;
    }

    private AppRepository(Context context) {
        dataBase = AppDataBase.getAppDataBase(context);
        noteList = getAll();
    }

    private LiveData<List<Note>> getAll() {
        return dataBase.noteDao().getAll();
    }

    public void addSamplesData() {
   executor.execute(new Runnable() {
       @Override
       public void run() {
           dataBase.noteDao().inser(Samples.getSamoles());
       }
   });
    }

    public void deleteAll() {
 executor.execute(new Runnable() {
     @Override
     public void run() {
         dataBase.noteDao().deleteAll();
     }
 });
    }
    public Note getNoteByID(int i) {
    return dataBase.noteDao().getById(i) ;
    }

    public void insertNote(final Note note) {
executor.execute(new Runnable() {
    @Override
    public void run() {
        dataBase.noteDao().inser(note);
    }
});
    }


    public void deleteNote(final Note note) {
    executor.execute(new Runnable() {
        @Override
        public void run() {
            dataBase.noteDao().delete(note);
        }
    });
    }
}
