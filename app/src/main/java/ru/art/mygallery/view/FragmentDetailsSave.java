package ru.art.mygallery.view;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import javax.inject.Inject;

import ru.art.mygallery.R;
import ru.art.mygallery.model.GlideLoader;
import ru.art.mygallery.model.room.Photo;
import ru.art.mygallery.model.room.RoomPresenter;

public class FragmentDetailsSave extends Fragment {

    private Photo photo;
    private MyAdapter adapter;
    private int position;

    @Inject
    RoomPresenter roomPresenter;

    FragmentDetailsSave(Photo photo, MyAdapter adapter, int position) {
        this.photo = photo;
        this.adapter = adapter;
        this.position = position;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details_save, container, false);
        initImage(view);
        initButton(view);
//        roomPresenter = new RoomPresenter();
        return view;
    }

    private void initButton(View view) {
        Button buttonSave = view.findViewById(R.id.button_delete_image);
        buttonSave.setOnClickListener(v -> deleteImage());
    }

    private void deleteImage() {
        roomPresenter.deleteData(photo);
        adapter.delete(position);
        returnActivity();
    }

    private void initImage(View view) {
        ImageView imageDetails = view.findViewById(R.id.image_details);
        GlideLoader glideLoader = new GlideLoader(this.getContext());
        glideLoader.loadImage(photo.url, imageDetails);
        imageDetails.setOnClickListener(v -> returnActivity());
    }

    private void returnActivity() {
        assert getFragmentManager() != null;
        getFragmentManager()
                .beginTransaction()
                .remove(this)
                .commit();
    }
}
