package com.example.douyinmusic.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.douyinmusic.R;
import com.example.douyinmusic.model.rank_list.RList;

import java.util.ArrayList;
import java.util.List;

public class RankListAdapter extends RecyclerView.Adapter<RankListAdapter.RankListViewHolder> {
    List<RList> data;
    ClickItemListener clickItemListener;

    public RankListAdapter() {
        data = new ArrayList<>();
    }

    public RankListAdapter(List<RList> data) {
        this.data = data;
    }

    public void setClickItemListener(ClickItemListener clickItemListener) {
        this.clickItemListener = clickItemListener;
    }

    public class RankListViewHolder extends RecyclerView.ViewHolder {
        public ImageView picture;
        public TextView nameText;

        public RankListViewHolder(@NonNull View itemView) {
            super(itemView);
            picture = (ImageView) itemView.findViewById(R.id.item_rank_img);
            nameText = (TextView) itemView.findViewById(R.id.item_rank_name);
        }
    }

    @NonNull
    @Override
    public RankListAdapter.RankListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        RankListViewHolder holder = new RankListViewHolder(inflater.inflate(R.layout.item_rank, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RankListAdapter.RankListViewHolder holder, final int position) {
        RequestOptions options = new RequestOptions()
                .error(R.drawable.ic_launcher_foreground)
                .bitmapTransform(new RoundedCorners(30));
        Glide.with(holder.itemView)
                .load(data.get(position).getCoverimgurl())
                .apply(options)
                .into(holder.picture);
        holder.nameText.setText(data.get(position).getName());
        if (clickItemListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickItemListener.click(position);
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public interface ClickItemListener {
        void click(int index);
    }
}
