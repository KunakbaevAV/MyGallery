package ru.art.mygallery.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.art.mygallery.R;
import ru.art.mygallery.model.room.Photo;
import ru.art.mygallery.presenter.SavePresenter;

public class SaveActivity extends AppCompatActivity implements IMoxyUpdater {

    @BindView(R.id.recycler_save)
    RecyclerView recyclerView;

    SavePresenter presenter;
    private MyAdapter myAdapter;
    private FragmentDetailsSave fragmentDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save);
        ButterKnife.bind(this);
        presenter = new SavePresenter(this);
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
    public void showDetails(Photo photo) {
        FragmentDetailsSave fragment = new FragmentDetailsSave(photo);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container_save, fragment)
                .commit();
    }

    @OnClick(R.id.button_save_back)
    public void backToHome() {
        finish();
    }
}
