package com.amedidevelopment.notesaludr.models.bll;

import android.app.Activity;
import android.content.Intent;

import com.amedidevelopment.notesaludr.controllers.activity.SettingsActivity;
import com.amedidevelopment.notesaludr.controllers.fragments.LoginFragment;
import com.amedidevelopment.notesaludr.controllers.fragments.MainFragment;

import org.roszonelib.notetools.navigation.BaseFragmentActivity;
import org.roszonelib.notetools.navigation.PageFragment;


/**
 * ====================================
 * Proyecto : NotesaludR
 * Empresa  : Amedi S.a.s.
 * Autor    : Rosember
 * Fecha    : 29/02/2016 10:26
 * ====================================
 */
public class NavigationBll {
    public enum Pages {
        Main, SupportChat, SupportFaq, SupportVideo, AdvanceStatus, AdvanceConfig, LogOut, Login,
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
            case LogOut:
                activity.finish();
                break;
            case AdvanceConfig:
                start(activity, SettingsActivity.class);
                break;
        }
    }

    public static void start(Activity from, Class<?> activity) {
        from.startActivity(new Intent(from, activity));
    }
}
