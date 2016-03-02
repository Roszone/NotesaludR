package com.amedidevelopment.notesaludr.controllers;

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

    public static boolean hasUserLogged() {
        CustomPreferences sh = new CustomPreferences(DeviceManager.getInstance(), "AccountController");
        return sh.getIntegerOrDefault(R.string.shared_login_id, DEFAULT_USER) != DEFAULT_USER;
    }
}
