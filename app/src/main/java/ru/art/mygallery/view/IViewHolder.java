package ru.art.mygallery.view;

import android.view.View;

import ru.art.mygallery.model.entity.Hit;

/**
 * Связь всех компонентов, задействованных в отображении элемента списка
 */
public interface IViewHolder {

    Hit getHit();

    void setHit(Hit hit);

    int getPos();

    void setOnClickListener(View.OnClickListener listener);
}
