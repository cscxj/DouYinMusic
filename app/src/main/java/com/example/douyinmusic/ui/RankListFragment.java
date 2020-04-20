package com.example.douyinmusic.ui;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.douyinmusic.R;
import com.example.douyinmusic.adapters.RankListAdapter;
import com.example.douyinmusic.model.rank_list.JSONRank;
import com.example.douyinmusic.model.rank_list.RList;

import java.util.List;

public class RankListFragment extends Fragment {
    private RankListViewModel viewModel;
    private RankListAdapter adapter;
    private RecyclerView recyclerView;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0x1111) {
                JSONRank data = (JSONRank) msg.obj;
                adapter = new RankListAdapter(data.getList());
                recyclerView.setAdapter(adapter);
            }
        }
    };

    public static RankListFragment newInstance() {
        return new RankListFragment();
    }

    @Override
    public void onStart() {
        super.onStart();
        viewModel =new ViewModelProvider(getActivity()).get(RankListViewModel.class);
        viewModel.init();
        viewModel.getRankData().observe(this, new Observer<JSONRank>() {
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
        // TODO: Use the ViewModel
    }

    class SelectRankListener implements RankListAdapter.ClickItemListener{
        @Override
        public void click(int index) {
            RankListFragment.this.viewModel.setCurrentRank(index);
        }
    }

}
