package ru.art.mygallery.model.room;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import ru.art.mygallery.App;

public class RoomPresenter {

    private final String TAG = "favourit";
    private List<Photo> savedImagesList;
    private IRoomUpdater roomUpdate;
    private PhotoDao photoDao;

    public RoomPresenter(IRoomUpdater roomUpdate) {
        photoDao = App.getAppDataBase().imageDao();
        savedImagesList = new ArrayList<>();
        this.roomUpdate = roomUpdate;
    }

    public void getData() {
        Disposable disposable = photoDao.getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        savedImages -> {
                            Log.i(TAG, "getData: prevous" + savedImagesList.toString());
                            savedImagesList.clear();
                            savedImagesList.addAll(savedImages);
                            Log.i(TAG, "getData: after" + savedImagesList.toString());
                            roomUpdate.update();
                        },
                        throwable -> Log.i(TAG, "getData: " + throwable)
                );
    }

    public List<Photo> getSavedImagesList() {
        return savedImagesList;
    }

    public void putData(Photo image) {
        Disposable disposable = insertImage(image).observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        id -> Log.i(TAG, "putData: " + id),
                        throwable -> Log.i(TAG, "putData: " + throwable));
    }

    public void deleteData(Photo image) {
        Disposable disposable = deleteImage(image).observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        position -> Log.i(TAG, "deleteData: " + position),
                        throwable -> Log.i(TAG, "deleteData: " + throwable));
    }

    private Single<Long> insertImage(Photo image) {
        return Single.create((SingleOnSubscribe<Long>)
                emitter -> {
                    long id = photoDao.insert(image);
                    emitter.onSuccess(id);
                })
                .subscribeOn(Schedulers.io());
    }

    private Single<Integer> deleteImage(Photo image) {
        return Single.create((SingleOnSubscribe<Integer>)
                emitter -> {
                    int position = photoDao.delete(image);
                    emitter.onSuccess(position);
                }
        ).subscribeOn(Schedulers.io());
    }
}
