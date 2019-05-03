package ru.art.mygallery.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import ru.art.mygallery.model.room.Photo;

public interface IMoxyUpdater extends MvpView {
    @StateStrategyType(value = AddToEndStrategy.class)
    void updateRecyclerView();

    @StateStrategyType(value = SkipStrategy.class)
    void showDetails(Photo photo, int position);
}
