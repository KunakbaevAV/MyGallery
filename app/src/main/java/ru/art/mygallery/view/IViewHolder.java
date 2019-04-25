package ru.art.mygallery.view;

/**
 * Связь всех компонентов, задействованных в отображении элемента списка
 */
public interface IViewHolder {
    void setText(String text);

    void setImage(int image);

    int getImageID();

    int getPos();
}
