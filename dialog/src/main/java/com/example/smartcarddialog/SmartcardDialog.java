package com.example.smartcarddialog;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.lang.reflect.Field;

/**
 * Created by gradydun on 2019/8/23.
 */

public class SmartcardDialog extends DialogFragment implements View.OnClickListener {

    private final String TAG = "SmartcardDialog";

    //UI component
    private EditText editText_pinCode;
    private Button mNum0;
    private Button mNum1;
    private Button mNum2;
    private Button mNum3;
    private Button mNum4;
    private Button mNum5;
    private Button mNum6;
    private Button mNum7;
    private Button mNum8;
    private Button mNum9;
    private LinearLayout mDelPwd;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.smartcard_dialog_container, null);
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout

        editText_pinCode = view.findViewById(R.id.pincode);

        //disable to pop up soft keyboard when editText on focus
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP ){
            editText_pinCode.setShowSoftInputOnFocus(false);
        }else {
            editText_pinCode.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    //v.performClick();
                    return true;
                }
            });
        }

        initView(view);
        initNumbericPadClickHandler(view);

        builder.setView(view)
                .setTitle("查詢餘額")
                // Add action buttons
                .setPositiveButton("確認Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        Log.e(TAG, "on click confirm handler");

                        int pincode_length = editText_pinCode.getText().length();

                        //如果檢核條件Pass則直接return
                        try {
                            if (pincode_length >= 6 && pincode_length <= 12) {
                                //關閉Dialog
                                Field field = dialog.getClass().getSuperclass().getDeclaredField("mShowing");
                                field.setAccessible(true);
                                field.set(dialog, true);
                            } else {
                                //不關閉Dialog
                                Field field = dialog.getClass().getSuperclass().getDeclaredField("mShowing");
                                field.setAccessible(true);
                                field.set(dialog, false);
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                })
                .setNegativeButton("取消Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.e(TAG, "on click cancel handler");
                        try {
                            Field field = dialogInterface.getClass().getSuperclass().getDeclaredField("mShowing");
                            field.setAccessible(true);
                            field.set(dialogInterface, true);
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                });
        return builder.create();
    }

    private void initView(View view) {
        mNum0 = (Button) view.findViewById(R.id.button0);
        mNum1 = (Button) view.findViewById(R.id.button1);
        mNum2 = (Button) view.findViewById(R.id.button2);
        mNum3 = (Button) view.findViewById(R.id.button3);
        mNum4 = (Button) view.findViewById(R.id.button4);
        mNum5 = (Button) view.findViewById(R.id.button5);
        mNum6 = (Button) view.findViewById(R.id.button6);
        mNum7 = (Button) view.findViewById(R.id.button7);
        mNum8 = (Button) view.findViewById(R.id.button8);
        mNum9 = (Button) view.findViewById(R.id.button9);
        mDelPwd = (LinearLayout) view.findViewById(R.id.button_del);
        //洗牌
        int[] num = new int[]{ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        shuffleSort(num);
        mNum0.setText(String.valueOf(num[0]));
        mNum1.setText(String.valueOf(num[1]));
        mNum2.setText(String.valueOf(num[2]));
        mNum3.setText(String.valueOf(num[3]));
        mNum4.setText(String.valueOf(num[4]));
        mNum5.setText(String.valueOf(num[5]));
        mNum6.setText(String.valueOf(num[6]));
        mNum7.setText(String.valueOf(num[7]));
        mNum8.setText(String.valueOf(num[8]));
        mNum9.setText(String.valueOf(num[9]));
    }

    //洗牌算法
    private void shuffleSort(int[] data) {
        for (int i = 0; i < data.length - 1; i++) {
            int j = (int) (data.length * Math.random());
            swap(data, i, j);
        }
    }

    private void swap(int[] data, int i, int j) {
        if (i == j) {
            return;
        }
        data[i] = data[i] + data[j];
        data[j] = data[i] - data[j];
        data[i] = data[i] - data[j];
    }

    private void initNumbericPadClickHandler(View view){
        mNum0.setOnClickListener(this);
        mNum1.setOnClickListener(this);
        mNum2.setOnClickListener(this);
        mNum3.setOnClickListener(this);
        mNum4.setOnClickListener(this);
        mNum5.setOnClickListener(this);
        mNum6.setOnClickListener(this);
        mNum7.setOnClickListener(this);
        mNum8.setOnClickListener(this);
        mNum9.setOnClickListener(this);
        mDelPwd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        int keycode;

        if(v.getId() == R.id.button_del){
            keycode = KeyEvent.KEYCODE_DEL;
            Log.e(TAG, "delete pad is clicked");
        }else {
            String buttonText = ((Button)v).getText().toString();
            Log.e(TAG, "Numberic pad is clicked: " + buttonText);
            //取得key code
            keycode = KeyEvent.KEYCODE_NUMPAD_0 + Integer.parseInt(buttonText);
        }

        //發送給editText物件
        editText_pinCode.dispatchKeyEvent(new KeyEvent(0, 0, KeyEvent.ACTION_DOWN,
                keycode, 0));
    }
}
