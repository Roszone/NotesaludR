package org.roszonelib.notetools.navigation;

import android.os.Parcelable;

import org.roszonelib.notetools.storage.CustomPreferences;

/**
 * Autor:  Rosember Perez Mengual
 * Proyecto : NotesaludR
 * Fecha: 23/01/2016
 * Empresa : Amedi S.a.S.
 */
public interface PageNavigationFragment {

    void backPage();

    void setPage(MasterPageFragment masterPage);

    void sendToParent(Parcelable args);

    void sendToChild(Parcelable args);

}
