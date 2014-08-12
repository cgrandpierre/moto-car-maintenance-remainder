package com.yromela.mocamare;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

public class AddVehicleActivity extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_vehicle);
    }

    public void performVehicleAdding(View view) {
        new AlertDialog.Builder(this)
                .setTitle("Hehe c'pas fait")
                .setMessage("TODO")
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_info)
                .show();
    }
}



