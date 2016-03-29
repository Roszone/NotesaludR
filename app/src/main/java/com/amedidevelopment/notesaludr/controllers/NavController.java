package com.amedidevelopment.notesaludr.controllers;

import com.amedidevelopment.notesaludr.R;
import com.amedidevelopment.notesaludr.models.bll.AccountBll;
import com.amedidevelopment.notesaludr.views.activity.SettingsActivity;
import com.amedidevelopment.notesaludr.views.fragments.LoginFragment;
import com.amedidevelopment.notesaludr.views.fragments.MainFragment;

import org.roszonelib.notetools.navigation.BaseFragmentActivity;
import org.roszonelib.notetools.navigation.PageFragment;
import org.roszonelib.notetools.settings.CustomPreferences;


/**
 * ====================================
 * Proyecto : NotesaludR
 * Empresa  : Amedi S.a.s.
 * Autor    : Rosember
 * Fecha    : 29/02/2016 10:26
 * ====================================
 */
public class NavController {
    public enum Pages {
        Login, Main, SupportChat, SupportFaq, SupportVideo, AdvanceStatus, AdvanceConfig, Exit,
    }

    public static PageFragment getPage(Pages page) {
        switch (page) {
            case Login:
                return new LoginFragment();
            case Main:
                return new MainFragment();
            default:
                return new LoginFragment();
        }
    }

    public static void sendAction(Pages item, BaseFragmentActivity activity) {
        switch (item) {
            case Exit:
                activity.finish();
                break;
            case AdvanceConfig:
                SettingsActivity.start(activity);
                break;
        }
    }

}
