package com.amedidevelopment.notesaludr.controllers.activity;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.amedidevelopment.notesaludr.R;

import org.roszonelib.notetools.settings.BaseSettingsActivity;

/**
 * ====================================
 * Proyecto : NotesaludR
 * Empresa  : Amedi S.a.s.
 * Autor    : Rosember
 * Fecha    : 15/03/2016 15:55
 * ====================================
 */
public class SettingsActivity extends BaseSettingsActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setPage(new GeneralPreferenceFragment());
        setHomeAsUpEnabled(true);
    }

    public static class GeneralPreferenceFragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.pref_general);
            setHasOptionsMenu(true);
            bindPreferenceSummaryToValue(findPreference("example_text"));
            bindPreferenceSummaryToValue(findPreference("example_list"));
        }
    }
}
