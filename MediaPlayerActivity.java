package android.intel.sdp.MediaPlayer;

import android.net.Uri;

public class MediaPlayerActivity extends VideoPlayback {
    public Uri getVideoUri() {
        return FileChoosingActivity.uri;
    }
}
