package com.amedidevelopment.notesaludr.views.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amedidevelopment.notesaludr.R;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;

import org.roszonelib.notetools.navigation.PageFragment;

/**
 * ====================================
 * Proyecto : NotesaludR
 * Empresa  : Amedi S.a.s.
 * Autor    : Rosember
 * Fecha    : 22/02/2016 11:57
 * ====================================
 */
public class LoginFragment extends PageFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login, null);
        return view;
    }
}
