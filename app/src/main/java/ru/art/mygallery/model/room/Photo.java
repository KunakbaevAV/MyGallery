package ru.art.mygallery.model.room;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "table_images")
public class Photo {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String url;

//    public int positionInRecycler;

    public Photo(String url) {
        this.url = url;
    }

    @NonNull
    @Override
    public String toString() {
        return "id=" + id +
                ", url='" + url + '\'' +
                "\n";
    }
}
