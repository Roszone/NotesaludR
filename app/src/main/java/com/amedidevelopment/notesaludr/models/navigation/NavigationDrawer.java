package com.amedidevelopment.notesaludr.models.navigation;

import android.app.Activity;
import android.support.v7.widget.Toolbar;

import com.amedidevelopment.notesaludr.R;
import com.amedidevelopment.notesaludr.models.bll.NavigationBll;
import com.amedidevelopment.notesaludr.models.enums.NavigationPage;
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
public class NavigationDrawer extends BaseDrawerBuilder<NavigationPage> {
    private Boolean isDefault;

    public NavigationDrawer(Activity activity, Toolbar toolbar) {
        super(activity, toolbar);
    }

    public static NavigationPage getPageByIdentifier(IDrawerItem drawerItem) {
        return NavigationPage.values()[((int) drawerItem.getIdentifier())];
    }

    public BaseDrawerBuilder isDefaultOptions(boolean isDefault) {
        this.isDefault = isDefault;
        return this;
    }

    @Override
    public Drawer build() {
        if (isDefault) {
            setOptionsDefault();
        } else {
            setOptionsUser();
        }
        return super.build();
    }

    /**
     * Navegacion por defecto incluye paginas de soporte, ayuda y salida de la aplicacion
     */
    private void setOptionsDefault() {
        withAccountHeader(createAccountHeader(R.drawable.header_amedi).build());
        addPrimaryDrawerItem(R.string.device);
        addNavigationDrawerItem(R.string.status, GoogleMaterial.Icon.gmd_phonelink_setup, NavigationPage.AdvanceStatus);
        addNavigationDrawerItem(R.string.settings, GoogleMaterial.Icon.gmd_settings, NavigationPage.AdvanceConfig);
        addPrimaryDrawerItem(R.string.support);
        addNavigationDrawerItem(R.string.support_chat, GoogleMaterial.Icon.gmd_chat, NavigationPage.SupportChat);
        addNavigationDrawerItem(R.string.support_faq, GoogleMaterial.Icon.gmd_help, NavigationPage.SupportFaq);
        addNavigationDrawerItem(R.string.support_video, GoogleMaterial.Icon.gmd_video_library, NavigationPage.SupportVideo);
        addDrawerItems(new DividerDrawerItem());
        addNavigationDrawerItem(R.string.exit, GoogleMaterial.Icon.gmd_input, NavigationPage.LogOut);
        addStickyDrawerItem(R.string.copyright_app);
        withSelectedItem(-1);
    }

    private void setOptionsUser() {
        withAccountHeader(createAccountHeader(R.drawable.header_amedi).build());
        addPrimaryDrawerItem(R.string.device);
        addStickyDrawerItem(R.string.copyright_app);
    }
}
