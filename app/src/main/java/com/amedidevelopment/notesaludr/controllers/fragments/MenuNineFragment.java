package com.amedidevelopment.notesaludr.controllers.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amedidevelopment.notesaludr.R;

import org.roszonelib.notetools.navigation.PageFragment;

/**
 * ====================================
 * Proyecto : NotesaludR
 * Empresa  : Amedi S.a.s.
 * Autor    : Rosember
 * Fecha    : 03/05/2016 17:00
 * ====================================
 */
public class MenuNineFragment extends PageFragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.menu_buttons,container, false);
    }
}
