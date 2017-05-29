package com.xuyonghong.xyhrecorder.media;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * manager for recording and replaying audio
 * Created by xuyonghong on 2017/5/28.
 */

public class RecordManager {

    private List<String> recordFiles = new ArrayList<>();

    private static final String TAG = RecordManager.class.getSimpleName();
    private static RecordManager recordManager;

    /**
     * 录音
     */
    private MediaRecorder mediaRecorder;

    /**
     * 播放器
     */
    private MediaPlayer mediaPlayer;

    /**
     * root path
     */
    public String mediaRootPath
            = Environment.getExternalStorageDirectory().getAbsolutePath();

    /**
     * current file path
     */
    private String currentFilePath;

    private RecordManager() {
    }

    public List<String> getRecordFiles() {
        return recordFiles;
    }

    public static RecordManager getInstance() {
        if (recordManager == null) {
            recordManager = new RecordManager();
        }
        return recordManager;
    }

    public void record(String fileName) {
        // set up recorder
        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        currentFilePath = mediaRootPath + "/" + fileName + ".3gp";
        mediaRecorder.setOutputFile(currentFilePath);
        try {
            mediaRecorder.prepare();
        } catch (IOException e) {
            Log.e(TAG, "recording " + fileName + " filed!");
        }
        mediaRecorder.start();

    }

    public void stopRecord() {
        if (currentFilePath == null) return;

        mediaRecorder.stop();
        mediaRecorder.release();
        recordFiles.add(currentFilePath);
        mediaRecorder = null;
    }

    public void play(int pathIndex) {
        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(recordFiles.get(pathIndex));
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            Log.e(TAG, "playing record filed!");
        }
    }

    public void stopPlay() {
        mediaPlayer.release();
        mediaPlayer = null;
    }



}
