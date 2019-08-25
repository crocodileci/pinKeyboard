package com.example.gradydun.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.smartcarddialog.SmartcardDialog;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";
    Button btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn2 = findViewById(R.id.button2);

        btn2.setOnClickListener(btn2Listener);
    }

    private Button.OnClickListener btn2Listener = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            Log.e(TAG, "button 2 clicked");
            SmartcardDialog dialog = new SmartcardDialog();
            dialog.setCancelable(false);
            dialog.show(getFragmentManager(), "Test");
        }
    };
}
