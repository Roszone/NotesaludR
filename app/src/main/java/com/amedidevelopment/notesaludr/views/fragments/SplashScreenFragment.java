package com.amedidevelopment.notesaludr.views.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amedidevelopment.notesaludr.R;

import org.roszonelib.notetools.navigation.MasterPageFragment;

/**
 * ====================================
 * Proyecto : NotesaludR
 * Empresa  : Amedi S.a.s.
 * Autor    : Rosember
 * Fecha    : 22/02/2016 11:56
 * ====================================
 */
public class SplashScreenFragment extends MasterPageFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.notesplashscreen, null);
        return view;
    }
}
