package ru.art.mygallery.presenter;

import ru.art.mygallery.view.IViewHolder;

public interface IRecyclerThreePresenter {
    void bindView(IViewHolder iViewHolder);

    int getItemCount();
}
