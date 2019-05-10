package ru.art.mygallery.dagger;

import javax.inject.Singleton;

import dagger.Component;
import ru.art.mygallery.presenter.SavePresenter;
import ru.art.mygallery.view.FragmentDetails;
import ru.art.mygallery.view.FragmentDetailsSave;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    void inject(FragmentDetails fragment);

    void inject(FragmentDetailsSave fragment);

    void inject(SavePresenter presenter);
}
