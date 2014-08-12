package com.yromela.mocamare.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class UIUtils {
    public static void showPopupInfo(final Context context, final String text) {
        new AlertDialog.Builder(context)
                .setTitle("Information")
                .setMessage(text)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int which) {
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_info)
                .show();
    }
}
