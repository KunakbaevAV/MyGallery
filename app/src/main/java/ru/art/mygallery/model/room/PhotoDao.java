package ru.art.mygallery.model.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface PhotoDao {

    @Query("SELECT * FROM table_images")
    Single<List<Photo>> getAll();

    @Insert
    long insert(Photo image);

    @Delete
    int delete(Photo image);
}
