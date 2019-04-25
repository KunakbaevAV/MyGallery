package ru.art.mygallery.view;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import ru.art.mygallery.R;
import ru.art.mygallery.presenter.ThreePresenter;

public class MainActivity extends AppCompatActivity implements IViewDetails {

    private ThreePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new ThreePresenter();
        initRecyclerView();
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.my_recycler);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        MyAdapter myAdapter = new MyAdapter(presenter, this);
        recyclerView.setAdapter(myAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.logs) {
            showMessage(presenter.showLogs().toString());
        }
        return super.onOptionsItemSelected(item);
    }

    private void showMessage(String text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showDetails(int imageID) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new FragmentDetails(imageID), "Details")
                .addToBackStack("BackStack")
                .commit();
    }
}
