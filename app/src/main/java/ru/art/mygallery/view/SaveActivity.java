package ru.art.mygallery.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.art.mygallery.R;
import ru.art.mygallery.presenter.SavePresenter;

public class SaveActivity extends AppCompatActivity implements IMoxyUpdater {

    @BindView(R.id.favourites_recycler)
    RecyclerView recyclerView;

    SavePresenter presenter;
    private MyAdapter myAdapter;

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
    public void showDetails(String imageUrl) {

    }
}
