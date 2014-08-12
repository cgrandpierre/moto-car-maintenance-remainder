package com.yromela.mocamare.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public final class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "mocamare.db";


    private static abstract class VehicleTable implements BaseColumns {
        private static final String NAME = "vehicle";
        private static final String COL_NAME = "name";
        private static final String COL_KM = "km";

        private static final String SQL_CREATE_TABLE_REQUEST = String.format("CREATE TABLE %s (" +
                "%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                "%s VARCHAR(255) NOT NULL," +
                "%s MEDIUMINT[(M)] [UNSIGNED] [ZEROFILL])", NAME, _ID, COL_NAME, COL_KM);

        private static final String SQL_DELETE_TABLE_REQUEST = "DROP TABLE IF EXISTS " + NAME;
    }

    private final SQLiteDatabase database;


    public DbHelper(final Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        database = this.getWritableDatabase();
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
        return database.insert(VehicleTable.NAME, "<null>", values);
    }

}
