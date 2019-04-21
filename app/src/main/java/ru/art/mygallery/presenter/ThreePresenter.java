package ru.art.mygallery.presenter;

import java.util.List;

import ru.art.mygallery.model.Cars;
import ru.art.mygallery.view.IViewHolder;

public class ThreePresenter {

    private RecyclerThreePresenter recyclerMainPresenter = new RecyclerThreePresenter();

    public RecyclerThreePresenter getRecyclerMainPresenter() {
        return recyclerMainPresenter;
    }

    private class RecyclerThreePresenter implements IRecyclerThreePresenter {

        private List<Integer> cars = new Cars().getCars();

        @Override
        public void bindView(IViewHolder holder) {
            holder.setImage(cars.get(holder.getPos()));
        }

        @Override
        public int getItemCount() {
            return cars.size();
        }
    }
}
