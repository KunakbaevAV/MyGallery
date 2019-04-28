package ru.art.mygallery.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import ru.art.mygallery.model.entity.Photos;
import ru.art.mygallery.model.retrofit.ApiHelper;
import ru.art.mygallery.view.IMoxyUpdater;
import ru.art.mygallery.view.IViewHolder;

/**
 * Реализация логики списка
 */
@InjectViewState
public class MainPresenter extends MvpPresenter<IMoxyUpdater> {

    private Photos photos;
    private RecyclerAdapter recyclerAdapter;
    private ApiHelper apiHelper;

    public MainPresenter() {
        recyclerAdapter = new RecyclerAdapter();
        apiHelper = new ApiHelper();
    }

    @Override
    protected void onFirstViewAttach() {
        getAllPhotos();
    }

    public RecyclerAdapter getRecyclerAdapter() {
        return recyclerAdapter;
    }

    private void getAllPhotos() {
        Observable<Photos> single = apiHelper.requestServer();

        Disposable disposable = single.observeOn(AndroidSchedulers.mainThread()).subscribe(
                photos -> {
                    this.photos = photos;
                    getViewState().updateRecyclerView();
                }
        );
    }

    //Реализация заполнения списка содержимым
    private class RecyclerAdapter implements IRecyclerAdapter {

        @Override
        public void bindView(IViewHolder holder) {
            int position = holder.getPos();
            holder.setHit(photos.hits.get(position));
            holder.setOnClickListener(v -> getViewState().showDetails(holder.getHit().webformatURL));
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
