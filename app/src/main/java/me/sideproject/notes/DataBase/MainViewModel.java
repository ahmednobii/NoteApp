package me.sideproject.notes.DataBase;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MainViewModel extends AndroidViewModel {
    public LiveData<List<Note>> listOfNotes ;
    private  AppRepository appRepository ;

    public MainViewModel(@NotNull Application application) {
        super(application);
        appRepository = AppRepository.getInstance(application.getApplicationContext()) ;
        listOfNotes = appRepository.noteList ;
    }

    public void addSampleData() {
    appRepository.addSamplesData() ;
    }

    public void DelelteAll() {
   appRepository.deleteAll() ;
    }
}
