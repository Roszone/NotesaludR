package org.roszonelib.notetools.navigation;


import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;

/**
 * Autor:  Rosember Perez Mengual
 * Proyecto : NotesaludR
 * Fecha: 23/01/2016
 * Empresa : Amedi S.a.S.
 */
public interface NavigationListener<masterPage extends Fragment> {

    void enableFullScreen(boolean enabled);

    void setPage(masterPage masterPage);

    void setHomeAsUpEnabled(boolean enabled);

    void showToolbar(boolean enable);

    Toolbar getToolbar();
}
