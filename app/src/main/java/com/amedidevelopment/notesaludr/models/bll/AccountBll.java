package com.amedidevelopment.notesaludr.models.bll;

import android.content.Context;
import android.os.SystemClock;
import android.util.Log;

import com.afollestad.materialdialogs.MaterialDialog;
import com.amedidevelopment.notesaludr.R;
import com.amedidevelopment.notesaludr.models.dao.ServiceGenerator;
import com.amedidevelopment.notesaludr.models.dao.WebApi;
import com.amedidevelopment.notesaludr.models.dto.CredentialsDto;
import com.amedidevelopment.notesaludr.models.dto.UserDto;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;

import org.roszonelib.notetools.adapters.ListItemAdapter;
import org.roszonelib.notetools.interfaces.LoginCallback;
import org.roszonelib.notetools.interfaces.OnLoginClickListener;
import org.roszonelib.notetools.notifications.NoteMaterialDialog;
import org.roszonelib.notetools.settings.CustomPreferences;
import org.roszonelib.notetools.utils.DeviceUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

    /**
     * Muestra en pantalla un dialog para recolectar  la informacion necesaria para
     * iniciar sesión en el sistema, dependiendo su estado de conecion iniciara el proceso de
     * autenticacion en modo local o en modo remoto
     */
    public void showLoginDialog(String username, final LoginCallback callback) {
        mDialog.dismissIfShowing();
        mDialog.setLoginDialog(R.string.tittle_dialog_login, username, new OnLoginClickListener() {
            public void onLogin(String username, String password, boolean isConnected) {
                CredentialsDto credentials = createCredentials(username, password);
                if (isConnected) {
                    loginWithInternet(credentials, callback);
                } else {
                    loginWithInternet(credentials, callback);
                }
            }

            @Override
            public void onOptions() {
                showOptionsDialog();
            }
        });
        mDialog.show();
    }


    /**
     * Obtiene las credenciales necesarias para la autenticacion correcta
     *
     * @param username usuario
     * @param password contraseña
     * @return credenciales encriptadas
     */
    public CredentialsDto createCredentials(String username, String password) {
        CredentialsDto credentials = new CredentialsDto();
        //Apartado para encriptar las contraseñas
        credentials.Password = password;
        credentials.Username = username;
        credentials.DeviceId = DeviceUtils.getDeviceId(mContext);
        return credentials;
    }


    /**
     * Inicia sesion en el servidor remotamente usando conexion web
     *
     * @param credentials credenciales
     * @param callback    callback
     */
    public void loginWithInternet(final CredentialsDto credentials, final LoginCallback callback) {
        mDialog.dismissIfShowing();
        mDialog.createProgressDialog(false, new NoteMaterialDialog.OnShowListener() {
            @Override
            public void onShow(final MaterialDialog dialog) {
                WebApi.AccountLogin api = ServiceGenerator.createService(WebApi.AccountLogin.class);
                Call<UserDto> call = api.AuthenticateLogin(credentials);
                call.enqueue(new Callback<UserDto>() {
                    @Override
                    public void onResponse(Call<UserDto> call, Response<UserDto> response) {
                        UserDto user = response.body();
                        dialog.getProgressBar().setMax(100);
                        for (int i = 0; i < 100; i++) {
                            dialog.setContent("Testo nuevo" + i);
                        }
                         callback.onLoginSuccess();
                        dialog.dismiss();
                    }

                    @Override
                    public void onFailure(Call<UserDto> call, Throwable t) {
                        mDialog.dismissIfShowing();
                        callback.onLoginError(Log.getStackTraceString(t));
                    }
                });
            }
        }).show();
    }

    /**
     * muestra un dialog donde el usuario podra
     * escoger las opciones avanzadas de registro de usuario
     * y recuperacion de contraseñas
     */
    private void showOptionsDialog() {
        mDialog.dismissIfShowing();
        //Creamos el adaptador con las opciones avanzadas
        ListItemAdapter adapter = new ListItemAdapter.builder(mContext)
                .addSingleListItem(R.string.register, GoogleMaterial.Icon.gmd_person_add)
                .addSingleListItem(R.string.restore, GoogleMaterial.Icon.gmd_lock)
                .addSingleListItem(R.string.choice, GoogleMaterial.Icon.gmd_contacts)
                .build();
        //Creamos el dialog con el manejador de eventos
        mDialog.setAdapter(R.string.advance_options, adapter, new ListItemAdapter.OnListItemClickListener() {
            @Override
            public void onClick(ListItemAdapter.ListItem item, int position, MaterialDialog dialog) {
                dialog.dismiss();
                switch (item.Id) {
                    case R.string.register:
                        showRegisterUserDialog();
                        break;
                    case R.string.restore:
                        showRestorePasswordDialog();
                        break;
                    case R.string.choice:
                        showChoiceUserDialog();
                        break;
                }
            }
        });
        mDialog.show();
    }

    /**
     * Muestra lista de usuarios almacenados localmente en el dispostivo
     */
    private void showChoiceUserDialog() {

    }

    /**
     * Muestra form para recolectar la informacion necesaria para restaurar la contraseña
     */
    private void showRestorePasswordDialog() {

    }

    /**
     * Muestra form para recolectar la informacion necesaria para Registrar un usuario temporal
     */
    private void showRegisterUserDialog() {
        mDialog.dismissIfShowing();
        mDialog.setFormUserDialog();
        mDialog.show();
    }

    /**
     * Identifica si existe un usuario registrado en la aplicación
     */
    public static boolean isUserConnected(Context context) {
        return CustomPreferences.newInstance(context)
                .getIntegerOrDefault(R.string.sharedLoginId, DEFAULT_USER_ID) != DEFAULT_USER_ID;
    }

    /**
     * Guarda el usuario actual en las prerencias locales del dispositivo
     */
    public void saveUserId(String userId) {
        CustomPreferences.newInstance(mContext).putString(R.string.sharedLoginId, userId);
    }
}
