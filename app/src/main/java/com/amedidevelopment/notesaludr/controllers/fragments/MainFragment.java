package com.amedidevelopment.notesaludr.controllers.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amedidevelopment.notesaludr.R;
import com.amedidevelopment.notesaludr.models.bll.AccountBll;

import org.roszonelib.notetools.navigation.PageFragment;
import org.roszonelib.notetools.utils.SimpleViewUtils;

/**
 * ====================================
 * Proyecto : NotesaludR
 * Empresa  : Amedi S.a.s.
 * Autor    : Rosember
 * Fecha    : 29/02/2016 10:05
 * ====================================
 */
public class MainFragment extends PageFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.menu_buttons,container, false);

        return view;
    }

}
