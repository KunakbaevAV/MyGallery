package ru.art.mygallery.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import ru.art.mygallery.R;
import ru.art.mygallery.presenter.IRecyclerThreePresenter;

class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private IRecyclerThreePresenter iRecyclerMainPresenter;

    MyAdapter(IRecyclerThreePresenter iRecyclerMainPresenter) {
        this.iRecyclerMainPresenter = iRecyclerMainPresenter;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_three, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.position = position;
        iRecyclerMainPresenter.bindView(holder);
    }

    @Override
    public int getItemCount() {
        return iRecyclerMainPresenter.getItemCount();
    }


    class MyViewHolder extends RecyclerView.ViewHolder implements IViewHolder {

        private TextView textView;
        private int position = 0;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text_view);
        }

        @Override
        public void setText(String text) {
            textView.setText(text);
        }

        @Override
        public int getPos() {
            return position;
        }
    }
}
