package ru.art.mygallery.presenter;

import ru.art.mygallery.view.IViewHolder;

/**
 * Связывает адаптер списка с презентером списка
 */
public interface IRecyclerAdapter {
    void bindView(IViewHolder iViewHolder);

    void delete(int position);

    int getItemCount();
}
