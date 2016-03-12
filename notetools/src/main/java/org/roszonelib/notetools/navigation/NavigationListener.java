package org.roszonelib.notetools.navigation;

import android.os.Parcelable;
import android.support.v7.widget.Toolbar;

import com.mikepenz.materialdrawer.Drawer;

import org.roszonelib.notetools.storage.CustomPreferences;

/**
 * Autor:  Rosember Perez Mengual
 * Proyecto : NotesaludR
 * Fecha: 23/01/2016
 * Empresa : Amedi S.a.S.
 */
public interface NavigationListener {
    Drawer getDrawer();

    void enableFullScreen(boolean enabled);

    void setPage(PageFragment masterPage);

    void setHomeAsUpEnabled(boolean enabled);

    void setToolbar(boolean enable);

    Toolbar getToolbar();
}
