package org.roszonelib.notetools.interfaces;


import android.support.v7.widget.Toolbar;

import org.roszonelib.notetools.navigation.PageFragment;

/**
 * Autor:  Rosember Perez Mengual
 * Proyecto : NotesaludR
 * Fecha: 23/01/2016
 * Empresa : Amedi S.a.S.
 */
public interface OnNavigationCallback {

    void enableFullScreen(boolean enabled);

    void setPage(PageFragment masterPage);

    void setHomeAsUpEnabled(boolean enabled);

    void showToolbar(boolean enable);

    void onCreateDrawer();
}
