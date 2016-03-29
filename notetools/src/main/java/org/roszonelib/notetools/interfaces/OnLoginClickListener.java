package org.roszonelib.notetools.interfaces;

import com.afollestad.materialdialogs.MaterialDialog;

public interface OnLoginClickListener {
    void onClickLogin(String username, String password, MaterialDialog dialog);

    void onClickOptions(MaterialDialog dialog);
}