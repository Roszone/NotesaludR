package org.roszonelib.notetools.database;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteEntityHelper extends SQLiteOpenHelper {
    private BaseSQLiteEnty mEnty;

    public SQLiteEntityHelper(BaseSQLiteEnty sql) {
        super(sql.getContext(), sql.DatabaseName, null, sql.Version);
        mEnty = sql;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) for (SQLiteTable table : mEnty.getUpdateTables()) {
            db.execSQL(table.getCreateQuery());
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        for (SQLiteTable table : mEnty.getCreateTables()) db.execSQL(table.getCreateQuery());
        for (SQLiteTable table : mEnty.getUpdateTables()) db.execSQL(table.getCreateQuery());
    }
}