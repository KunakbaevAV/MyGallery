package ru.art.mygallery.view;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import ru.art.mygallery.R;

/**
 * Реализация детального изображения
 */
public class FragmentDetails extends Fragment {

    private int imageRes;

    FragmentDetails(int imageRes) {
        this.imageRes = imageRes;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details, container, false);
        ImageView image = view.findViewById(R.id.image_details);
        image.setImageResource(imageRes);
        return view;
    }

}
