package com.amedidevelopment.notesaludr.controllers;


import com.amedidevelopment.notesaludr.models.bll.Pages;
import com.amedidevelopment.notesaludr.views.fragments.LoginFragment;
import com.amedidevelopment.notesaludr.views.fragments.MainFragment;
import com.amedidevelopment.notesaludr.views.fragments.SplashScreenFragment;

import org.roszonelib.notetools.navigation.MasterPageFragment;

/**
 * ====================================
 * Proyecto : NotesaludR
 * Empresa  : Amedi S.a.s.
 * Autor    : Rosember
 * Fecha    : 29/02/2016 10:26
 * ====================================
 */
public class NavigationController {

    public static MasterPageFragment get(Pages page) {
        switch (page) {
            case SplashScreen:
                return new SplashScreenFragment();
            case Login:
                return new LoginFragment();
            case Main:
                return new MainFragment();
            default:
                return new LoginFragment();
        }
    }
}
