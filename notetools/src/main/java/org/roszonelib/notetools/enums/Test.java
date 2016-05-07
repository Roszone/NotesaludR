package org.roszonelib.notetools.enums;

import org.roszonelib.notetools.anotations.SQLiteTableColumn;
import org.roszonelib.notetools.anotations.SQLiteTableName;
import org.roszonelib.notetools.database.SQLiteColumnType;

import java.util.Date;

/**
 * ====================================
 * Proyecto : NotesaludR
 * Empresa  : Amedi S.a.s.
 * Autor    : Rosember
 * Fecha    : 06/05/2016 18:38
 * ====================================
 */
@SQLiteTableName(tableName = "Rosember")
public class Test {
    @SQLiteTableColumn(fieldType = SQLiteColumnType.INTEGER_AUTOINCREMENT)
    public Integer testa;
    @SQLiteTableColumn(fieldType = SQLiteColumnType.INTEGER)
    public Integer teso;
    @SQLiteTableColumn(fieldType = SQLiteColumnType.STRING)
    public String tes3;
    @SQLiteTableColumn(fieldType = SQLiteColumnType.INTEGER)
    public Integer tesq;
    @SQLiteTableColumn(fieldType = SQLiteColumnType.DATE)
    public Date test;
}
