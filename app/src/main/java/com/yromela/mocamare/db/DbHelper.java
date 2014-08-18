package com.yromela.mocamare.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.provider.BaseColumns;

public final class DbHelper extends SQLiteOpenHelper {

    private static final String AUTHORITY = "com.yromela.mocamare";

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "mocamare.db";


    public static abstract class VehicleTable implements BaseColumns {
        public static final Uri CONTENT_URI = Uri.parse("content://"
                + AUTHORITY + "/vehicles");
        public static final String NAME = "vehicle";
        public static final String COL_NAME = "name";
        public static final String COL_KM = "km";

        private static final String SQL_CREATE_TABLE_REQUEST = String.format("CREATE TABLE %s (" +
                "%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                "%s VARCHAR(255) NOT NULL," +
                "%s MEDIUMINT[(M)] [UNSIGNED] [ZEROFILL])", NAME, _ID, COL_NAME, COL_KM);

        private static final String SQL_DELETE_TABLE_REQUEST = "DROP TABLE IF EXISTS " + NAME;
    }


    public DbHelper(final Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(final SQLiteDatabase db) {
        db.execSQL(VehicleTable.SQL_CREATE_TABLE_REQUEST);
    }

    public void onUpgrade(final SQLiteDatabase db, final int oldVersion, final int newVersion) {
        db.execSQL(VehicleTable.SQL_DELETE_TABLE_REQUEST);
        onCreate(db);
    }

    public void onDowngrade(final SQLiteDatabase db, final int oldVersion, final int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public long createVehicle(final String name, final int kms) {
        final ContentValues values = new ContentValues();
        values.put(VehicleTable.COL_NAME, name);
        values.put(VehicleTable.COL_KM, kms);
        return this.getWritableDatabase().insert(VehicleTable.NAME, "<null>", values);
    }

    public long countVehicles() {
        return DatabaseUtils.queryNumEntries(this.getReadableDatabase(), VehicleTable.NAME, null, null);
    }

    public Cursor fetchAllVehicles() {
        final Cursor cursor = getReadableDatabase().query(VehicleTable.NAME, new String[]{VehicleTable._ID,
                        VehicleTable.COL_NAME, VehicleTable.COL_KM},
                null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }
}
