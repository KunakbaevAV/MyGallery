package ru.art.mygallery.model.retrofit;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.art.mygallery.model.entity.Photos;

public interface IApiService {
    @GET("api")
    Observable<Photos> getPhoto(@Query("key") String key);
}
