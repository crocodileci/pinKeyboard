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

/**
 * Created by gradydun on 2019/8/23.
 */

public class SmartcardDialog extends DialogFragment {

    private final String TAG = "SmartcardDialog";
    Button btn_confirm;
    Button btn_cancel;

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

        builder.setView(view)
                .setTitle("查詢餘額")
                // Add action buttons
                .setPositiveButton("確認Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        Log.e(TAG, "on click confirm handler");
                    }
                })
                .setNegativeButton("取消Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.e(TAG, "on click cancel handler");
                    }
                });
        return builder.create();
    }
}
