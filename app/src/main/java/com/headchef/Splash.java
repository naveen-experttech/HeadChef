package com.headchef;

import android.content.Intent;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.VideoView;


/**
 * Created by Leanberg on 03-Nov-17.
 */

public class Splash extends AppCompatActivity {


    private VideoView mVideoView;
    private Uri mVideoPath;


    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.spalsh);


        mVideoView = (VideoView) findViewById(R.id.videoView);
        mVideoView.setOnPreparedListener(OnVideoViewPrepared);
        mVideoView.setOnErrorListener(OnVideoViewError);
        mVideoView.setOnCompletionListener(OnVideoViewCompleted);


    }



    private MediaPlayer.OnErrorListener OnVideoViewError = new MediaPlayer.OnErrorListener() {
        @Override
        public boolean onError(MediaPlayer mediaPlayer, int what, int extra) {

            if (what == MediaPlayer.MEDIA_ERROR_UNKNOWN)
                Log.d("Debug", "MEDIA_ERROR_UNKNOWN");
            else if (what == MediaPlayer.MEDIA_ERROR_SERVER_DIED)
                Log.d("Debug", "MEDIA_ERROR_SERVER_DIED");

            if (extra == MediaPlayer.MEDIA_ERROR_IO)
                Log.d("Debug", "MEDIA_ERROR_IO");
            else if (extra == MediaPlayer.MEDIA_ERROR_MALFORMED)
                Log.d("Debug", "MEDIA_ERROR_MALFORMED");
            else if (extra == MediaPlayer.MEDIA_ERROR_NOT_VALID_FOR_PROGRESSIVE_PLAYBACK)
                Log.d("Debug", "MEDIA_ERROR_NOT_VALID_FOR_PROGRESSIVE_PLAYBACK");
            else if (extra == MediaPlayer.MEDIA_ERROR_TIMED_OUT)
                Log.d("Debug", "MEDIA_ERROR_TIMED_OUT");
            else if (extra == MediaPlayer.MEDIA_ERROR_UNSUPPORTED)
                Log.d("Debug", "MEDIA_ERROR_UNSUPPORTED");

            return false;
        }
    };

    private MediaPlayer.OnPreparedListener OnVideoViewPrepared = new MediaPlayer.OnPreparedListener() {
        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public void onPrepared(MediaPlayer mediaPlayer) {
            // mediaPlayer.setLooping(true);
            mediaPlayer.setVideoScalingMode(MediaPlayer.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING);
        }
    };


    private MediaPlayer.OnCompletionListener OnVideoViewCompleted = new MediaPlayer.OnCompletionListener() {
        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }


    };



    @Override
    public void onStart() {
        super.onStart();
    }



    @Override
    public void onResume() {
        super.onResume();

        String path = "android.resource://" + getPackageName() + "/" + R.raw.fruits;
        mVideoView.setVideoURI(Uri.parse(path));
        mVideoView.start();
    }

    /**
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /**
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
