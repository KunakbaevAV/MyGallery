package ru.art.mygallery.dagger;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.art.mygallery.model.room.RoomPresenter;

@Module
public class AppModule {

    private final Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Singleton
    @Provides
    RoomPresenter provideRoomPresenter() {
        return new RoomPresenter();
    }
}
