package ru.art.mygallery.view;

import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import ru.art.mygallery.R;
import ru.art.mygallery.presenter.IRecyclerThreePresenter;

import static ru.art.mygallery.AppConstants.MY_COUNTER;

class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private IRecyclerThreePresenter presenter;
    private IViewDetails fragmentDetails;
    private int counter;

    private SharedPreferences preferences;

    MyAdapter(IRecyclerThreePresenter presenter, SharedPreferences preferences, IViewDetails fragmentDetails) {
        this.presenter = presenter;
        this.preferences = preferences;
        this.fragmentDetails = fragmentDetails;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.position = position;
        presenter.bindView(holder);
        int image = holder.getImageRes();
        holder.cardImage.setOnClickListener(view -> fragmentDetails.showDetails(image));
    }

    private void showCouner(View view) {
        loadCounter();
        Toast.makeText(view.getContext(), "Счетчик = " + counter, Toast.LENGTH_SHORT).show();
        saveCounter();
    }

    private void saveCounter() {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(MY_COUNTER, counter);
        editor.apply();
    }

    private void loadCounter() {
        counter = preferences.getInt(MY_COUNTER, 0);
        counter++;
    }

    @Override
    public int getItemCount() {
        return presenter.getItemCount();
    }


    class MyViewHolder extends RecyclerView.ViewHolder implements IViewHolder {

        private ImageView cardImage;
        private int position = 0;
        private int imageRes;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cardImage = itemView.findViewById(R.id.card_image);
        }

        @Override
        public void setText(String text) {
        }

        @Override
        public void setImage(int image) {
            cardImage.setImageResource(image);
            imageRes = image;
        }

        @Override
        public int getPos() {
            return position;
        }

        int getImageRes() {
            return imageRes;
        }
    }
}
