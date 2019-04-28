package ru.art.mygallery.presenter;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import ru.art.mygallery.model.entity.Photos;
import ru.art.mygallery.model.retrofit.ApiHelper;
import ru.art.mygallery.view.IActivityUpdater;
import ru.art.mygallery.view.IViewHolder;

/**
 * Реализация логики списка
 */
public class MainPresenter {

    private ArrayList<String> log;
    private Photos photos;
    private RecyclerAdapter recyclerAdapter;
    private ApiHelper apiHelper;
    private IActivityUpdater iActivityUpdater;

    public MainPresenter(IActivityUpdater iActivityUpdater) {
        this.iActivityUpdater = iActivityUpdater;
        log = new ArrayList<>();
        recyclerAdapter = new RecyclerAdapter();
        apiHelper = new ApiHelper();
    }

    public RecyclerAdapter getRecyclerAdapter() {
        return recyclerAdapter;
    }

    public ArrayList<String> showLogs() {
        return log;
    }

    public void getAllPhotos() {
        Observable<Photos> single = apiHelper.requestServer();

        Disposable disposable = single.observeOn(AndroidSchedulers.mainThread()).subscribe(
                photos -> {
                    this.photos = photos;
                    iActivityUpdater.updateRecyclerView();
                }
        );
    }

    //Реализация заполнения списка содержимым
    private class RecyclerAdapter implements IRecyclerAdapter {

        @Override
        public void bindView(IViewHolder holder) {
            int position = holder.getPos();
            holder.setHit(photos.hits.get(position));
            holder.setOnClickListener(v -> iActivityUpdater.showDetails(holder.getHit().webformatURL));
        }

        @Override
        public int getItemCount() {
            if (photos != null) {
                return photos.hits.size();
            }
            return 0;
        }
    }
}
