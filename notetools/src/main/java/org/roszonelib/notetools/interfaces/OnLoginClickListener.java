package org.roszonelib.notetools.interfaces;

import com.afollestad.materialdialogs.MaterialDialog;

public interface OnLoginClickListener {
    void onLogin(String username, String password, boolean isConnected);

    void onOptions();
}