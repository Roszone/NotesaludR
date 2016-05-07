package com.amedidevelopment.notesaludr.controllers.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amedidevelopment.notesaludr.R;
import com.amedidevelopment.notesaludr.models.bll.AccountBll;
import com.amedidevelopment.notesaludr.models.bll.NavigationBll;
import com.amedidevelopment.notesaludr.models.enums.NavigationPage;

import org.roszonelib.notetools.interfaces.LoginCallback;
import org.roszonelib.notetools.navigation.PageFragment;
import org.roszonelib.notetools.notifications.NoteMaterialDialog;
import org.roszonelib.notetools.utils.SimpleViewUtils;


/**
 * ====================================
 * Proyecto : NotesaludR
 * Empresa  : Amedi S.a.s.
 * Autor    : Rosember
 * Fecha    : 22/02/2016 11:57
 * ====================================
 */
public class LoginFragment extends PageFragment implements View.OnClickListener {
    private AccountBll mBll;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        mBll = new AccountBll(getContext());
        View view = inflater.inflate(R.layout.login, parent, false);
        SimpleViewUtils.addListener(view, R.id.btn_login, this);
        SimpleViewUtils.addListener(view, R.id.btn_manual, this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                mBll.showLoginDialog(AccountBll.DEFAULT_USER_NAME, new LoginCallback() {
                    @Override
                    public void onLoginSuccess() {
                        getNavigation().setPage(NavigationBll.getPage(NavigationPage.Main));
                    }

                    @Override
                    public void onLoginError(String reason) {
                        NoteMaterialDialog.showInDialog(reason, getContext());
                    }
                });
                break;
            case R.id.btn_manual:

                break;
        }
    }
}
