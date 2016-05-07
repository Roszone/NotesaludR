package org.roszonelib.notetools.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

/**
 * ====================================
 * Proyecto : NotesaludR
 * Empresa  : Amedi S.a.s.
 * Autor    : Rosember
 * Fecha    : 06/05/2016 11:21
 * ====================================
 */
public abstract class BaseSQLiteEnty {
    public String DatabaseName;
    private Context mContext;
    public Integer Version;
    public SQLiteDatabase nBD;
    private boolean mIsOpen;
    private SQLiteEntityHelper nHelper;

    public BaseSQLiteEnty(Context context, String name, Integer version) {
        mContext = context;
        DatabaseName = name;
        Version = version;
    }

    public SQLiteDatabase getDatabase() {
        return nBD;
    }

    public Context getContext() {
        return mContext;
    }

    public void setAlwaysOpen(boolean alwaysOpen) {
        mIsOpen = alwaysOpen;
        if (alwaysOpen) {
            open();
        } else {
            close();
        }
    }

    public void close() {
        if (!mIsOpen) nHelper.close();
    }

    public void open() {
        if (nHelper == null || !nBD.isOpen()) nHelper = new SQLiteEntityHelper(this);
        nBD = nHelper.getWritableDatabase();
    }

    public abstract List<SQLiteTable> getCreateTables();

    public abstract List<SQLiteTable> getUpdateTables();
}
