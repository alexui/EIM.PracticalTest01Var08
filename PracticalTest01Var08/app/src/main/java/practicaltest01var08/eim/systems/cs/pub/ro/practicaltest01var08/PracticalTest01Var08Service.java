package practicaltest01var08.eim.systems.cs.pub.ro.practicaltest01var08;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by Alex on 4/1/2016.
 */
public class PracticalTest01Var08Service extends Service {

    private ProcessingThread processingThread;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String text;
        //TODO onStartCommand
        text = intent.getStringExtra(Constants.SERVICE_INTENT_TEXT_TAG);
        processingThread = new ProcessingThread(this.getApplicationContext(), text);
        processingThread.start();
        return START_REDELIVER_INTENT;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
