package org.roszonelib.notetools.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import org.roszonelib.notetools.navigation.PageFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * ====================================
 * Proyecto : NotesaludR
 * Empresa  : Amedi S.a.s.
 * Autor    : Rosember
 * Fecha    : 29/02/2016 12:12
 * ====================================
 */
public class CustomPageAdapter extends FragmentPagerAdapter{
    public List<PageFragment> mListFragments;
    public List<String> mNames;
    public CustomPageAdapter(FragmentManager fm) {
        super(fm);
        mListFragments =new ArrayList<>();
        mNames = new ArrayList<>();
    }
    @Override
    public Fragment getItem(int position) {
        return mListFragments.get(position);
    }

    public void addFragment(PageFragment fragment,String name) {
        mListFragments.add(fragment);
        mNames.add(name);
    }

    @Override
    public int getCount() {
        return mListFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mNames.get(position);
    }
}
