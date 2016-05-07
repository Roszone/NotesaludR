package org.roszonelib.notetools.database;

import android.text.TextUtils;
import android.util.Log;

import org.roszonelib.notetools.anotations.SQLiteTableColumn;
import org.roszonelib.notetools.anotations.SQLiteTableName;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * ====================================
 * Proyecto : NotesaludR
 * Empresa  : Amedi S.a.s.
 * Autor    : Rosember
 * Fecha    : 06/05/2016 15:44
 * ====================================
 */
public class SQLiteTable {
    public String Name;
    public boolean HasPrimaryKey;
    public List<SQLiteColumn> Columns;

    public SQLiteTable(String name) {
        Name = name;
        HasPrimaryKey = false;
        Columns = new ArrayList<>();
    }


    public void addColumn(SQLiteColumn column) {
        if (column.IsPrimaryKey || column.ColumnType == SQLiteColumnType.INTEGER_AUTOINCREMENT) {
            if (!HasPrimaryKey) {
                HasPrimaryKey = true;
                Columns.add(column);
            } else {
                Log.e("SQLiteTable", "Duplicated Primary Key " + column.Name + " in " + Name);
            }
        } else {
            Columns.add(column);
        }
    }

    public String getCreateQuery() {
        return String.format("CREATE TABLE  %s ( %s );", Name.toUpperCase(), TextUtils.join(",", Columns));
    }

    public static <T> SQLiteTable createTable(Class<T> obj) {
        SQLiteTable table = null;
        if (obj.isAnnotationPresent(SQLiteTableName.class)) {
            Annotation annotation = obj.getAnnotation(SQLiteTableName.class);
            SQLiteTableName tableName = (SQLiteTableName) annotation;
            table = new SQLiteTable(tableName.tableName());
            for (Field field : obj.getDeclaredFields()) {
                if (field.isAnnotationPresent(SQLiteTableColumn.class)) {
                    annotation = field.getAnnotation(SQLiteTableColumn.class);
                    SQLiteTableColumn column = (SQLiteTableColumn) annotation;
                    table.addColumn(new SQLiteColumn(field.getName(), column));
                }
            }
        }
        return table;
    }


}
