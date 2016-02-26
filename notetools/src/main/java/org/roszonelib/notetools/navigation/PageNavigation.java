package org.roszonelib.notetools.navigation;


import android.os.Bundle;

/**
 * Autor:  Rosember Perez Mengual
 * Proyecto : NotesaludR
 * Fecha: 25/01/2016
 * Empresa : Amedi S.a.S.
 */
public interface PageNavigation {

    boolean onSplashScreen();

    void onStartActivity();

    void onNavigate(int navigationId,Bundle extras);
}
