package com.amedidevelopment.notesaludr.models.bll;

import android.app.Activity;
import android.support.v7.widget.Toolbar;

import com.amedidevelopment.notesaludr.R;
import com.amedidevelopment.notesaludr.controllers.NavController;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import org.roszonelib.notetools.navigation.BaseDrawerBuilder;

/**
 * ====================================
 * Proyecto : NotesaludR
 * Empresa  : Amedi S.a.s.
 * Autor    : Rosember
 * Fecha    : 12/03/2016 22:59
 * ====================================
 */
public class DrawerBuilderBll extends BaseDrawerBuilder<NavController.Pages> {
    private Boolean isNavigationEnabled;

    public DrawerBuilderBll(Activity activity, Toolbar toolbar) {
        super(activity, toolbar);
    }

    public static NavController.Pages getPageByIdentifier(IDrawerItem drawerItem) {
        return NavController.Pages.values()[((int) drawerItem.getIdentifier())];
    }

    public BaseDrawerBuilder enableNavigationUser(boolean enable) {
        isNavigationEnabled = enable;
        return this;
    }

    @Override
    public Drawer build() {
        if (isNavigationEnabled) {
            setupNavigationUser();
        } else {
            setupNavigationDefault();
        }
        return super.build();
    }
    /**
     * Navegacion por defecto incluye paginas de soporte, ayuda y salida de la aplicacion
     */
    private void setupNavigationDefault() {
        withAccountHeader(getAccountHeader(R.drawable.header_amedi).build());
        addPrimaryDrawerItem(R.string.device);
        addNavigationDrawerItem(R.string.status, GoogleMaterial.Icon.gmd_phonelink_setup, NavController.Pages.AdvanceStatus);
        addNavigationDrawerItem(R.string.settings, GoogleMaterial.Icon.gmd_settings, NavController.Pages.AdvanceConfig);
        addPrimaryDrawerItem(R.string.support);
        addNavigationDrawerItem(R.string.support_chat, GoogleMaterial.Icon.gmd_chat, NavController.Pages.SupportChat);
        addNavigationDrawerItem(R.string.support_faq, GoogleMaterial.Icon.gmd_help, NavController.Pages.SupportFaq);
        addNavigationDrawerItem(R.string.support_video, GoogleMaterial.Icon.gmd_video_library, NavController.Pages.SupportVideo);
        addDrawerItems(new DividerDrawerItem());
        addNavigationDrawerItem(R.string.exit, GoogleMaterial.Icon.gmd_input, NavController.Pages.Exit);
        addStickyDrawerItem(R.string.copyright_app);
        withSelectedItem(-1);
    }

    private void setupNavigationUser() {
        withAccountHeader(getAccountHeader(R.drawable.header_amedi).build());
        addPrimaryDrawerItem(R.string.device);
        addStickyDrawerItem(R.string.copyright_app);
    }
}
