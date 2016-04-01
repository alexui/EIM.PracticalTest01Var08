package practicaltest01var08.eim.systems.cs.pub.ro.practicaltest01var08;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PracticalTest01Var08MainActivity extends Activity {

    private int noOfTries;
    private int noOfSuccess;
    private int noOfFailures;

    //TODO definire obiecte interfata
    private Button navigate;
    private Button topLeft;
    private Button topRight;
    private Button center;
    private Button bottomLeft;
    private Button bottomRight;
    private TextView textView;

    private boolean isServiceStarted;

    private StartedServiceBroadcastReceiver startedServiceBroadcastReceiver;
    private IntentFilter startedServiceIntentFilter;

    private class ButtonClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            int val;
            String value;
            Button button = (Button) v;
            switch (v.getId()) {
                case R.id.button_navigate:
                    Intent intent = new Intent(PracticalTest01Var08MainActivity.this,
                            PracticalTest01Var08SecondaryActivity.class);
                    intent.putExtra(Constants.INTENT_TEXT_TAG, textView.getText().toString());
                    startActivityForResult(intent, Constants.SECONDARY_ACTIVITY_REQ_CODE);
                    break;
                default:
                    value = textView.getText().toString();
                    value += button.getText().toString() + " ";
                    textView.setText(value);
                    checkThreshold();
                    break;
            }
        }
    }

    private void checkThreshold() {
        String textViewValue = textView.getText().toString();
        int noElems = (textViewValue.split(" ")).length;
        if (noElems > Constants.THRESHOLD && !isServiceStarted) {
            Intent intent = new Intent(this, PracticalTest01Var08Service.class);
            intent.putExtra(Constants.SERVICE_INTENT_TEXT_TAG, textViewValue);
            startService(intent);
            isServiceStarted = true;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var08_main);

        navigate = (Button) findViewById(R.id.button_navigate);
        topLeft = (Button) findViewById(R.id.button_top_left);
        topRight = (Button) findViewById(R.id.button_top_right);
        bottomLeft = (Button) findViewById(R.id.button_bottom_left);
        bottomRight = (Button) findViewById(R.id.button_bottom_right);
        center = (Button) findViewById(R.id.button_center);
        textView = (TextView) findViewById(R.id.secondTextView);

        ButtonClickListener buttonClickListener = new ButtonClickListener();

        center.setOnClickListener(buttonClickListener);
        topLeft.setOnClickListener(buttonClickListener);
        topRight.setOnClickListener(buttonClickListener);
        bottomLeft.setOnClickListener(buttonClickListener);
        bottomRight.setOnClickListener(buttonClickListener);
        navigate.setOnClickListener(buttonClickListener);

        startedServiceBroadcastReceiver = new StartedServiceBroadcastReceiver();
        startedServiceIntentFilter = new IntentFilter();
        startedServiceIntentFilter.addAction(Constants.ACTION1);
        startedServiceIntentFilter.addAction(Constants.ACTION2);
        startedServiceIntentFilter.addAction(Constants.ACTION3);
        startedServiceIntentFilter.addAction(Constants.ACTION4);
        startedServiceIntentFilter.addAction(Constants.ACTION5);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        registerReceiver(startedServiceBroadcastReceiver, startedServiceIntentFilter);
    }

    @Override
    protected void onPause() {
        unregisterReceiver(startedServiceBroadcastReceiver);
        super.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(Constants.NUM_TRIES, this.noOfTries);
        outState.putInt(Constants.NUM_SUCCESS, this.noOfSuccess);
        outState.putInt(Constants.NUM_FAILURE, this.noOfFailures);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if (savedInstanceState.containsKey(Constants.NUM_TRIES)) {
            noOfTries =  savedInstanceState.getInt(Constants.NUM_TRIES);
        }
        if (savedInstanceState.containsKey(Constants.NUM_SUCCESS)) {
            noOfTries =  savedInstanceState.getInt(Constants.NUM_SUCCESS);
        }
        if (savedInstanceState.containsKey(Constants.NUM_FAILURE)) {
            noOfTries =  savedInstanceState.getInt(Constants.NUM_FAILURE);
        }

        Toast.makeText(this, "Num tries : " + noOfTries + "Num success : "
                + noOfSuccess + "Num failure : " + noOfFailures, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        String message = "Result Code : ";
        if (requestCode == Constants.SECONDARY_ACTIVITY_REQ_CODE)
            Toast.makeText(this, message + resultCode, Toast.LENGTH_SHORT).show();
            if (resultCode == RESULT_OK) {
                this.noOfSuccess++;
                this.noOfTries++;
            }
            if (resultCode == RESULT_CANCELED){
                this.noOfFailures++;
                this.noOfTries++;
            }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_practical_test01_var08_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Intent intent = new Intent(this, PracticalTest01Var08Service.class);
        stopService(intent);
    }
}