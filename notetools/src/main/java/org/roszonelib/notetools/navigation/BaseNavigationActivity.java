package org.roszonelib.notetools.navigation;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;

import org.roszonelib.notetools.utils.TimeUtils;

/**
 * Autor:  Rosember Perez Mengual
 * Proyecto : NotesaludR
 * Fecha: 23/01/2016
 * Empresa : Amedi S.a.S.
 */

public abstract class BaseNavigationActivity extends AppCompatActivity implements PageNavigationFragment,PageNavigation {

    public static int SPLASH_SCREEN_TIMEOUT = 5000;

    //Determina cuando o no la actividad esta en dos paneles
    private boolean mTwoPane;

    //Almacen el fragmento maestro mostrado actualmente
    private MasterPageFragment mMasterFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onCreateNavigation(savedInstanceState);
        if (!onSplashScreen()) {
            onStartActivity();
        }else{
            TimeUtils.setTimeOut(new TimeUtils.onTimeout() {
                @Override
                public void onTimeout() {
                    onStartActivity();
                }
            }, SPLASH_SCREEN_TIMEOUT);
        }
    }

    protected abstract void onCreateNavigation(Bundle savedInstanceState);

    @Override
    public void backPage() {

    }

    @Override
    public void setPage(MasterPageFragment masterPage) {

    }

    @Override
    public void sendToParent(Parcelable args) {

    }

    @Override
    public void sendToChild(Parcelable args) {

    }
}
