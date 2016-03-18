package org.roszonelib.notetools.navigation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;

import org.roszonelib.notetools.R;

/**
 * ====================================
 * Proyecto : NotesaludR
 * Empresa  : Amedi S.a.s.
 * Autor    : Rosember
 * Fecha    : 16/03/2016 17:56
 * ====================================
 */
public abstract class BaseFragmentActivity<fragment extends Fragment> extends AppCompatActivity implements NavigationListener<fragment> {
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_container);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
    }

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
        }
    }

    @Override
    public void showToolbar(boolean enable) {
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
    public void setPage(fragment masterPage) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, masterPage)
                .commitAllowingStateLoss();
    }

    public void setPage(android.app.Fragment fragment) {
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }

}