package ru.art.mygallery.presenter;

import ru.art.mygallery.view.IViewDetails;
import ru.art.mygallery.view.IViewHolder;

/**
 * Связвает фрагмент для демонстрации картинки и саму картинку
 */
public interface IRecyclerDetails {
    void showDetails(IViewDetails details, IViewHolder holder);
}
