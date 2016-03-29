package org.roszonelib.notetools.navigation;

import android.app.Activity;
import android.graphics.Typeface;
import android.support.v7.widget.Toolbar;

import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

/**
 * ====================================
 * Proyecto : NotesaludR
 * Empresa  : Amedi S.a.s.
 * Autor    : Rosember
 * Fecha    : 12/03/2016 23:40
 * ====================================
 */
public abstract class BaseDrawerBuilder<navigation extends Enum> extends DrawerBuilder {

    public BaseDrawerBuilder(Activity activity, Toolbar toolbar) {
        withTranslucentStatusBar(true);
        withActionBarDrawerToggle(true);
        withActivity(activity);
        withToolbar(toolbar);
    }

    protected AccountHeaderBuilder getAccountHeader(int resIdBackground) {
        return new AccountHeaderBuilder()
                .withActivity(mActivity)
                .withHeaderBackground(resIdBackground);
    }

    protected void addPrimaryDrawerItem(int resId) {
        addDrawerItems(new PrimaryDrawerItem()
                .withName(resId)
                .withSelectable(false)
                .withTypeface(Typeface.DEFAULT_BOLD)
                .withEnabled(false));
    }

    protected void addNavigationDrawerItem(int resId, GoogleMaterial.Icon icon, navigation identifier) {
        addDrawerItems(new SecondaryDrawerItem()
                .withName(resId)
                .withIcon(icon)
                .withSelectable(false)
                .withIdentifier(identifier.ordinal()));
    }

    protected void addStickyDrawerItem(int resId) {
        addStickyDrawerItems(new SecondaryDrawerItem().withName(resId).withEnabled(false));
    }

}
