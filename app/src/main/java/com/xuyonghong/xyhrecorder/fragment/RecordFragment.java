package com.xuyonghong.xyhrecorder.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xuyonghong.xyhrecorder.R;
import com.xuyonghong.xyhrecorder.listener.FragmentInteractionListener;
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

    /**
     * this listener is often a activity, representing a bridge
     * between the current fragment and the activity this fragment is in
     */
    private FragmentInteractionListener mListener;
    public FragmentInteractionListener getmListener() {
        return mListener;
    }
    public void setmListener(FragmentInteractionListener mListener) {
        this.mListener = mListener;
    }


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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_record, container, false);
        ButterKnife.bind(this, view);

        recordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recording = !recording;
                if (recording) {
                    startTimer();
                } else {
                    stopTimer();
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

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(this, uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentInteractionListener) {
            mListener = (FragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

}
