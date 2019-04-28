package ru.art.mygallery.view;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.art.mygallery.R;
import ru.art.mygallery.presenter.MainPresenter;

public class MainActivity extends AppCompatActivity implements IActivityUpdater {

    MyAdapter myAdapter;
    @BindView(R.id.my_recycler)
    RecyclerView recyclerView;
    private MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initRecyclerView();
    }

    private void initRecyclerView() {
        presenter = new MainPresenter(this);
        presenter.getAllPhotos();
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        myAdapter = new MyAdapter(presenter, this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(myAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.description) {
            showMessage("Первое приложение с использованием популярных библиотек");
        } else if (item.getItemId() == R.id.action) {
            showMessage("Пока ничего не меняется");
        }
        return super.onOptionsItemSelected(item);
    }

    private void showMessage(String text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }

    @Override
    public void updateRecyclerView() {
        myAdapter.notifyDataSetChanged();
    }

    @Override
    public void showDetails(String imageUrl) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, new FragmentDetails(imageUrl))
                .addToBackStack("backStack")
                .commit();
    }
}
