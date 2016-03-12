package com.amedidevelopment.notesaludr.controllers;

import android.content.Context;

import com.amedidevelopment.notesaludr.R;

import org.roszonelib.notetools.storage.CustomPreferences;

/**
 * Autor:  Rosember Perez Mengual
 * Proyecto : NotesaludR
 * Fecha: 23/01/2016
 * Empresa : Amedi S.a.S.
 */
public class AccountController {
    public static final int DEFAULT_USER = 0;

    public static boolean hasUserLogged(Context context) {
        CustomPreferences custom = CustomPreferences.newInstance(context);
        return custom.getIntegerOrDefault(R.string.sharedLoginId, DEFAULT_USER) != DEFAULT_USER;
    }
}
