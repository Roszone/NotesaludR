package org.roszonelib.notetools.navigation;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;

import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;

import org.roszonelib.notetools.R;
import org.roszonelib.notetools.utils.TimeUtils;

/**
 * Autor:  Rosember Perez Mengual
 * Proyecto : NotesaludR
 * Fecha: 23/01/2016
 * Empresa : Amedi S.a.S.
 */

public abstract class BaseNavigationActivity extends AppCompatActivity implements NavigationListener {
    private Toolbar mToolbar;
    private Drawer mDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_container);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        setupDrawer();
        if (savedInstanceState == null) {
            onStartActivity();
        }
    }

    private void setupDrawer() {
        mDrawer = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(mToolbar)
                .build();
    }

    @Override
    public Drawer getDrawer() {
        return mDrawer;
    }

    protected abstract void onStartActivity();

    /**
     * Habilita o desabilita el modo pantalla completa
     *
     * @param showInFullScreen -
     */
    @Override
    public void enableFullScreen(boolean showInFullScreen) {
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
    public Toolbar getToolbar() {
        return mToolbar;
    }

    @Override
    public void setHomeAsUpEnabled(boolean enabled) {
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

    @Override
    public void setToolbar(boolean enable) {
        if (getSupportActionBar() != null) {
            ActionBar bar = getSupportActionBar();
            if (!enable) {
                bar.hide();
            } else if (!bar.isShowing()) {
                bar.show();
            }
        }
    }

    @Override
    public void setPage(PageFragment masterPage) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container, masterPage)
                .commitAllowingStateLoss();
    }

}
