package com.amedidevelopment.notesaludr.controllers.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.amedidevelopment.notesaludr.models.bll.AccountBll;
import com.amedidevelopment.notesaludr.models.bll.NavigationBll;
import com.amedidevelopment.notesaludr.models.enums.NavigationPage;
import com.amedidevelopment.notesaludr.models.navigation.NavigationDrawer;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;
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
public class MainActivity extends BaseFragmentActivity {


    private Drawer mDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onCreateDrawer();
        if (savedInstanceState == null) {
            setPage(NavigationBll.getPage(isUserConnected() ? NavigationPage.Main : NavigationPage.Login));
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

    private void onNavigationItemClick(NavigationPage item) {
        NavigationBll.sendAction(item, this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem item = menu.add("Buscar");
        item.setIcon(new IconicsDrawable(this)
                .icon(GoogleMaterial.Icon.gmd_search)
                .color(Color.WHITE)
                .sizeDp(24));
        return true;
    }
}
