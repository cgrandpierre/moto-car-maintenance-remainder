package com.yromela.mocamare;

import android.app.ListActivity;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.yromela.mocamare.db.DbHelper;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
import static java.lang.String.format;


public class Main extends ListActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    // This is the Adapter being used to display the list's data
    SimpleCursorAdapter mAdapter;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        final TextView vehicleCount = (TextView) findViewById(R.id.vehicle_count);
        vehicleCount.setText(format("%d vehicles", new DbHelper(this).countVehicles()));

        // Create a progress bar to display while the list loads
        final ProgressBar progressBar = new ProgressBar(this);
        progressBar.setLayoutParams(new WindowManager.LayoutParams(WRAP_CONTENT,
                WRAP_CONTENT, Gravity.CENTER));
        progressBar.setIndeterminate(true);
        getListView().setEmptyView(progressBar);


        // Must add the progress bar to the root of the layout
        final ViewGroup root = (ViewGroup) findViewById(android.R.id.content);
//        root.addView(progressBar);

        // For the cursor adapter, specify which columns go into which views
        final String[] fromColumns = {DbHelper.VehicleTable.COL_NAME};
        final int[] toViews = {android.R.id.text1}; // The TextView in simple_list_item_1

        // Create an empty adapter we will use to display the loaded data.
        // We pass null for the cursor, then update it in onLoadFinished()
        mAdapter = new SimpleCursorAdapter(this,
                android.R.layout.simple_list_item_1, null,
                fromColumns, toViews, 0);
        setListAdapter(mAdapter);

        // Prepare the loader.  Either re-connect with an existing one,
        // or start a new one.
        getLoaderManager().initLoader(0, null, this);
    }

    // Called when a new Loader needs to be created
    public Loader<Cursor> onCreateLoader(final int id, final Bundle args) {
        // Now create and return a CursorLoader that will take care of
        // creating a Cursor for the data being displayed.
        return new CursorLoader(this, DbHelper.VehicleTable.CONTENT_URI,
                new String[]{DbHelper.VehicleTable._ID,
                        DbHelper.VehicleTable.COL_NAME}, null, null, null);
    }


    // Called when a previously created loader has finished loading
    public void onLoadFinished(final Loader<Cursor> loader, final Cursor data) {
        // Swap the new cursor in.  (The framework will take care of closing the
        // old cursor once we return.)
        mAdapter.swapCursor(data);
    }

    // Called when a previously created loader is reset, making the data unavailable
    public void onLoaderReset(final Loader<Cursor> loader) {
        // This is called when the last Cursor provided to onLoadFinished()
        // above is about to be closed.  We need to make sure we are no
        // longer using it.
        mAdapter.swapCursor(null);
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
