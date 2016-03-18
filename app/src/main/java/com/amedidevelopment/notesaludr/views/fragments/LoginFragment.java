package com.amedidevelopment.notesaludr.views.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.MaterialDialog;
import com.amedidevelopment.notesaludr.R;
import com.amedidevelopment.notesaludr.models.bll.AccountBll;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;

import org.roszonelib.notetools.adapters.ListItemAdapter;
import org.roszonelib.notetools.dialogs.NoteMaterialDialog;
import org.roszonelib.notetools.navigation.PageFragment;
import org.roszonelib.notetools.utils.CustomViewUtils;


/**
 * ====================================
 * Proyecto : NotesaludR
 * Empresa  : Amedi S.a.s.
 * Autor    : Rosember
 * Fecha    : 22/02/2016 11:57
 * ====================================
 */
public class LoginFragment extends PageFragment implements View.OnClickListener {
    private MaterialDialog mDialog;
    private AccountBll mLogic;
    private String DEFAULT_USERNAME = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login, parent, false);
        mLogic = new AccountBll(getContext());
        CustomViewUtils.addListener(view, R.id.btn_login, this);
        CustomViewUtils.addListener(view, R.id.btn_manual, this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                showDialogForLogin(DEFAULT_USERNAME);
                break;
            case R.id.btn_manual:
                break;
        }
    }

    /**
     * Muestra en pantalla un dialog para recolectar
     * la informacion necesaria para iniciar sesión en el sistema
     *
     * @param username nombre de usuario
     */
    private void showDialogForLogin(String username) {
        if (mDialog != null && mDialog.isShowing()) mDialog.dismiss();
        mDialog = new NoteMaterialDialog(getActivity())
                .setTitle(getString(R.string.tittle_dialog_login))
                .createLoginDialog(username, new NoteMaterialDialog.OnLoginCallback() {
                    @Override
                    public void onLogin(String username, String password, MaterialDialog dialog) {

                    }

                    @Override
                    public void onOptionsClick(MaterialDialog dialog) {
                        dialog.dismiss();
                        showDialogForAdvanceOptions();
                    }
                });
        mDialog.show();
    }

    /**
     * Muestra en pantalla un dialog donde el usuario podra
     * escoger las opciones avanzadas de registro de usuario
     * o recuperacion de contraseñas
     */
    private void showDialogForAdvanceOptions() {
        if (mDialog != null && mDialog.isShowing()) mDialog.dismiss();
        //Creamos el adaptador con las opciones avanzadas
        ListItemAdapter adapter = new ListItemAdapter.builder(getActivity())
                .addSingleListItem(R.string.createUser, GoogleMaterial.Icon.gmd_person_add)
                .addSingleListItem(R.string.restorePass, GoogleMaterial.Icon.gmd_enhanced_encryption)
                .build();
        //Creamos el dialog con el manejador de eventos
        mDialog = new NoteMaterialDialog(getActivity()).setTitle(getString(R.string.advance_options))
                .adapter(adapter, new ListItemAdapter.OnListItemClickListener() {
                    @Override
                    public void onClick(ListItemAdapter.ListItem item, int position, MaterialDialog dialog) {
                        dialog.dismiss();
                        switch (item.Id) {
                            case R.string.createUser:
                                showDialogForCreateTempUser();
                                break;
                            case R.string.restorePass:
                                showDialogForRestorePassword();
                                break;
                        }
                    }
                }).build();
        mDialog.show();
    }

    /**
     * Muestra en pantalla un dialog para recolectar
     * la informacion necesaria para restaurar la contraseña
     */
    private void showDialogForRestorePassword() {
        if (mDialog != null && mDialog.isShowing()) mDialog.dismiss();

    }

    /**
     * Muestra en pantalla un dialog para recolectar
     * la informacion necesaria para Registrar un usuario temporal
     */
    private void showDialogForCreateTempUser() {
        if (mDialog != null && mDialog.isShowing()) mDialog.dismiss();

    }
}
