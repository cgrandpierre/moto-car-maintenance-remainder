package com.yromela.mocamare;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yromela.mocamare.db.DbHelper;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
import static com.yromela.mocamare.db.DbHelper.VehicleTable;
import static com.yromela.mocamare.db.DbHelper.VehicleTable.COL_KM;
import static com.yromela.mocamare.db.DbHelper.VehicleTable.COL_NAME;
import static com.yromela.mocamare.db.DbHelper.VehicleTable._ID;


public class Main extends Activity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        final RelativeLayout layout = (RelativeLayout) findViewById(R.id.main_layout);

//        final RelativeLayout layout = new RelativeLayout(this);
//
//        final ListView listView = (ListView) findViewById(R.id.vehicle_list);
        final DbHelper dbHelper = new DbHelper(this);
        final SQLiteDatabase database = dbHelper.getReadableDatabase();
        final String[] projection = {_ID, COL_NAME, COL_KM};

        final Cursor c = database.query(VehicleTable.NAME, projection, null, null, null, null, null);

        final StringBuilder text = new StringBuilder();
        while (c.moveToNext()) {
            final int id = c.getInt(c.getColumnIndex(_ID));
            final long kms = c.getLong(c.getColumnIndex(COL_KM));
            final String name = c.getString(c.getColumnIndex(COL_NAME));
            text.append(String.format("%d %s (%d)", id, name, kms)).append("\n");
        }

        final TextView itemView = new TextView(this);
        itemView.setText(text.toString());
        layout.addView(itemView, new ViewGroup.LayoutParams(MATCH_PARENT, WRAP_CONTENT));


//        setContentView(layout);
    }


    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        final int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void addVehicle(final View view) {
        startActivity(new Intent(this, AddVehicleActivity.class));
    }
}
