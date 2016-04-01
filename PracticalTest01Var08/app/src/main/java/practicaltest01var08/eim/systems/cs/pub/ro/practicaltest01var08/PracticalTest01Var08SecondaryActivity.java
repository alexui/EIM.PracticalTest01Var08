package practicaltest01var08.eim.systems.cs.pub.ro.practicaltest01var08;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Alex on 4/1/2016.
 */
public class PracticalTest01Var08SecondaryActivity extends Activity {

    private Intent intentFromParent;
    private Intent intentToParent;

    //TODO creaza widgeturi
    private TextView textView;
    private Button buttonVerify;
    private Button buttonCancel;

    private class ButtonClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            intentToParent = new Intent();
            int resultCode = RESULT_OK;
            switch (v.getId()) {
                case R.id.button_verify :
                    resultCode = RESULT_OK;
                    break;
                case R.id.button_cancel :
                    resultCode = RESULT_CANCELED;
                    break;
            }
            setResult(resultCode, intentToParent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var08_secondary);

        //TODO initializeaza widgeturi
        intentFromParent = getIntent();
        textView = (TextView) findViewById(R.id.secondTextView);
        buttonVerify = (Button) findViewById(R.id.button_verify);
        buttonCancel = (Button) findViewById(R.id.button_cancel);

        ButtonClickListener buttonClickListener = new ButtonClickListener();

        buttonVerify.setOnClickListener(buttonClickListener);
        buttonCancel.setOnClickListener(buttonClickListener);

        textView.setText(intentFromParent.getStringExtra(Constants.INTENT_TEXT_TAG));
    }
}
