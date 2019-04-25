package ru.art.mygallery.presenter;

import java.util.ArrayList;
import java.util.List;

import ru.art.mygallery.model.Cars;
import ru.art.mygallery.view.IViewDetails;
import ru.art.mygallery.view.IViewHolder;

/**
 * Реализация логики списка
 */

public class ThreePresenter {

    private ArrayList<String> log;
    private RecyclerThreePresenter recyclerMainPresenter;
    private DetailPresenter detailPresenter;

    public ThreePresenter() {
        log = new ArrayList<>();
        recyclerMainPresenter = new RecyclerThreePresenter();
        detailPresenter = new DetailPresenter();
    }

    public RecyclerThreePresenter getRecyclerMainPresenter() {
        return recyclerMainPresenter;
    }

    public DetailPresenter getDetailPresenter() {
        return detailPresenter;
    }

    public ArrayList<String> showLogs() {
        return log;
    }

    //Реализация заполнения списка содержимым
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

    //Реализация вывода детальной картинки
    private class DetailPresenter implements IRecyclerDetails {

        @Override
        public void showDetails(IViewDetails view, IViewHolder holder) {
            view.showDetails(holder.getImageID());
            log.add("Нажата картинка № " + holder.getPos());
        }
    }
}
