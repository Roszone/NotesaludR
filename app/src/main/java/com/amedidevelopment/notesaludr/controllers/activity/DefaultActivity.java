package com.amedidevelopment.notesaludr.controllers.activity;

import android.os.Bundle;
import android.view.View;

import com.amedidevelopment.notesaludr.models.bll.NavigationBll;
import com.amedidevelopment.notesaludr.models.bll.AccountBll;
import com.amedidevelopment.notesaludr.models.navigation.NavigationDrawer;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import org.roszonelib.notetools.navigation.BaseFragmentActivity;

/**
 * ====================================
 * Proyecto : NotesaludR
 * Empresa  : Amedi S.a.s.
 * Autor    : Rosember Perez Mengual
 * Fecha    : 03/02/2016 12:55
 * ====================================
 */
public class DefaultActivity extends BaseFragmentActivity {
    private Drawer mDrawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onCreateDrawer();
        if (savedInstanceState == null) {
            setPage(NavigationBll.getPage(isUserConnected() ? NavigationBll.Pages.Main : NavigationBll.Pages.Login));
        }
    }

    @Override
    public void onCreateDrawer() {
        if (mDrawer != null) mDrawer.removeAllItems();
        mDrawer = new NavigationDrawer(this, getToolbar())
                .isDefaultOptions(!isUserConnected())
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        onNavigationItemClick(NavigationDrawer.getPageByIdentifier(drawerItem));
                        return false;
                    }
                }).build();
    }

    private boolean isUserConnected() {
        return AccountBll.isUserConnected(this);
    }

    private void onNavigationItemClick(NavigationBll.Pages item) {
        NavigationBll.sendAction(item, this);
    }
}
