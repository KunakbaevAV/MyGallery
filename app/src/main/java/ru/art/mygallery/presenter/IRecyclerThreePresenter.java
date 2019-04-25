package ru.art.mygallery.presenter;

import ru.art.mygallery.view.IViewHolder;


/**
 * Связывает адаптер списка с презентером списка
 */
public interface IRecyclerThreePresenter {
    void bindView(IViewHolder iViewHolder);

    int getItemCount();
}
