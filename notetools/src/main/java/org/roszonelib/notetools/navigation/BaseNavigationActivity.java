package org.roszonelib.notetools.navigation;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import org.roszonelib.notetools.R;
import org.roszonelib.notetools.storage.CustomPreferences;
import org.roszonelib.notetools.utils.TimeUtils;

/**
 * Autor:  Rosember Perez Mengual
 * Proyecto : NotesaludR
 * Fecha: 23/01/2016
 * Empresa : Amedi S.a.S.
 */

public abstract class BaseNavigationActivity extends AppCompatActivity implements PageNavigationFragment, PageInit {

    public static int SPLASH_SCREEN_TIMEOUT = 2000;

    //Almacena el fragmento maestro actual
    private MasterPageFragment mMasterFragment;

    //Almacena las preferencias de usuario
    private CustomPreferences mPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPreference = new CustomPreferences(this, "BaseNavigation");
        setContentView(R.layout.fragment_container);
        if (!isSplashScreenEnabled()) {
            onStartActivity();
        } else {
            setFullScreen(true);
            onSplashScreen();
            TimeUtils.setTimeOut(new TimeUtils.Timeout() {
                @Override
                public void onTimeout() {
                    setFullScreen(false);
                    onStartActivity();
                }
            }, SPLASH_SCREEN_TIMEOUT);
        }
    }

    /**
     * Muestra el fragment actual en pantalla completa
     *
     * @param showInFullScreen -
     */
    private void setFullScreen(boolean showInFullScreen) {
        if (showInFullScreen) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        } else {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        }
    }

    /**
     * Identifica cuando el splash screen esta habilitado
     *
     * @return - Es el primer inicio
     */
    private boolean isSplashScreenEnabled() {
        return mPreference.getBooleanOrDefault(R.string.isFirstBoot, true);
    }

    @Override
    public void backPage() {

    }

    @Override
    public void setPage(MasterPageFragment masterPage) {
        mMasterFragment = masterPage;
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container, mMasterFragment)
                .commitAllowingStateLoss();
    }

    @Override
    public void sendToParent(Parcelable args) {

    }

    @Override
    public void sendToChild(Parcelable args) {

    }

    /**
     * Identifica cuando el dispositivo esta en dos paneles
     *
     * @return
     */
    @Override
    public boolean isEnabledTwoPane() {
        return false;
    }
}
