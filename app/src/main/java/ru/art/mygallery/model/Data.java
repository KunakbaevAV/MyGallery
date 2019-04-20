package ru.art.mygallery.model;

import java.util.ArrayList;
import java.util.List;

public class Data {
    private List<String> list;

    public Data() {
        list = new ArrayList<>();
        list.add("Клубника");
        list.add("Малина");
        list.add("Лимон");
        list.add("Апельсин");
        list.add("Киви");
        list.add("Черника");
        list.add("Голубика");
        list.add("Ананас");
        list.add("Малина");
        list.add("Земляника");
        list.add("Манго");
        list.add("Арбуз");
    }

    public List<String> getList() {
        return list;
    }
}
