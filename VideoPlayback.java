package android.intel.sdp.MediaPlayer;

import android.app.Activity;
import android.app.ActivityManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;
import java.text.DecimalFormat;

public class VideoPlayback extends Activity implements OnPreparedListener {
    private static final String TAG = "VideoPlayback";
    private long mClipStartTime;
    private Handler mHandler = new Handler();
    private String mHeader;
    private SurfaceHolder mHolder;
    private String mIndicatorText;
    private TextView mIndicator_BottomLeft;
    private TextView mIndicator_BottomRight;
    private TextView mIndicator_TopLeft;
    private TextView mIndicator_TopRight;
    private MediaPlayer mMediaPlayer;
    private ImageButton mPause;
    private ImageButton mPlay;
    private SurfaceView mPreview;
    private long mStartTime;
    private ImageButton mStop;
    private Runnable mUpdateTimeTask = new C00031();
    private SDPUtility mUtility;
    private boolean videoStopped = true;
    protected Uri videoUri;

    /* renamed from: android.intel.sdp.MediaPlayer.VideoPlayback$1 */
    class C00031 implements Runnable {
        C00031() {
        }

        public void run() {
            if (!VideoPlayback.this.videoStopped) {
                long elapsedtime = SystemClock.elapsedRealtime() - VideoPlayback.this.mClipStartTime;
                long playerElapsedTime = (long) VideoPlayback.this.mMediaPlayer.getCurrentPosition();
                DecimalFormat df = new DecimalFormat("#.##");
                String cpuUsage = df.format((double) (VideoPlayback.this.mUtility.readUsage() * 100.0f)) + "%";
                String memUsage = new StringBuilder(String.valueOf(Long.toString(VideoPlayback.this.mUtility.readMem((ActivityManager) VideoPlayback.this.getSystemService("activity"))))).append("M").toString();
                VideoPlayback.this.mIndicatorText = new StringBuilder(String.valueOf(VideoPlayback.this.mHeader)).append("CPU usage: ").append(cpuUsage).append("\nMemory usage: ").append(memUsage).append("\nFPS: ").append(df.format((double) ((23.0f * ((float) playerElapsedTime)) / ((float) elapsedtime)))).toString();
                VideoPlayback.this.updateIndicator();
            }
            VideoPlayback.this.mHandler.postAtTime(this, SystemClock.uptimeMillis() + 2000);
        }
    }

    /* renamed from: android.intel.sdp.MediaPlayer.VideoPlayback$2 */
    class C00042 implements OnClickListener {
        C00042() {
        }

        public void onClick(View view) {
            if (VideoPlayback.this.mMediaPlayer == null) {
                VideoPlayback.this.prepareVideo();
            }
            if (!VideoPlayback.this.mMediaPlayer.isPlaying()) {
                VideoPlayback.this.mMediaPlayer.start();
                VideoPlayback.this.videoStopped = false;
            }
        }
    }

    /* renamed from: android.intel.sdp.MediaPlayer.VideoPlayback$3 */
    class C00053 implements OnClickListener {
        C00053() {
        }

        public void onClick(View view) {
            if (VideoPlayback.this.mMediaPlayer != null) {
                VideoPlayback.this.mMediaPlayer.pause();
            }
        }
    }

    /* renamed from: android.intel.sdp.MediaPlayer.VideoPlayback$4 */
    class C00064 implements OnClickListener {
        C00064() {
        }

        public void onClick(View view) {
            if (VideoPlayback.this.mMediaPlayer != null) {
                VideoPlayback.this.stopVideo();
            }
        }
    }

    protected Uri getVideoUri() {
        return null;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0002R.layout.media);
        this.mPreview = (SurfaceView) findViewById(C0002R.id.surface);
        this.mIndicator_TopLeft = (TextView) findViewById(C0002R.id.textbox_topleft);
        this.mIndicator_TopRight = (TextView) findViewById(C0002R.id.textbox_topright);
        this.mIndicator_BottomLeft = (TextView) findViewById(C0002R.id.textbox_bottomleft);
        this.mIndicator_BottomRight = (TextView) findViewById(C0002R.id.textbox_bottomright);
        this.mHolder = this.mPreview.getHolder();
        this.mHolder.setType(3);
        this.mIndicator_TopLeft.setVisibility(4);
        this.mIndicator_BottomLeft.setVisibility(4);
        this.mIndicator_BottomRight.setVisibility(4);
        this.mUtility = new SDPUtility();
        if (this.mStartTime == 0) {
            this.mStartTime = SystemClock.uptimeMillis();
            this.mHandler.removeCallbacks(this.mUpdateTimeTask);
            this.mHandler.postDelayed(this.mUpdateTimeTask, 100);
        }
        this.mHeader = "Performance Indicators:\n";
        this.mClipStartTime = SystemClock.elapsedRealtime() + 100;
        this.mPreview = (SurfaceView) findViewById(C0002R.id.surface);
        this.mPlay = (ImageButton) findViewById(C0002R.id.play);
        this.mStop = (ImageButton) findViewById(C0002R.id.stop);
        this.mPause = (ImageButton) findViewById(C0002R.id.pause);
        this.mPlay.setOnClickListener(new C00042());
        this.mPause.setOnClickListener(new C00053());
        this.mStop.setOnClickListener(new C00064());
    }

    private void stopVideo() {
        this.videoStopped = true;
        if (this.mMediaPlayer != null) {
            this.mMediaPlayer.stop();
            this.mMediaPlayer.release();
            this.mMediaPlayer = null;
        }
    }

    private void prepareVideo() {
        try {
            this.videoUri = getVideoUri();
            this.mMediaPlayer = new MediaPlayer();
            this.mMediaPlayer.setDataSource(this, this.videoUri);
            this.mMediaPlayer.setDisplay(this.mHolder);
            this.mMediaPlayer.prepare();
            this.mMediaPlayer.setOnPreparedListener(this);
            this.mMediaPlayer.setAudioStreamType(3);
        } catch (Exception e) {
            Log.e(TAG, "error: " + e.getMessage(), e);
            finish();
        }
        this.videoStopped = false;
    }

    public void onPrepared(MediaPlayer arg0) {
        this.mMediaPlayer.start();
        this.videoStopped = false;
    }

    private void updateIndicator() {
        this.mIndicator_TopRight.setText(this.mIndicatorText);
    }
}
