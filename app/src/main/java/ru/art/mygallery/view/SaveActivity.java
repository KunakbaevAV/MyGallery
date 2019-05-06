package ru.art.mygallery.view;

import android.os.Bundle;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.art.mygallery.R;
import ru.art.mygallery.model.room.Photo;
import ru.art.mygallery.presenter.SavePresenter;
import ru.art.mygallery.x_moxy.MvpKtxActivity;

public class SaveActivity extends MvpKtxActivity implements IMoxyUpdater {

    @BindView(R.id.recycler_save)
    RecyclerView recyclerView;

    private MyAdapter myAdapter;
    private FragmentDetailsSave fragmentDetails;

    @InjectPresenter
    SavePresenter presenter;

    @ProvidePresenter
    public SavePresenter providePresenter() {
        return new SavePresenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save);
        ButterKnife.bind(this);
        initRecyclerView();
    }

    private void initRecyclerView() {
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        myAdapter = new MyAdapter(presenter.getRecyclerAdapter(), this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(myAdapter);
    }

    @Override
    public void updateRecyclerView() {
        myAdapter.notifyDataSetChanged();
    }

    @Override
    public void showDetails(Photo photo, int position) {
        fragmentDetails = new FragmentDetailsSave(photo, myAdapter, position);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container_save, fragmentDetails)
                .commit();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (fragmentDetails != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .remove(fragmentDetails)
                    .commit();
        }
    }

    @OnClick(R.id.button_save_back)
    public void backToHome() {
        finish();
    }
}
