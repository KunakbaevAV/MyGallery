package ru.art.mygallery.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import ru.art.mygallery.R;
import ru.art.mygallery.presenter.IRecyclerDetails;
import ru.art.mygallery.presenter.IRecyclerThreePresenter;
import ru.art.mygallery.presenter.ThreePresenter;

class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private IRecyclerThreePresenter presenterThree;
    private IRecyclerDetails presenterDetails;
    private IViewDetails viewDetails;

    MyAdapter(ThreePresenter threePresenter, IViewDetails viewDetails) {
        this.presenterThree = threePresenter.getRecyclerMainPresenter();
        this.presenterDetails = threePresenter.getDetailPresenter();
        this.viewDetails = viewDetails;
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
        presenterThree.bindView(holder);
        holder.cardImage.setOnClickListener(view -> presenterDetails.showDetails(viewDetails, holder));
    }

    @Override
    public int getItemCount() {
        return presenterThree.getItemCount();
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
        public int getImageID() {
            return imageRes;
        }

        @Override
        public int getPos() {
            return position;
        }

    }
}
