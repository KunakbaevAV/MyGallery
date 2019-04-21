package ru.art.mygallery.model;

import java.util.ArrayList;
import java.util.List;

import ru.art.mygallery.R;

public class Cars {
    private List<Integer> list;

    public Cars() {
        list = new ArrayList<>();
        list.add(R.drawable.car01);
        list.add(R.drawable.car02);
        list.add(R.drawable.car03);
        list.add(R.drawable.car04);
        list.add(R.drawable.car05);
        list.add(R.drawable.car06);
        list.add(R.drawable.car07);
        list.add(R.drawable.car08);
        list.add(R.drawable.car09);
        list.add(R.drawable.car10);
        list.add(R.drawable.car11);
        list.add(R.drawable.car12);
    }

    public List<Integer> getCars() {
        return list;
    }
}
