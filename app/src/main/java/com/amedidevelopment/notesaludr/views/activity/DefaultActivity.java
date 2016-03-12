package com.amedidevelopment.notesaludr.views.activity;
import com.amedidevelopment.notesaludr.controllers.AccountController;
import com.amedidevelopment.notesaludr.controllers.NavigationController;
import com.amedidevelopment.notesaludr.models.bll.Pages;

import org.roszonelib.notetools.navigation.BaseNavigationActivity;

/**
 * ====================================
 * Proyecto : NotesaludR
 * Empresa  : Amedi S.a.s.
 * Autor    : Rosember Perez Mengual
 * Fecha    : 03/02/2016 12:55
 * ====================================
 */
public class DefaultActivity extends BaseNavigationActivity {

    @Override
    public void onStartActivity() {
        setPage(NavigationController.getPage(AccountController.hasUserLogged(this) ? Pages.Main : Pages.Login));
    }
}
