package com.xuyonghong.xyhrecorder.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xuyonghong.xyhrecorder.R;
import com.xuyonghong.xyhrecorder.adapter.SavedRecordAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the interface
 * to handle interaction events.
 * Use the {@link SavedRecrodFragment#} factory method to
 * create an instance of this fragment.
 */
public class SavedRecrodFragment extends Fragment {
    @BindView(R.id.saved_recording_list)
    RecyclerView savedRecordingList;

    public static final String TITLE = "已存录音";

    private SavedRecordAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_saved_recrod, container, false);
        ButterKnife.bind(this, view);
        adapter = new SavedRecordAdapter();
        savedRecordingList.setAdapter(adapter);
        savedRecordingList.setLayoutManager(new LinearLayoutManager(getContext()));
        savedRecordingList.setItemAnimator(new DefaultItemAnimator());

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }
}
