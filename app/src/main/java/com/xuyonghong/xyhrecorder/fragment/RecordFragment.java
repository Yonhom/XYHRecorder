package com.xuyonghong.xyhrecorder.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xuyonghong.xyhrecorder.R;
import com.xuyonghong.xyhrecorder.media.RecordManager;
import com.xuyonghong.xyhrecorder.util.CommonUtils;
import com.xuyonghong.xyhrecorder.view.TimerRing;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the interface
 * to handle interaction events.
 * Use the {@link RecordFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecordFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private static int recordingCount;

    /**
     * status of the permission of recording
     */
    private boolean recordPermissionGranted;

    private String[] permissions = {android.Manifest.permission.RECORD_AUDIO};

    /**
     * the current fragment's title
     */
    public static final String TITLE = "录音";

    public RecordFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RecordFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RecordFragment newInstance(String param1, String param2) {
        RecordFragment fragment = new RecordFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        // request recording permission
        ActivityCompat.requestPermissions(getActivity(), permissions, 200);
    }

    @BindView(R.id.timer_display)
    TimerRing timerRing;

    @BindView(R.id.text_indicator)
    TextView textIndicator;

    @BindView(R.id.record_button)
    FloatingActionButton recordButton;

    /**
     * 是否在录音
     */
    private boolean recording = false;

    public int getCurrentRecordCount() {
        SharedPreferences record_count
                = getActivity().getSharedPreferences("record_count", Context.MODE_PRIVATE);
        return record_count.getInt("current_count", 1);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_record, container, false);
        ButterKnife.bind(this, view);

        // set the current record_count form shared preference
        recordingCount = getCurrentRecordCount();

        recordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recording = !recording;
                if (recording) {
                    startTimer();
                    // start recording
                    RecordManager.getInstance().record("My_Recording_" + ++recordingCount);
                } else {
                    stopTimer();
                    RecordManager.getInstance().stopRecord();

                    //

                }
                // 更新UI
                updateUI();
            }
        });

        return view;
    }

    private void updateUI() {
        if (recording) {
            textIndicator.setText("正在录音..");
            recordButton.setImageResource(R.drawable.ic_media_stop);

        } else {
            textIndicator.setText("点击按钮开始录音");
            recordButton.setImageResource(R.drawable.ic_mic_white_36dp);
        }


    }

    private Disposable disposable;

    public void startTimer() {
        CommonUtils.getCounterObservable(TimeUnit.SECONDS)
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(@NonNull Long aLong) {
                        timerRing.setTotolSeconds(aLong.intValue());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void stopTimer() {
        if (!disposable.isDisposed())
            disposable.dispose();
        timerRing.setTotolSeconds(0);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        SharedPreferences record_count = getActivity().getSharedPreferences("record_count", Context.MODE_PRIVATE);
        record_count.edit().putInt("current_count", recordingCount).apply();
    }

    /**
     * when the requestPermission() is called, this method will be called
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @android.support.annotation.NonNull String[] permissions, @android.support.annotation.NonNull int[] grantResults) {
        switch (requestCode) {
            case 200:
                recordPermissionGranted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                break;
        }
        // if permission is not granted, finish the activity this fragment is in
        if (!recordPermissionGranted) getActivity().finish();
    }
}
