package org.roszonelib.notetools.navigation;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;


/**
 * ====================================
 * Proyecto : NotesaludR
 * Empresa  : Amedi S.a.s.
 * Autor    : Rosember Perez Mengual
 * Fecha    : 03/02/2016 13:04
 * ====================================
 */
public abstract class PageFragment extends Fragment {
    private NavigationListener mListener;

    /*
    * Debido a que onAttach(Context)
    * no es llamado en pre APi 23 se usa onAttachToContext
    */
    @TargetApi(23)
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        onAttachToContext(context);
    }

    /*
     * Deprecated on API 23
     * se usa onAttachToContext
     */
    @SuppressWarnings("deprecation")
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            onAttachToContext(activity);
        }
    }

    /*
     * Called when the fragment attaches to the context
     */
    protected void onAttachToContext(Context context) {
        NavigationListener listener = (NavigationListener) context;
        if (listener != null) {
            mListener = listener;
        } else {
            throw new ClassCastException("Need Implement NavigationListener");
        }

    }

    /**
     * Obtiene el callback de navegacion heredado del fragment maestro
     *
     * @return callback
     */
    public NavigationListener getNavigation() {
        return mListener;
    }


    public PageFragment addArguments(Bundle args) {
        setArguments(args);
        return this;
    }
}

