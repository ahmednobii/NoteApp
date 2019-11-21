package me.sideproject.notes.DataBase;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

@Database(entities = {Note.class},version = 1)
@TypeConverters(DateConverter.class)
public abstract class AppDataBase extends RoomDatabase {
private static final String DATABASENAME  = "Notes.db" ;
private static volatile  AppDataBase appDataBase ;
private static Object lock = new Object() ;
public abstract  NoteDao noteDao() ;
    public static AppDataBase getAppDataBase(Context context) {
if (appDataBase  == null ){
synchronized (lock){
if (appDataBase == null) {
    appDataBase = Room.databaseBuilder(context.getApplicationContext(),AppDataBase.class,DATABASENAME).build();
}}
}
  return appDataBase;  }
}
