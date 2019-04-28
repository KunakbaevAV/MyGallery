package ru.art.mygallery.view;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import ru.art.mygallery.R;
import ru.art.mygallery.model.GlideLoader;

/**
 * Реализация изображения на весь экран
 */
public class FragmentDetails extends Fragment {

    private String imageUrl;

    FragmentDetails(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details, container, false);
        loadImage(view);
        return view;
    }

    private void loadImage(View view) {
        GlideLoader glideLoader = new GlideLoader(this.getContext());
        ImageView image = view.findViewById(R.id.image_details);
        glideLoader.loadImage(imageUrl, image);
    }

}