package org.roszonelib.notetools.utils;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * ====================================
 * Proyecto : NotesaludR
 * Empresa  : Amedi S.a.s.
 * Autor    : Rosember
 * Fecha    : 17/03/2016 10:22
 * ====================================
 */
public class CustomViewUtils {

    public static String getStringInput(View view, int resId, String errorMsg) {
        TextInputLayout til = getInput(view, resId);
        if (til != null && til.getEditText() != null) {
            EditText ed = til.getEditText();
            if (TextUtils.isEmpty(ed.getText())) {
                til.setError(errorMsg);
            } else {
                til.setError(null);
            }
            return ed.getText().toString();
        } else {
            return "";
        }
    }

    public static void addListener(View view, int resId, View.OnClickListener listener) {
        view.findViewById(resId).setOnClickListener(listener);
    }

    public static void setFocusEditText(View view, int resId, String text) {
        TextInputLayout ed = getInput(view, resId);
        if (ed != null && ed.getEditText() != null) {
            ed.getEditText().setText(text);
            setFocus(view.getContext(), ed);
        }
    }

    private static void setFocus(Context context, View view) {
        view.requestFocus();
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }

    private static TextInputLayout getInput(View view, int resId) {
        return view != null ? (TextInputLayout) view.findViewById(resId) : null;
    }

    public static void hideKeyboard(Activity activity) {
        activity.getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        );
    }

}
