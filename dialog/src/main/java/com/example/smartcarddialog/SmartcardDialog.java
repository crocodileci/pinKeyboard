package com.example.smartcarddialog;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.lang.reflect.Field;

/**
 * Created by gradydun on 2019/8/23.
 */

public class SmartcardDialog extends DialogFragment {

    private final String TAG = "SmartcardDialog";
    Button btn_confirm;
    Button btn_cancel;
    EditText editText_pinCode;

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
//        View view = inflater.inflate(R.layout.smartcard_dialog_container, container);
//
//        return view;
//    }

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

    private void shuffleNumbericPad(View view){

    }
}
