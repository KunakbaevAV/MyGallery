package ru.art.mygallery;

import android.app.Application;

import androidx.room.Room;

import ru.art.mygallery.model.room.AppDataBase;

public class App extends Application {
    private static AppDataBase appDataBase;

    public static AppDataBase getAppDataBase() {
        return appDataBase;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appDataBase = Room.databaseBuilder(getApplicationContext(),
                AppDataBase.class, "room_database").build();
    }
}
