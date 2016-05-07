package org.roszonelib.notetools.interfaces;

/**
 * ====================================
 * Proyecto : NotesaludR
 * Empresa  : Amedi S.a.s.
 * Autor    : Rosember
 * Fecha    : 29/03/2016 16:47
 * ====================================
 */
public interface LoginCallback {
    void onLoginSuccess();
    void onLoginError(String reason);
}
