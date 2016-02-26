package org.roszonelib.notetools.navigation;

import android.os.Bundle;

/**
 * ====================================
 * Proyecto : NotesaludR
 * Empresa  : Amedi S.a.s.
 * Autor    : Rosember Perez Mengual
 * Fecha    : 03/02/2016 12:55
 * ====================================
 */
public abstract class MasterPageFragment extends PageFragment {
    /**
     * Obtiene el fragment asociado a la pagina de detalle
     *
     * @return fragment secundario
     */
    public PageFragment getChildFragment(){
        return null;
    }

    /**
     * Identifica si este fragment debe ser presentado en pantalla completa
     * @return -
     */

    public Boolean isFullScreen() {
        return false;
    }

    /**
     * Identifica si este pagina debe ser mostrada en dos paginas
     * @return tiene fragment secundario
     */
    public boolean isTwoPane() {
        return getChildFragment() != null;
    }

    public MasterPageFragment addArguments(Bundle args) {
        setArguments(args);
        return this;
    }

}
