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
    //    private IRoomListener roomUpdate;
    private PhotoDao photoDao;
    private List<IRoomListener> listeners;

//    public RoomPresenter(IRoomListener roomUpdate) {
//        photoDao = App.getAppDataBase().imageDao();
//        savedImagesList = new ArrayList<>();
//        this.roomUpdate = roomUpdate;
//    }

    public RoomPresenter() {
        photoDao = App.getAppDataBase().imageDao();
        savedImagesList = new ArrayList<>();
        listeners = new ArrayList<>();
    }

    public void addListener(IRoomListener listener) {
        listeners.add(listener);
    }

    public void removeListener(IRoomListener listener) {
        listeners.remove(listener);
    }

    private void updateListeners() {
        for (IRoomListener listener : listeners) {
            updateListeners();
        }
    }

    public void getData() {
        Disposable disposable = photoDao.getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        savedImages -> {
                            savedImagesList.clear();
                            savedImagesList.addAll(savedImages);
                            updateListeners();
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
                        position -> getData(), //тут нужно подумать, как обновлять список сохраненных изображений
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
