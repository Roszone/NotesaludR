package org.roszonelib.notetools.anotations;

import org.roszonelib.notetools.database.SQLiteColumnType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ====================================
 * Proyecto : NotesaludR
 * Empresa  : Amedi S.a.s.
 * Autor    : Rosember
 * Fecha    : 06/05/2016 14:24
 * ====================================
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface SQLiteTableColumn {
    boolean isPrimaryKey() default false;

    SQLiteColumnType fieldType();

    boolean isNullable() default true;
}
