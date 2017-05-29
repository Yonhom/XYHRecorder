package com.xuyonghong.xyhrecorder.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xuyonghong.xyhrecorder.R;
import com.xuyonghong.xyhrecorder.media.RecordManager;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xuyonghong on 2017/5/28.
 */

public class SavedRecordAdapter extends RecyclerView.Adapter<SavedRecordAdapter.SavedRecordViewHolder> {

    @Override
    public SavedRecordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.record_item, null);

        return new SavedRecordViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SavedRecordViewHolder holder, int position) {
        String filePath = RecordManager.getInstance().getRecordFiles().get(position);
        int startIndex = filePath.lastIndexOf("/");
        String fileName = filePath.substring(startIndex);
        holder.recording_file_name_view.setText(fileName);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    static class SavedRecordViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.recording_file_name)
        TextView recording_file_name_view;

        public SavedRecordViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
