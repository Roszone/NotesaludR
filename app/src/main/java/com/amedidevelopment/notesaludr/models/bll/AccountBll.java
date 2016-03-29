package com.amedidevelopment.notesaludr.models.bll;

import android.content.Context;

import com.afollestad.materialdialogs.MaterialDialog;
import com.amedidevelopment.notesaludr.R;
import com.amedidevelopment.notesaludr.controllers.DeviceManager;
import com.amedidevelopment.notesaludr.models.vo.CredentialsVo;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;

import org.roszonelib.notetools.adapters.ListItemAdapter;
import org.roszonelib.notetools.interfaces.OnLoginCallback;
import org.roszonelib.notetools.interfaces.OnLoginClickListener;
import org.roszonelib.notetools.notifications.NoteMaterialDialog;
import org.roszonelib.notetools.settings.CustomPreferences;

/**
 * Autor:  Rosember Perez Mengual
 * Proyecto : NotesaludR
 * Fecha: 23/01/2016
 * Empresa : Amedi S.a.S.
 */
public class AccountBll {
    public static final String DEFAULT_USER_NAME = "";
    public static final int DEFAULT_USER_ID = 0;
    private Context mContext;
    private NoteMaterialDialog mDialog;

    public AccountBll(Context context) {
        mContext = context;
        mDialog = new NoteMaterialDialog(mContext);
    }

    public static boolean isUserConnected(Context context) {
        return CustomPreferences.newInstance(context)
                .getIntegerOrDefault(R.string.sharedLoginId, DEFAULT_USER_ID) != DEFAULT_USER_ID;
    }

    public void saveUserId(Integer userId) {
        //Guardamos el id en las preferencias locales
        CustomPreferences.newInstance(mContext).putInteger(R.string.sharedLoginId, userId);
    }

    /**
     * Muestra en pantalla un dialog para recolectar
     * la informacion necesaria para iniciar sesión en el sistema
     */
    public void showDialogForLogin(String username, final OnLoginCallback callback) {
        mDialog.setLoginDialog(R.string.tittle_dialog_login, username, new OnLoginClickListener() {
            @Override
            public void onClickLogin(String username, String password, MaterialDialog dialog) {
                dialog.dismiss();
                //Creamos las credenciales para la autentificacion de usuario
                CredentialsVo credentials = new CredentialsVo(username, password);
                //Segun el estado de la conexion elegimos el modo conectado
                // o desconectado para ingresar al sistema
                if (DeviceManager.isConnectedToInternet(mContext)) {
                    loginWithInternet(credentials, callback);
                } else {
                    loginWithoutInternet(credentials, callback);
                }
            }

            @Override
            public void onClickOptions(MaterialDialog dialog) {
                //Mostramos el dialog para las opciones avanzadas
                showDialogForAdvanceOptions();
            }
        });
        mDialog.show();
    }

    private boolean loginWithoutInternet(CredentialsVo credentials, OnLoginCallback callback) {
        return loginWithInternet(credentials, callback);
    }

    private boolean loginWithInternet(CredentialsVo credentials, OnLoginCallback callback) {
        /*mDialog = new NoteProgressDialog(mContext).showWaitDialog();
        mDialog.show();
        UserVo user = TestUSER.getTestUser(credentials);
        //TODO remover temp user
        TimeUtils.setTimeOut(new TimeUtils.Timeout() {
            @Override
            public void onTimeout() {
                mDialog.setContent("Actualizando Usuario... \nRosember Perez mengual");
            }
        }, 1000);
        //TODO remover temp user
        TimeUtils.setTimeOut(new TimeUtils.Timeout() {
            @Override
            public void onTimeout() {
                mDialog.setContent("Actualizando Perfil... \nAuxiliar de Enfermeria");
            }
        }, 2000);
        TimeUtils.setTimeOut(new TimeUtils.Timeout() {
            @Override
            public void onTimeout() {
                mDialog.dismissDialog();
            }
        }, 5000);
        setDefaultUser(user);*/
        return true;
    }


    /**
     * muestra un dialog donde el usuario podra
     * escoger las opciones avanzadas de registro de usuario
     * y recuperacion de contraseñas
     */
    private void showDialogForAdvanceOptions() {
        //Creamos el adaptador con las opciones avanzadas
        ListItemAdapter adapter = new ListItemAdapter.builder(mContext)
                .addSingleListItem(R.string.createUser, GoogleMaterial.Icon.gmd_person_add)
                .addSingleListItem(R.string.restorePass, GoogleMaterial.Icon.gmd_lock)
                .addSingleListItem(R.string.listUser, GoogleMaterial.Icon.gmd_contacts)
                .build();
        //Creamos el dialog con el manejador de eventos
        mDialog.setAdapter(R.string.advance_options, adapter, new ListItemAdapter.OnListItemClickListener() {
            @Override
            public void onClick(ListItemAdapter.ListItem item, int position, MaterialDialog dialog) {
                dialog.dismiss();
                switch (item.Id) {
                    case R.string.createUser:
                        showDialogForCreateUser();
                        break;
                    case R.string.restorePass:
                        showDialogForRestorePassword();
                        break;
                    case R.string.listUser:
                        showDialogForSelectUser();
                        break;
                }
            }
        });
        mDialog.show();
    }

    /**
     * Muestra lista de usuarios almacenados localmente en el dispostivo
     */
    private void showDialogForSelectUser() {

    }

    /**
     * Muestra form para recolectar la informacion necesaria para restaurar la contraseña
     */
    private void showDialogForRestorePassword() {

    }

    /**
     * Muestra form para recolectar la informacion necesaria para Registrar un usuario temporal
     */
    private void showDialogForCreateUser() {
        mDialog = new NoteMaterialDialog(mContext)
                .setFormUserDialog();
        mDialog.show();
    }

}
