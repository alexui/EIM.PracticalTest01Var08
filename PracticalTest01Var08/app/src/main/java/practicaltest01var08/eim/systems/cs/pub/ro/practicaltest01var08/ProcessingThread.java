package practicaltest01var08.eim.systems.cs.pub.ro.practicaltest01var08;

import android.content.Context;
import android.content.Intent;

import java.sql.Timestamp;
import java.util.Random;

/**
 * Created by Alex on 4/1/2016.
 */
public class ProcessingThread extends Thread {

    private Context context;

    private String text;
    private boolean activeThread;

    private Random random;

    private String actionsArray[];

    public ProcessingThread(Context context, String text) {
        this.context = context;
        this.text = text;
        this.activeThread = true;
        actionsArray = text.split(" ");
    }


    @Override
    public void run() {
        while (activeThread) {

            //TODO choose action and send message
            for (int i = 0; i < actionsArray.length; i++)
                sendMessage(actionsArray[i]);
            sleep();
        }
    }

    public void stopThread() {
        this.activeThread = false;
    }

    private void sleep() {
        try {
            Thread.sleep(Constants.SLEEP_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void sendMessage(String action) {
        //TODO sendBroadcast Implementation
        Intent intent = new Intent();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String data = timestamp + "--" + action;

        intent.setAction(action);
        intent.putExtra(Constants.DATA, data);

        context.sendBroadcast(intent);
    }
}
