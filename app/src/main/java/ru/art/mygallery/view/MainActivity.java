package ru.art.mygallery.view;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.art.mygallery.R;
import ru.art.mygallery.presenter.MainPresenter;
import ru.art.mygallery.x_moxy.MvpKtxActivity;

public class MainActivity extends MvpKtxActivity implements IMoxyUpdater {

    private MyAdapter myAdapter;
    private FragmentDetails fragmentDetails;

    @BindView(R.id.my_recycler)
    RecyclerView recyclerView;

    @InjectPresenter
    MainPresenter presenter;

    @ProvidePresenter
    public MainPresenter providePresenter() {
        return new MainPresenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initRecyclerView();
    }

    private void initRecyclerView() {
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

        fragmentDetails = new FragmentDetails(imageUrl);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container, fragmentDetails)
                .commit();
    }


    @Override
    protected void onPause() {
        //Переопределяю данный метод, чтобы программа не вылетала при повороте экрана во время просмотра
        //изображения на весь экран.
        super.onPause();
        if (fragmentDetails != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .remove(fragmentDetails)
                    .commit();
        }
    }
}
