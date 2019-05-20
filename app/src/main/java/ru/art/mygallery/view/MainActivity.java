package ru.art.mygallery.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.art.mygallery.R;
import ru.art.mygallery.model.room.Photo;
import ru.art.mygallery.presenter.MainPresenter;
import ru.art.mygallery.x_moxy.MvpKtxActivity;

public class MainActivity extends MvpKtxActivity implements IMoxyUpdater {

    private MyAdapter myAdapter;
    private FragmentDetails fragmentDetails;

    @BindView(R.id.my_recycler)
    RecyclerView recyclerView;

    @BindView(R.id.button_show_save)
    Button buttonShowSave;

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
        buttonShowSave.setOnClickListener(v -> startSaveActivity());
    }

    private void initRecyclerView() {
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        myAdapter = new MyAdapter(presenter.getRecyclerAdapter(), this);
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
            Toast.makeText(this,
                    "Первое приложение с использованием популярных библиотек",
                    Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.action) {
            startSaveActivity();
        }
        return super.onOptionsItemSelected(item);
    }

    private void startSaveActivity() {
        Intent intent = new Intent(this, SaveActivity.class);
        startActivity(intent);
    }

    @Override
    public void updateRecyclerView() {
        myAdapter.notifyDataSetChanged();
    }

    @Override
    public void showDetails(Photo photo, int position) {
        fragmentDetails = new FragmentDetails(photo);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container_main, fragmentDetails)
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
