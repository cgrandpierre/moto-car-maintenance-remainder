package com.yromela.mocamare;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.yromela.mocamare.db.DbHelper;

import ua.org.zasadnyy.zvalidations.Field;
import ua.org.zasadnyy.zvalidations.Form;
import ua.org.zasadnyy.zvalidations.validations.IsPositiveInteger;
import ua.org.zasadnyy.zvalidations.validations.NotEmpty;

import static com.yromela.mocamare.util.UIUtils.showPopupInfo;
import static java.lang.Integer.parseInt;
import static java.lang.String.format;

public class AddVehicleActivity extends ActionBarActivity {

    private final Form mForm = new Form(this);

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_vehicle);
        mForm.addField(Field.using(getVehicleName()).validate(NotEmpty.build(this)));
        mForm.addField(Field.using(getVehicleKms()).validate(NotEmpty.build(this)).validate(IsPositiveInteger.build(this)));
    }

    public void performVehicleAdding(final View view) {
        if (mForm.isValid()) {
            final DbHelper dbHelper = new DbHelper(this);
            final String name = (getVehicleName()).getText().toString();
            final int kms = parseInt(getVehicleKms().getText().toString());
            dbHelper.createVehicle(name, kms);
        }

    }


    private EditText getVehicleName() {
        return (EditText) findViewById(R.id.vehicle_name);
    }

    private EditText getVehicleKms() {
        return ((EditText) findViewById(R.id.vehicle_km));
    }

}



