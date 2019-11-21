package me.sideproject.notes.DataBase;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class EditorViewModel extends AndroidViewModel {
    public MutableLiveData<Note> note  = new MutableLiveData<>();
    AppRepository appRepository ;
private Executor executor = Executors.newSingleThreadExecutor() ;
    public EditorViewModel(@NonNull Application application) {
        super(application);
    appRepository = AppRepository.getInstance(application.getApplicationContext()) ;


    }

    public void loadData(final int id) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
              Note note1 =   appRepository.getNoteByID(id);
            note.postValue(note1);
            }
        });
    }

    public void saveNote(String text) {
        Note note = this.note.getValue() ;
        if (note == null )
        { if (TextUtils.isEmpty(text)) {
            return;
        }
note = new Note (text ,new Date()) ;
        }else note.setText(text);
appRepository.insertNote (note) ;
    }

    public void deleteNote() {
    appRepository.deleteNote(note.getValue()) ;
    }
}
