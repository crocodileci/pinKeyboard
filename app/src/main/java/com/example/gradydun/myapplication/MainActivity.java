package com.example.gradydun.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.smartcarddialog.Log2;
import com.example.smartcarddialog.SmartcardDialog;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";
    Button btn2;
    Button btn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn2 = findViewById(R.id.button2);

        btn2.setOnClickListener(btn2Listener);

        btn3 = findViewById(R.id.button3);

        btn3.setOnClickListener(btn3Listener);
    }

    private Button.OnClickListener btn2Listener = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            Log.e(TAG, "button 2 clicked");
            SmartcardDialog dialog = new SmartcardDialog();

            dialog.setTitle("餘額查詢");
            dialog.setDialogType(SmartcardDialog.VERIFY_PIN);
            dialog.setOnInputCompleteListener(completeListener);
            dialog.setLogLevel(Log2.LogLevels.DEBUG);

            //禁止點選Dailog外部或返回鍵時關閉Dialog
            dialog.setCancelable(false);

            dialog.show(getFragmentManager(), "Test");
        }
    };

    private Button.OnClickListener btn3Listener = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.e(TAG, "button 3 clicked");
            SmartcardDialog dialog = new SmartcardDialog();

            dialog.setTitle("變更密碼");
            dialog.setDialogType(SmartcardDialog.CHANGE_PIN);
            dialog.setOnInputCompleteListener(completeListener);
            dialog.setLogLevel(Log2.LogLevels.DEBUG);

            //禁止點選Dailog外部或返回鍵時關閉Dialog
            dialog.setCancelable(false);

            dialog.show(getFragmentManager(), "Test");
        }
    };

    private SmartcardDialog.InputCompleteListener completeListener = new SmartcardDialog.InputCompleteListener() {
        @Override
        public void inputComplete(boolean isCanceled, String pinCode_orig, String pinCode_new) {
            if(isCanceled){
                showToast("User Canceled");
            }else{
                showToast("pinCode: " + pinCode_orig + " pinCode_new: " + pinCode_new);
            }
        }
    };

    private void showToast(String message){
        Display display = getWindowManager().getDefaultDisplay();
        int height = display.getHeight();
        Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.TOP, 0, height / 6);
        toast.show();
    }
}
