package com.amedidevelopment.notesaludr.controllers;

import android.content.Context;
import android.os.Bundle;

import com.amedidevelopment.notesaludr.R;
import com.amedidevelopment.notesaludr.views.login.LoginFragment;
import com.amedidevelopment.notesaludr.views.main.DefaultFragment;
import com.amedidevelopment.notesaludr.views.main.SplashScreenFragment;

import org.roszonelib.notetools.navigation.MasterPageFragment;
import org.roszonelib.notetools.storage.CustomPreferences;

/**
 * Autor:  Rosember Perez Mengual
 * Proyecto : NotesaludR
 * Fecha: 22/01/2016
 * Empresa : Amedi S.a.S.
 */
public class NavController {
    //Almacena las preferencias compartidas
    //paginas a las que el usuario podra navegar
    public static final int PAGE_SPLASH_SCREEN =0;
    public static final int PAGE_DEFAULT = 1;
    public static final int PAGE_LOGIN = 2;
    public static final int PAGE_RESTORE_SCREEN = 3;
    public static final int PAGE_NURSE_MAIN = 4;

    //Manejador de las preferencias compartidas entre usuarios
    private CustomPreferences mPreference;

    private Context mContext;
    private MasterPageFragment mInstance;

    public NavController(Context context) {
        mContext = context;
        mPreference = new CustomPreferences(mContext,"NavController");
    }

    public boolean willShowSplash() {
        return mPreference.getBooleanOrDefault(R.string.shared_show_splash, true);
    }

    public void restoreInstance(Bundle savedInstanceState) {

    }

    public boolean hasSavedInstance() {
        return false;
    }

    public MasterPageFragment getCurrentMasterPage() {
        return mInstance;
    }

    /**
     * Retorna que master fragment debe ser mostrado para cada llamada
     * @param navigationId id
     * @param args arg
     * @return -
     */
    public static MasterPageFragment getPage(int navigationId, Bundle args) {
        switch (navigationId) {
            case PAGE_SPLASH_SCREEN:
                return new SplashScreenFragment();
            case PAGE_LOGIN:
                return new LoginFragment();
            default:
                return new DefaultFragment().addArguments(args);
        }
    }

    public static MasterPageFragment getPage(int id) {
        return getPage(id,null);
    }
}
