package android.intel.sdp.MediaPlayer;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

public class FileChoosingActivity extends Activity {
    static Uri uri;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getVideo();
    }

    private void getVideo() {
        Intent pickMedia = new Intent("android.intent.action.GET_CONTENT");
        pickMedia.setType("video/*");
        startActivityForResult(pickMedia, 12345);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 12345 && resultCode == -1) {
            Uri selectedVideoLocation = data.getData();
            Toast.makeText(this, selectedVideoLocation.toString(), 1).show();
            uri = selectedVideoLocation;
            startActivity(new Intent(this, MediaPlayerActivity.class));
        }
    }
}
