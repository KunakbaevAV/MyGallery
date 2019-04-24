package ru.art.mygallery.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import ru.art.mygallery.R;
import ru.art.mygallery.presenter.ThreePresenter;

import static ru.art.mygallery.AppConstants.MY_COUNTER;
import static ru.art.mygallery.AppConstants.MY_PREFERENCES;

public class MainActivity extends AppCompatActivity implements IViewDetails {


    private ThreePresenter presenter;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new ThreePresenter();
        preferences = getSharedPreferences(MY_PREFERENCES, Context.MODE_PRIVATE);
        initRecyclerView();
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.my_recycler);
        GridLayoutManager layoutManager1 = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager1);
        MyAdapter myAdapter = new MyAdapter(presenter.getRecyclerMainPresenter(), preferences, this);
        recyclerView.setAdapter(myAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.settings) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt(MY_COUNTER, 0);
            editor.apply();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showDetails(int imageID) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new FragmentDetails(imageID), "Details")
                .addToBackStack("BackStack")
                .commit();
    }
}
