package com.yromela.mocamare;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.yromela.mocamare.db.DbHelper;

import static android.view.Gravity.CENTER;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
import static com.yromela.mocamare.R.id.menu_add_vehicle;
import static com.yromela.mocamare.R.id.menu_settings;
import static com.yromela.mocamare.R.id.vehicle_count;
import static com.yromela.mocamare.R.id.vehicle_list;
import static com.yromela.mocamare.R.id.vehicle_list_item_km_info;
import static com.yromela.mocamare.R.id.vehicle_list_item_name_info;
import static com.yromela.mocamare.db.DbHelper.VehicleTable.COL_KM;
import static com.yromela.mocamare.db.DbHelper.VehicleTable.COL_NAME;
import static java.lang.String.format;


public class Main extends ActionBarActivity {


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        final DbHelper dbHelper = new DbHelper(this);
        setupTitleTextView(dbHelper);
        setupVehicleListView(dbHelper);
    }

    private void setupTitleTextView(final DbHelper dbHelper) {
        final TextView vehicleCount = (TextView) findViewById(vehicle_count);
        vehicleCount.setText(format("%d vehicles", dbHelper.countVehicles()));
    }

    private void setupVehicleListView(final DbHelper dbHelper) {
        final ListView listView = (ListView) findViewById(vehicle_list);
        final ProgressBar progressBar = new ProgressBar(this);
        progressBar.setLayoutParams(new WindowManager.LayoutParams(WRAP_CONTENT, WRAP_CONTENT, CENTER));
        progressBar.setIndeterminate(true);
        listView.setEmptyView(progressBar);
        final SimpleCursorAdapter dataAdapter = new SimpleCursorAdapter(
                this,
                R.layout.vehicle_list_item,
                dbHelper.fetchAllVehicles(),
                new String[]{COL_NAME, COL_KM,},
                new int[]{vehicle_list_item_name_info, vehicle_list_item_km_info},
                0);
        listView.setAdapter(dataAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        final int id = item.getItemId();
        switch (id) {
            case menu_settings:
                return true;
            case menu_add_vehicle:
                startActivity(new Intent(this, AddVehicleActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
