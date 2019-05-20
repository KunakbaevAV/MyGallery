package ru.art.mygallery.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.art.mygallery.R;
import ru.art.mygallery.model.GlideLoader;
import ru.art.mygallery.model.entity.Hit;
import ru.art.mygallery.presenter.IRecyclerAdapter;

import static androidx.recyclerview.widget.RecyclerView.OnClickListener;
import static androidx.recyclerview.widget.RecyclerView.ViewHolder;

class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private IRecyclerAdapter iRecyclerAdapter;
    private GlideLoader glideLoader;

    MyAdapter(IRecyclerAdapter recyclerAdapter, Context context) {
        this.iRecyclerAdapter = recyclerAdapter;
        glideLoader = new GlideLoader(context);
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
        iRecyclerAdapter.bindView(holder);
    }

    @Override
    public int getItemCount() {
        return iRecyclerAdapter.getItemCount();
    }

    void delete(int position) {
        iRecyclerAdapter.delete(position);
        notifyDataSetChanged();
    }

    class MyViewHolder extends ViewHolder implements IViewHolder {

        @BindView(R.id.card_image)
        ImageView imageView;

        private Hit hit;

        private int position = 0;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public Hit getHit() {
            return hit;
        }

        @Override
        public void setHit(Hit hit) {
            this.hit = hit;
            glideLoader.loadImage(hit.webformatURL, imageView);
        }

        @Override
        public int getPos() {
            return position;
        }

        @Override
        public void setOnClickListener(OnClickListener listener) {
            imageView.setOnClickListener(listener);
        }
    }
}
