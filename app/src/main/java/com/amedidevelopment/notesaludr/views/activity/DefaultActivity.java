package com.amedidevelopment.notesaludr.views.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.amedidevelopment.notesaludr.controllers.NavController;
import com.amedidevelopment.notesaludr.models.bll.AccountBll;
import com.amedidevelopment.notesaludr.models.bll.DrawerBuilderBll;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import org.roszonelib.notetools.navigation.BaseFragmentActivity;
import org.roszonelib.notetools.navigation.PageFragment;

/**
 * ====================================
 * Proyecto : NotesaludR
 * Empresa  : Amedi S.a.s.
 * Autor    : Rosember Perez Mengual
 * Fecha    : 03/02/2016 12:55
 * ====================================
 */
public class DefaultActivity extends BaseFragmentActivity<PageFragment> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onCreateDrawer(getToolbar());
        if (savedInstanceState == null) {
            setPage(NavController.getPage(isUserConnected() ? NavController.Pages.Main : NavController.Pages.Login));
        }
    }

    private void onCreateDrawer(Toolbar toolbar) {
        new DrawerBuilderBll(this, toolbar)
                .enableNavigationUser(isUserConnected())
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        onNavigationItemClick(DrawerBuilderBll.getPageByIdentifier(drawerItem));
                        return false;
                    }
                }).build();
    }

    private void onNavigationItemClick(NavController.Pages item) {
        NavController.setAction(item, this);
    }


    private boolean isUserConnected() {
        return AccountBll.isUserLogin(this);
    }
}
