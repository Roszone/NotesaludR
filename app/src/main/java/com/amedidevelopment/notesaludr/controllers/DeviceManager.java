package com.amedidevelopment.notesaludr.controllers;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.PowerManager;
import android.provider.Settings;

/**
 * Autor:  Rosember Perez Mengual
 * Proyecto : NotesaludR
 * Fecha: 23/01/2016
 * Empresa : Amedi S.a.S.
 */
public class DeviceManager extends Application {
    private static DeviceManager mInstance;
    private PowerManager.WakeLock wakeLock;

    /**
     * Retorna la instancia unica del dispositivo
     */
    public static DeviceManager getInstance() {
        if(mInstance == null){
            mInstance = new DeviceManager();
        }
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    /**
     * Retorna el <code>ANDROID_ID</code> unico del dispositivo
     */
    public static String getDeviceId(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    /**
     * Verifica si existe alguna conexion activa de internet
     * (Edge, 3g, 4g, wifi)
     *
     * @return estado de la conexion
     */
    public static boolean isConnectedToInternet(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm == null) {
            return false;
        } else {
            NetworkInfo net = cm.getActiveNetworkInfo();
            return net != null && net.isConnected();
        }
    }

    /**
     * Activa el dispositivo si este se encuentra en reposo
     * se debe usar releaseWakeLock() para regresar su estado
     */
    public void acquireWakeLock() {
        //liberamos el estado
        releaseWakeLock();

        //activamos el servicio
        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        wakeLock = pm.newWakeLock(
                PowerManager.PARTIAL_WAKE_LOCK |
                        PowerManager.ACQUIRE_CAUSES_WAKEUP |
                        PowerManager.ON_AFTER_RELEASE, "WakeLock");
        wakeLock.acquire();
    }

    /**
     * Regresa el dispositivo a el modo reposo
     */
    public void releaseWakeLock() {
        if (wakeLock != null) wakeLock.release();
        wakeLock = null;
    }
}
