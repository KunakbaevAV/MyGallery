package ru.art.mygallery.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

public interface IMoxyUpdater extends MvpView {
    @StateStrategyType(value = AddToEndStrategy.class)
    void updateRecyclerView();

    @StateStrategyType(value = AddToEndStrategy.class)
    void showDetails(String imageUrl);
}
