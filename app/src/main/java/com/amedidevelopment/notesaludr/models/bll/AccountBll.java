package com.amedidevelopment.notesaludr.models.bll;

import android.content.Context;

import com.amedidevelopment.notesaludr.R;

import org.roszonelib.notetools.settings.CustomPreferences;

/**
 * Autor:  Rosember Perez Mengual
 * Proyecto : NotesaludR
 * Fecha: 23/01/2016
 * Empresa : Amedi S.a.S.
 */
public class AccountBll {
    public static final int DEFAULT_USER = 0;

    public AccountBll(Context context) {

    }

    public static boolean isUserLogin(Context context) {
        CustomPreferences custom = CustomPreferences.newInstance(context);
        return custom.getIntegerOrDefault(R.string.sharedLoginId, DEFAULT_USER) != DEFAULT_USER;
    }

    public String getDefaultUser() {
        return null;
    }
}
