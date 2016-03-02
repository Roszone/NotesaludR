package com.amedidevelopment.notesaludr.views.fragments;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.amedidevelopment.notesaludr.R;

import org.roszonelib.notetools.adapter.CustomPageAdapter;
import org.roszonelib.notetools.navigation.MasterPageFragment;
import org.roszonelib.notetools.navigation.PageFragment;

/**
 * ====================================
 * Proyecto : NotesaludR
 * Empresa  : Amedi S.a.s.
 * Autor    : Rosember
 * Fecha    : 22/02/2016 11:57
 * ====================================
 */
public class LoginFragment extends MasterPageFragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.account_login, null);
        TabLayout tl = (TabLayout) view.findViewById(R.id.sliding_tabs);
        ViewPager vp = (ViewPager) view.findViewById(R.id.viewpager);
        CustomPageAdapter mAdapter = new CustomPageAdapter(getChildFragmentManager());
        mAdapter.addFragment(new SingInFragment(),"Inicio");
        mAdapter.addFragment(new TempSingInFragment(),"Tutorial");
        vp.setAdapter(mAdapter);
        tl.setupWithViewPager(vp);
        return view;
    }
    public static class SingInFragment extends PageFragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.test_btn, null);
            Button btn =(Button)view.findViewById(R.id.button);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getNavigation().setPage(new SplashScreenFragment());
                }
            });
            return view;
        }
    }

    public static class TempSingInFragment extends PageFragment{

    }

}
