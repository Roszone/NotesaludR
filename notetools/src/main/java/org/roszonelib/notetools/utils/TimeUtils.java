package org.roszonelib.notetools.utils;

import android.os.Handler;

/**
 * ====================================
 * Proyecto : NotesaludR
 * Empresa  : Amedi S.a.s.
 * Autor    : Rosember Perez Mengual
 * Fecha    : 03/02/2016 17:47
 * ====================================
 */
public class TimeUtils {
    public static void setTimeOut(final Timeout timeOut, int timeInMilliseconds) {
        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            public void run() {
                timeOut.onTimeout();
                handler.removeCallbacks(this);
            }
        };
        handler.postDelayed(runnable, timeInMilliseconds);
    }
    public interface Timeout {
        void onTimeout();
    }
}
