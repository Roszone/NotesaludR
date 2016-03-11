package org.roszonelib.notetools.navigation;


import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;

import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.materialize.color.Material;

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
    private Toolbar mToolbar;

    public static int SPLASH_SCREEN_TIMEOUT = 2000;

    //Almacena el fragmento maestro actual
    private MasterPageFragment mMasterFragment;

    //Almacena las preferencias de usuario
    private CustomPreferences mPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPreference = new CustomPreferences(this, "BaseNavigation");
        setLayoutMode();
        setContentView(R.layout.fragment_container);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        if (isSplashScreenEnabled()) {
            setFullScreen(true);
            onSplashScreen();
            TimeUtils.setTimeOut(new TimeUtils.Timeout() {
                @Override
                public void onTimeout() {
                    setFullScreen(false);
                    onStartActivity();
                }
            }, SPLASH_SCREEN_TIMEOUT);
        } else {
            onStartActivity();
        }
    }

    private void setLayoutMode() {
        if (isPortraitEnabled()) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
    }

    protected abstract boolean isPortraitEnabled();

    public CustomPreferences getPreference() {
        return mPreference;
    }

    protected void setHomeAsUpEnabled(Boolean enabled) {
        if (getSupportActionBar() != null) {
            ActionBar bar = getSupportActionBar();
            bar.setHomeButtonEnabled(enabled);
            bar.setDisplayHomeAsUpEnabled(enabled);
            bar.setHomeAsUpIndicator(new IconicsDrawable(this)
                    .icon(GoogleMaterial.Icon.gmd_arrow_back)
                    .color(Color.WHITE)
                    .sizeDp(20)
                    );
        }
    }

    protected abstract boolean isSplashScreenEnabled();

    /**
     * Muestra el fragment actual en pantalla completa
     *
     * @param showInFullScreen -
     */
    private void setFullScreen(boolean showInFullScreen) {
        if (showInFullScreen) {
            mToolbar.setVisibility(View.GONE);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        } else {
            mToolbar.setVisibility(View.VISIBLE);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        }
    }

    @Override
    public boolean isTwoPaneEnabled() {
        return false;
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

    @Override
    public void backPage() {

    }

}
