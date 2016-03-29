package com.amedidevelopment.notesaludr.views.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amedidevelopment.notesaludr.R;
import com.amedidevelopment.notesaludr.controllers.NavController;
import com.amedidevelopment.notesaludr.models.bll.AccountBll;

import org.roszonelib.notetools.interfaces.OnLoginCallback;
import org.roszonelib.notetools.navigation.PageFragment;
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
    private AccountBll mLogic;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login, parent, false);
        mLogic = new AccountBll(getContext());
        SimpleViewUtils.addListener(view, R.id.btn_login, this);
        SimpleViewUtils.addListener(view, R.id.btn_manual, this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                mLogic.showDialogForLogin(AccountBll.DEFAULT_USER_NAME, new OnLoginCallback() {
                    @Override
                    public void onLoginSuccess(Integer userId) {
                        mLogic.saveUserId(userId);
                        getNavigation().onCreateDrawer();
                        getNavigation().setPage(NavController.getPage(NavController.Pages.Main));
                    }

                    @Override
                    public void onLoginFail(String reason) {
                        showInToast(reason);
                    }
                });
                break;
            case R.id.btn_manual:
                break;
        }
    }
}
