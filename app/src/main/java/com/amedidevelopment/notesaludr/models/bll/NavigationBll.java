package com.amedidevelopment.notesaludr.models.bll;

import android.app.Activity;
import android.content.Intent;

import com.amedidevelopment.notesaludr.controllers.activity.SettingsActivity;
import com.amedidevelopment.notesaludr.controllers.fragments.LoginFragment;
import com.amedidevelopment.notesaludr.controllers.fragments.MainFragment;
import com.amedidevelopment.notesaludr.controllers.fragments.MenuNineFragment;
import com.amedidevelopment.notesaludr.models.enums.NavigationPage;

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

    public static PageFragment getPage(NavigationPage page) {
        switch (page) {
            case Login:
                return new LoginFragment();
            case Main:
                return new MainFragment();
            case SupportVideo:
                return new MenuNineFragment();
            default:
                return new LoginFragment();
        }
    }

    public static void sendAction(NavigationPage item, BaseFragmentActivity activity) {
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
