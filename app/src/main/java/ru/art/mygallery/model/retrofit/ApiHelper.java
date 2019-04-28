package ru.art.mygallery.model.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.art.mygallery.model.entity.Photos;

import static ru.art.mygallery.AppConstants.APY_KEY;

public class ApiHelper {
    public Observable<Photos> requestServer() {

        IApiService api = new Retrofit.Builder()
                .baseUrl("https://pixabay.com")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(getGsonConverterFactory())
                .build()
                .create(IApiService.class);
        return api.getPhoto(APY_KEY).subscribeOn(Schedulers.io());
    }

    private GsonConverterFactory getGsonConverterFactory() {
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
        return GsonConverterFactory.create(gson);
    }
}
