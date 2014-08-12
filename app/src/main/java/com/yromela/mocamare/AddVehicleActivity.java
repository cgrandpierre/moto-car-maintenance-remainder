package com.yromela.mocamare;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;

import com.yromela.mocamare.db.DbHelper;

import static com.yromela.mocamare.util.UIUtils.showPopupInfo;
import static java.lang.Integer.parseInt;
import static java.lang.String.format;

public class AddVehicleActivity extends ActionBarActivity {


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_vehicle);
    }

    public void performVehicleAdding(final View view) {
        final DbHelper dbHelper = new DbHelper(this);
        final String name = ((EditText) findViewById(R.id.vehicle_name)).getText().toString();
        final int kms = parseInt(((EditText) findViewById(R.id.vehicle_km)).getText().toString());
        final long vehicleId = dbHelper.createVehicle(name, kms);

        showPopupInfo(this, format("Vehicle %s successfully added", vehicleId));
    }

}



