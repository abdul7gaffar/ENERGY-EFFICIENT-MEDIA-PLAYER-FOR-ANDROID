package android.intel.sdp.MediaPlayer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class HomePageActivity extends Activity {
    private Button continueBtn;
    private Intent intent;
    private long ms = 0;
    private boolean paused = false;
    private boolean splashActive = true;
    private long splashTime = 2000;

    /* renamed from: android.intel.sdp.MediaPlayer.HomePageActivity$1 */
    class C00001 extends Thread {
        C00001() {
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            /*
            r6 = this;
            r4 = 100;
        L_0x0002:
            r0 = android.intel.sdp.MediaPlayer.HomePageActivity.this;	 Catch:{ Exception -> 0x004c, all -> 0x0067 }
            r0 = r0.splashActive;	 Catch:{ Exception -> 0x004c, all -> 0x0067 }
            if (r0 == 0) goto L_0x001a;
        L_0x000a:
            r0 = android.intel.sdp.MediaPlayer.HomePageActivity.this;	 Catch:{ Exception -> 0x004c, all -> 0x0067 }
            r0 = r0.ms;	 Catch:{ Exception -> 0x004c, all -> 0x0067 }
            r2 = android.intel.sdp.MediaPlayer.HomePageActivity.this;	 Catch:{ Exception -> 0x004c, all -> 0x0067 }
            r2 = r2.splashTime;	 Catch:{ Exception -> 0x004c, all -> 0x0067 }
            r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
            if (r0 < 0) goto L_0x0034;
        L_0x001a:
            r0 = android.intel.sdp.MediaPlayer.HomePageActivity.this;
            r1 = new android.content.Intent;
            r2 = android.intel.sdp.MediaPlayer.HomePageActivity.this;
            r3 = android.intel.sdp.MediaPlayer.FileChoosingActivity.class;
            r1.<init>(r2, r3);
            r0.intent = r1;
            r0 = android.intel.sdp.MediaPlayer.HomePageActivity.this;
            r1 = android.intel.sdp.MediaPlayer.HomePageActivity.this;
            r1 = r1.intent;
            r0.startActivity(r1);
        L_0x0033:
            return;
        L_0x0034:
            r0 = android.intel.sdp.MediaPlayer.HomePageActivity.this;	 Catch:{ Exception -> 0x004c, all -> 0x0067 }
            r0 = r0.paused;	 Catch:{ Exception -> 0x004c, all -> 0x0067 }
            if (r0 != 0) goto L_0x0046;
        L_0x003c:
            r0 = android.intel.sdp.MediaPlayer.HomePageActivity.this;	 Catch:{ Exception -> 0x004c, all -> 0x0067 }
            r1 = r0.ms;	 Catch:{ Exception -> 0x004c, all -> 0x0067 }
            r1 = r1 + r4;
            r0.ms = r1;	 Catch:{ Exception -> 0x004c, all -> 0x0067 }
        L_0x0046:
            r0 = 100;
            android.intel.sdp.MediaPlayer.HomePageActivity.C00001.sleep(r0);	 Catch:{ Exception -> 0x004c, all -> 0x0067 }
            goto L_0x0002;
        L_0x004c:
            r0 = move-exception;
            r0 = android.intel.sdp.MediaPlayer.HomePageActivity.this;
            r1 = new android.content.Intent;
            r2 = android.intel.sdp.MediaPlayer.HomePageActivity.this;
            r3 = android.intel.sdp.MediaPlayer.FileChoosingActivity.class;
            r1.<init>(r2, r3);
            r0.intent = r1;
            r0 = android.intel.sdp.MediaPlayer.HomePageActivity.this;
            r1 = android.intel.sdp.MediaPlayer.HomePageActivity.this;
            r1 = r1.intent;
            r0.startActivity(r1);
            goto L_0x0033;
        L_0x0067:
            r0 = move-exception;
            r1 = android.intel.sdp.MediaPlayer.HomePageActivity.this;
            r2 = new android.content.Intent;
            r3 = android.intel.sdp.MediaPlayer.HomePageActivity.this;
            r4 = android.intel.sdp.MediaPlayer.FileChoosingActivity.class;
            r2.<init>(r3, r4);
            r1.intent = r2;
            r1 = android.intel.sdp.MediaPlayer.HomePageActivity.this;
            r2 = android.intel.sdp.MediaPlayer.HomePageActivity.this;
            r2 = r2.intent;
            r1.startActivity(r2);
            throw r0;
            */
            throw new UnsupportedOperationException("Method not decompiled: android.intel.sdp.MediaPlayer.HomePageActivity.1.run():void");
        }
    }

    /* renamed from: android.intel.sdp.MediaPlayer.HomePageActivity$2 */
    class C00012 implements OnClickListener {
        C00012() {
        }

        public void onClick(View v) {
            HomePageActivity.this.intent = new Intent(HomePageActivity.this, FileChoosingActivity.class);
            HomePageActivity.this.startActivity(HomePageActivity.this.intent);
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0002R.layout.activity_home_page);
        this.continueBtn = (Button) findViewById(C0002R.id.continueBtn);
        new C00001().start();
        this.continueBtn.setOnClickListener(new C00012());
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(C0002R.menu.home_page, menu);
        return true;
    }
}
