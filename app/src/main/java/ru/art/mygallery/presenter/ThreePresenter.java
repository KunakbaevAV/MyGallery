package ru.art.mygallery.presenter;

import java.util.List;

import ru.art.mygallery.model.Data;
import ru.art.mygallery.view.IViewHolder;

public class ThreePresenter {

    private RecyclerThreePresenter recyclerMainPresenter = new RecyclerThreePresenter();

    public RecyclerThreePresenter getRecyclerMainPresenter() {
        return recyclerMainPresenter;
    }

    private class RecyclerThreePresenter implements IRecyclerThreePresenter {

        private Data data = new Data();
        private List<String> list = data.getList();

        @Override
        public void bindView(IViewHolder holder) {
            holder.setText(list.get(holder.getPos()));
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }
}
