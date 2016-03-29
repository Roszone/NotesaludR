package org.roszonelib.notetools.notifications;

import android.content.Context;

import com.afollestad.materialdialogs.MaterialDialog;

/**
 * ====================================
 * Proyecto : NotesaludR
 * Empresa  : Amedi S.a.s.
 * Autor    : Rosember
 * Fecha    : 28/03/2016 11:23
 * ====================================
 */
public class NoteProgressDialog extends MaterialDialog.Builder {
    public NoteProgressDialog(Context context) {
        super(context);
    }

    public MaterialDialog showWaitDialog() {
        content = "Conectando... \nEspere un momento";
        indeterminateProgress = true;
        cancelable(false);
        return build();
    }

    public MaterialDialog showProgressDialog(int maxProgress) {
        title = "Procesando información";
        content = "Esperando datos...";
        indeterminateProgress = false;
        progressMax = maxProgress;
        progress = 0;
        cancelable(false);
        return build();
    }
}
