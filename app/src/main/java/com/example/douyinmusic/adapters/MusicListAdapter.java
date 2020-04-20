package com.example.douyinmusic.adapters;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.douyinmusic.R;
import com.example.douyinmusic.model.music_list.Playlist;
import com.example.douyinmusic.components.AudioWaveView;

public class MusicListAdapter extends RecyclerView.Adapter<MusicListAdapter.MusicItemViewHolder> {
    private Playlist data;
    /**
     * 选择音乐回调
     */
    private OnSelect selectCallBack = new OnSelect() {
        @Override
        public void select(int index) {
            Log.i(this.toString(), "Item with index " + index + " is selected");
        }
    };
    // 当前播放的音乐
    private int actionItem = -1;
    private MusicItemViewHolder itemViewHolder;

    public MusicListAdapter(Playlist musicData) {
        this.data = musicData;
    }

    static class MusicItemViewHolder extends RecyclerView.ViewHolder {
        public TextView nameView;
        public TextView singerText;
        public TextView durationText;
        public TextView rankText;
        public AudioWaveView audioWaveView;


        public MusicItemViewHolder(@NonNull View itemView) {
            super(itemView);
            nameView = (TextView) itemView.findViewById(R.id.music_name);
            singerText = (TextView) itemView.findViewById(R.id.text_singer);
            durationText = (TextView) itemView.findViewById(R.id.text_duration);
            rankText = (TextView) itemView.findViewById(R.id.text_rank);
            audioWaveView = (AudioWaveView) itemView.findViewById(R.id.tag_active);
        }
    }

    @NonNull
    @Override
    public MusicItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        MusicItemViewHolder holder = new MusicItemViewHolder(inflater.inflate(R.layout.item_music, parent, false));
        return holder;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    /*
     * 毫秒时间 转时间格式
     * */
    private String formatTime(long millisecond) {
        long m = millisecond / 1000 / 60;
        long s = millisecond / 1000 % 60;
        return String.format("%02d:%02d", m, s);
    }

    @Override
    public void onBindViewHolder(@NonNull final MusicItemViewHolder holder, int position) {
        // 绑定数据
        holder.itemView.setTag(position); // 标记每一个item
        holder.nameView.setText(data.getTracks().get(position).getName());
        holder.rankText.setText(position + 1 + "");
        holder.durationText.setText(formatTime(data.getTracks().get(position).getDt()));
        holder.singerText.setText(data.getTracks().get(position).getAr().get(0).getName());
        // 添加点击事件
        if (data.getTracks().get(position).getFee() != 4) { // fee为4是付费歌曲
            holder.itemView.setOnClickListener(new ClickItemListener());
        } else {
            holder.itemView.setOnClickListener(new ClickItemListener());
            holder.itemView.setBackgroundColor(0x11000000);
        }
        // 设置排名颜色
        if (position == 0) {
            holder.rankText.setTextColor(0xffef4238);
        } else if (position == 1 || position == 2) {
            holder.rankText.setTextColor(0xffffb400);
        } else {
            holder.rankText.setTextColor(Color.BLACK);
        }
        // 设置action
        if (position == actionItem) {
            holder.audioWaveView.setVisibility(View.VISIBLE);
        } else {
            holder.audioWaveView.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return data.getTracks().size();
    }

    // 点击事件回调
    class ClickItemListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            int position = (int) v.getTag();
            if (actionItem != position) {
                switchTo(position);
                selectCallBack.select(position);
            }
        }
    }

    public void setSlectMusicListener(OnSelect onSelect) {
        this.selectCallBack = onSelect;
    }

    public void switchTo(int index) {
        actionItem = index;
        notifyDataSetChanged();
    }

    // 控制active图标的播放和暂停
    public void play() {

    }

    public void pause() {

    }

    public interface OnSelect {
        void select(int index);
    }
}
