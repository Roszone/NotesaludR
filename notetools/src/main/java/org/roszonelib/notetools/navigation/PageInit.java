package org.roszonelib.notetools.navigation;


import android.os.Bundle;

/**
 * Autor:  Rosember Perez Mengual
 * Proyecto : NotesaludR
 * Fecha: 25/01/2016
 * Empresa : Amedi S.a.S.
 */
public interface PageInit {

    void onSplashScreen();

    void onStartActivity();

    boolean isEnabledTwoPane();
}
