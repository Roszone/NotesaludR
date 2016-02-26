package com.amedidevelopment.notesaludr.views.main;

import android.os.Bundle;

import com.amedidevelopment.notesaludr.controllers.AccountController;
import com.amedidevelopment.notesaludr.controllers.NavController;

import org.roszonelib.notetools.navigation.BaseNavigationActivity;

/**
 * ====================================
 * Proyecto : NotesaludR
 * Empresa  : Amedi S.a.s.
 * Autor    : Rosember Perez Mengual
 * Fecha    : 03/02/2016 12:55
 * ====================================
 */
public class HomeActivity extends BaseNavigationActivity {

    //Almacena el controlador de navegacion de paginas
    private NavController mNav;

    @Override
    protected void onCreateNavigation(Bundle savedInstanceState) {
        mNav = new NavController(this);
    }

    @Override
    public boolean onSplashScreen() {
        if (!mNav.willShowSplash()) {
            return false;
        } else {
            onNavigate(NavController.PAGE_SPLASH_SCREEN, null);
            return true;
        }
    }

    @Override
    public void onStartActivity() {
        if (!AccountController.hasUserLogged()) {
            onNavigate(NavController.PAGE_LOGIN,null);
        } else {
            onNavigate(NavController.PAGE_DEFAULT,null);
        }
    }

    @Override
    public void onNavigate(int navigationId, Bundle extras) {
        setPage(NavController.getPage(navigationId, extras));
    }

}
