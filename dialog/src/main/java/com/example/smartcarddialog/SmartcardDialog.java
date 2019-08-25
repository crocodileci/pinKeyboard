package com.example.smartcarddialog;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.lang.reflect.Field;

/**
 * Created by gradydun on 2019/8/23.
 */

public class SmartcardDialog extends DialogFragment implements View.OnClickListener {

    private final String TAG = "SmartcardDialog";
    public final static String VERIFY_PIN = "VERIFY_PIN";
    public final static String CHANGE_PIN = "CHANGE_PIN";
    public final static String BUNDLE_KEY_DIALOG_TYPE = "BUNDLE_KEY_DIALOG_TYPE";
    public final static String BUNDLE_KEY_TITLE = "BUNDLE_KEY_TITLE";

    //UI component
    private EditText editText_pinCode;
    private EditText editText_pinCode_new1;
    private EditText editText_pinCode_new2;
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

    private String title;
    private String dialogType;

    private View.OnTouchListener disableSoftKeyboard_touchListener;
    private EditText focusEditText;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        View view = inflater.inflate(R.layout.smartcard_dialog_container, null);

        editText_pinCode = view.findViewById(R.id.pincode);
        editText_pinCode_new1 = view.findViewById(R.id.pincode_new1);
        editText_pinCode_new2 = view.findViewById(R.id.pincode_new2);

        title = "";

        Bundle bundle = getArguments();
        if (bundle != null) {
            dialogType = bundle.getString(SmartcardDialog.BUNDLE_KEY_DIALOG_TYPE);
            title = bundle.getString(SmartcardDialog.BUNDLE_KEY_TITLE);
            Log.d("Tag", "dialogType: " + dialogType + " title: " + title);
        }

        if(SmartcardDialog.VERIFY_PIN.equals(dialogType)){
            editText_pinCode_new1.setVisibility(View.GONE);
            editText_pinCode_new2.setVisibility(View.GONE);
        }

        //disable to pop up soft keyboard when editText on focus
        disableSoftKeyboard_touchListener = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //v.performClick();
                v.onTouchEvent(event);
                InputMethodManager inputMethod = (InputMethodManager)v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                if (inputMethod!= null) {
                    inputMethod.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }

                int id = v.getId();

                if(id == R.id.pincode){
                    focusEditText = editText_pinCode;
                }else if(id == R.id.pincode_new1){
                    focusEditText = editText_pinCode_new1;
                } else if(id == R.id.pincode_new2){
                    focusEditText = editText_pinCode_new2;
                }
                return true;
            }
        };

        focusEditText = editText_pinCode;

        editText_pinCode.setOnTouchListener(disableSoftKeyboard_touchListener);
        editText_pinCode_new1.setOnTouchListener(disableSoftKeyboard_touchListener);
        editText_pinCode_new2.setOnTouchListener(disableSoftKeyboard_touchListener);

        initView(view);
        initNumbericPadClickHandler(view);

        builder.setView(view)
                .setTitle(title)
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
        focusEditText.dispatchKeyEvent(new KeyEvent(0, 0, KeyEvent.ACTION_DOWN,
                keycode, 0));
    }
}
