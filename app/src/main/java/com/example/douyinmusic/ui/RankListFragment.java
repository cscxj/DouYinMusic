package com.example.douyinmusic.ui;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.douyinmusic.MainActivityViewModel;
import com.example.douyinmusic.R;
import com.example.douyinmusic.adapters.RankListAdapter;
import com.example.douyinmusic.model.rank_list.JSONRank;

public class RankListFragment extends Fragment {
    private MainActivityViewModel model;
    private RankListAdapter adapter;
    private RecyclerView recyclerView;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0x1111) {
                model.getRankData().setValue((JSONRank)msg.obj);
            }
        }
    };

    public static RankListFragment newInstance() {
        return new RankListFragment();
    }

    @Override
    public void onStart() {
        super.onStart();
        model =new ViewModelProvider(getActivity()).get(MainActivityViewModel.class);
        model.getRankData().observe(this, new Observer<JSONRank>() {
            @Override
            public void onChanged(JSONRank jsonRank) {
                // 数据更新了
                adapter = new RankListAdapter(jsonRank.getList());
                recyclerView.setAdapter(adapter);
                adapter.setClickItemListener(new SelectRankListener());
            }
        });
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ViewGroup holder = (ViewGroup) inflater.inflate(R.layout.rank_list_fragment, container, false);

        recyclerView = (RecyclerView) holder.findViewById(R.id.rank_list_recyclerview);
        adapter = new RankListAdapter();
        GridLayoutManager layoutManager = new GridLayoutManager(holder.getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        adapter.setClickItemListener(new SelectRankListener());

        return holder;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    class SelectRankListener implements RankListAdapter.ClickItemListener{
        @Override
        public void click(int index) {
            RankListFragment.this.model.setCurrentRank(index);
            model.updateMusicListData();
        }
    }

}
