package com.amedidevelopment.notesaludr.views.activity;

import android.os.Bundle;
import android.view.View;

import com.amedidevelopment.notesaludr.controllers.NavController;
import com.amedidevelopment.notesaludr.models.bll.AccountBll;
import com.amedidevelopment.notesaludr.models.bll.DrawerBuilderBll;
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
            setPage(NavController.getPage(isUserConnected() ? NavController.Pages.Main : NavController.Pages.Login));
        }
    }


    @Override
    public void onCreateDrawer() {
        if (mDrawer != null) mDrawer.removeAllItems();
        mDrawer = new DrawerBuilderBll(this, getToolbar())
                .enableNavigationUser(isUserConnected())
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        onNavigationItemClick(DrawerBuilderBll.getPageByIdentifier(drawerItem));
                        return false;
                    }
                }).build();
    }

    private boolean isUserConnected() {
        return AccountBll.isUserConnected(this);
    }

    private void onNavigationItemClick(NavController.Pages item) {
        NavController.sendAction(item, this);
    }
}
