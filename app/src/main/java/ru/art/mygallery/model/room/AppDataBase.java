package ru.art.mygallery.model.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Photo.class}, version = 1, exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {
    public abstract PhotoDao imageDao();
}
