package org.roszonelib.notetools.database;

import org.roszonelib.notetools.anotations.SQLiteTableColumn;

public class SQLiteColumn {
    public SQLiteColumnType ColumnType;
    public String Name;
    public boolean IsNullable;
    public boolean IsPrimaryKey;


    public SQLiteColumn(String name, SQLiteTableColumn column) {
        Name = name;
        ColumnType = column.fieldType();
        IsNullable = column.isNullable();
        IsPrimaryKey = column.isPrimaryKey();
    }

    @Override
    public String toString() {
        return formatColumn(this);
    }

    private static String formatColumn(SQLiteColumn column) {
        StringBuilder sb = new StringBuilder();
        sb.append(column.Name);
        sb.append(column.ColumnType == SQLiteColumnType.STRING ? " TEXT" : " INTEGER");
        if (column.ColumnType == SQLiteColumnType.INTEGER_AUTOINCREMENT) {
            sb.append(" PRIMARY KEY AUTOINCREMENT");
        } else if (column.IsPrimaryKey) {
            sb.append(" PRIMARY KEY");
        }
        if (!column.IsNullable) {
            sb.append(" NOT NULL");
        }
        return sb.toString().toUpperCase();
    }
}