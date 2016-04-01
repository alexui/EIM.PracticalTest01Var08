package practicaltest01var08.eim.systems.cs.pub.ro.practicaltest01var08;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Alex on 4/1/2016.
 */
public class StartedServiceBroadcastReceiver extends BroadcastReceiver {

    public StartedServiceBroadcastReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        String action = intent.getAction();
        String data = intent.getStringExtra(Constants.DATA);

        Log.d(Constants.LOGCAT_FILTER_TAG, "Action: " + action + ", data :" + data);

    }

}