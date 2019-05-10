package ru.art.mygallery;

import android.app.Application;

import androidx.room.Room;

import ru.art.mygallery.dagger.AppComponent;
import ru.art.mygallery.dagger.AppModule;
import ru.art.mygallery.dagger.DaggerAppComponent;
import ru.art.mygallery.model.room.AppDataBase;

public class App extends Application {
    private static AppDataBase appDataBase;
    private static AppComponent appComponent;

    public static AppDataBase getAppDataBase() {
        return appDataBase;
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appDataBase = Room.databaseBuilder(getApplicationContext(),
                AppDataBase.class, "room_database").build();
        appComponent = generateAppComponent();
    }

    private AppComponent generateAppComponent() {
        return DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .build();
    }
}
