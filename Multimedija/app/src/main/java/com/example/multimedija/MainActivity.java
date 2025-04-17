package com.example.multimedija;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;


public class MainActivity extends AppCompatActivity {
    public static String sRecordedFileName;
    private MediaRecorder mediaRecorder;
    private MediaPlayer mediaPlayer;

    private Button btnRec;
    private Button btnPlay;
    private Button btnStop;

    private boolean isRecording = false;
    private boolean isPlaying = false;

    /*@Override
    protected void OnDestroy(){
        super.OnDestroy();
        releaseMediaRecorder();
        releaseMediaPlayer();
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        btnRec = (Button) findViewById(R.id.button_rec);
        btnPlay = (Button) findViewById(R.id.button_play);
        btnStop = (Button) findViewById(R.id.button_stop);

        btnRec.setEnabled(true);
        btnPlay.setEnabled(true);
        btnStop.setEnabled(true);

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.RECORD_AUDIO},0);
        }

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE},0);
        }

        sRecordedFileName = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/audio_file.3gpp";

        btnRec.setOnClickListener(recordClick);
        btnRec.setOnClickListener(playClick);
        btnRec.setOnClickListener(stopClick);

    }
    View.OnClickListener recordClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            try{
                mediaRecorder = new MediaRecorder();
                mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                mediaRecorder.setOutputFile(sRecordedFileName);
                mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
                mediaRecorder.setAudioEncodingBitRate(16);
                mediaRecorder.setAudioSamplingRate(44100);
                mediaRecorder.prepare();
                mediaRecorder.start();

                isRecording = true;
                btnRec.setEnabled(false); btnPlay.setEnabled(true); btnStop.setEnabled(true);

                releaseMediaRecorder();
            }
            catch (IllegalStateException | IOException e) {
                e.printStackTrace();
                Toast.makeText(MainActivity.this,"Sorry!" + e.getMessage(),Toast.LENGTH_SHORT).show();
                return;
            }
        }
    };
    View.OnClickListener playClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            try{
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setDataSource(sRecordedFileName);
                mediaPlayer.prepare();
                mediaPlayer.start();

                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        btnRec.setEnabled(false); btnPlay.setEnabled(false); btnStop.setEnabled(false);
                    }
                });

                isPlaying = true;
                btnRec.setEnabled(false); btnPlay.setEnabled(false); btnStop.setEnabled(true);
                releaseMediaPlayer();
            }
            catch (IllegalStateException | IOException e) {
                e.printStackTrace();
                Toast.makeText(MainActivity.this,"Sorry!" + e.getMessage(),Toast.LENGTH_SHORT).show();
                return;
            }
        }
    };
    View.OnClickListener stopClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            try{
                releaseMediaRecorder();
                releaseMediaPlayer();
                btnRec.setEnabled(true); btnPlay.setEnabled(true); btnStop.setEnabled(false);
            }
            catch (IllegalStateException e)
            {
                Log.e("stopClick","Error",e);
            }
        }
    };

    private void releaseMediaRecorder(){
        if(isRecording == true)
        {
            mediaRecorder.stop();
            mediaRecorder.reset();
            mediaRecorder.release();
            mediaRecorder=null;
            isRecording = false;
        }
    }
    private void releaseMediaPlayer(){
        if(isPlaying == true)
        {
            mediaRecorder.stop();
            mediaRecorder.reset();
            mediaRecorder.release();
            mediaRecorder=null;
            isPlaying = false;
        }
    }
}