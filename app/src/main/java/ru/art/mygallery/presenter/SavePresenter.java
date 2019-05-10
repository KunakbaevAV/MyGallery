package ru.art.mygallery.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import ru.art.mygallery.App;
import ru.art.mygallery.model.entity.Hit;
import ru.art.mygallery.model.room.IRoomListener;
import ru.art.mygallery.model.room.Photo;
import ru.art.mygallery.model.room.RoomPresenter;
import ru.art.mygallery.view.IMoxyUpdater;
import ru.art.mygallery.view.IViewHolder;

@InjectViewState
public class SavePresenter extends MvpPresenter<IMoxyUpdater> implements IRoomListener {

    private List<Photo> photos;
    private RecyclerAdapter recyclerAdapter;

    @Inject
    RoomPresenter roomPresenter;


    public SavePresenter() {
        initRoomPresenter();
        recyclerAdapter = new RecyclerAdapter();
        photos = new ArrayList<>();
        roomPresenter.getData();
    }

    private void initRoomPresenter() {
        App.getAppComponent().inject(this);
        roomPresenter.addListener(this);
    }

    public RecyclerAdapter getRecyclerAdapter() {
        return recyclerAdapter;
    }

    @Override
    public void update() {
        photos = roomPresenter.getSavedImagesList();
        getViewState().updateRecyclerView();
    }

    private class RecyclerAdapter implements IRecyclerAdapter {

        @Override
        public void bindView(IViewHolder iViewHolder) {
            int position = iViewHolder.getPos();
            Hit hit = new Hit();
            hit.webformatURL = photos.get(position).url;
            iViewHolder.setHit(hit);
            Photo photo = photos.get(position);
            iViewHolder.setOnClickListener(v -> getViewState().showDetails(photo, position));
        }

        @Override
        public void delete(int position) {
            photos.remove(position);
        }

        @Override
        public int getItemCount() {
            if (photos != null) {
                return photos.size();
            }
            return 0;
        }
    }
}
