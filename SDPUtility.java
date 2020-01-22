package android.intel.sdp.MediaPlayer;

import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.app.ProgressDialog;
import android.os.Handler;
import java.io.IOException;
import java.io.RandomAccessFile;

public class SDPUtility {
    static Handler myHandler;
    ProgressDialog myProgress;

    public float readUsage() {
        try {
            RandomAccessFile reader = new RandomAccessFile("/proc/stat", "r");
            String[] toks = reader.readLine().split(" ");
            long idle1 = Long.parseLong(toks[5]);
            long cpu1 = ((((Long.parseLong(toks[2]) + Long.parseLong(toks[3])) + Long.parseLong(toks[4])) + Long.parseLong(toks[6])) + Long.parseLong(toks[7])) + Long.parseLong(toks[8]);
            try {
                Thread.sleep(360);
            } catch (Exception e) {
            }
            reader.seek(0);
            String load = reader.readLine();
            reader.close();
            toks = load.split(" ");
            long cpu2 = ((((Long.parseLong(toks[2]) + Long.parseLong(toks[3])) + Long.parseLong(toks[4])) + Long.parseLong(toks[6])) + Long.parseLong(toks[7])) + Long.parseLong(toks[8]);
            return ((float) (cpu2 - cpu1)) / ((float) ((cpu2 + Long.parseLong(toks[5])) - (cpu1 + idle1)));
        } catch (IOException ex) {
            ex.printStackTrace();
            return 0.0f;
        }
    }

    public long readMem(ActivityManager am) {
        MemoryInfo mi = new MemoryInfo();
        am.getMemoryInfo(mi);
        return mi.availMem / 1048576;
    }
}
