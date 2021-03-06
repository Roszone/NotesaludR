package com.amedidevelopment.notesaludr.controllers.device;

import android.content.Context;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.PowerManager;
import android.provider.Settings;
import android.support.multidex.MultiDexApplication;


/**
 * Autor:  Rosember Perez Mengual
 * Proyecto : NotesaludR
 * Fecha: 23/01/2016
 * Empresa : Amedi S.a.S.
 */
public class DeviceManager extends MultiDexApplication {
    private static DeviceManager mInstance;
    private PowerManager.WakeLock mWakelock;


    /**
     * Retorna la instancia unica del dispositivo
     */
    public static DeviceManager getInstance() {
        if (mInstance == null) {
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
     * Activa el dispositivo si este se encuentra en reposo
     * se debe usar releaseWakeLock() para regresar su estado
     */
    public void acquireWakeLock() {
        //liberamos el estado
        releaseWakeLock();

        //activamos el servicio
        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        mWakelock = pm.newWakeLock(
                PowerManager.PARTIAL_WAKE_LOCK |
                        PowerManager.ACQUIRE_CAUSES_WAKEUP |
                        PowerManager.ON_AFTER_RELEASE, "WakeLock");
        mWakelock.acquire();
    }

    /**
     * Regresa el dispositivo a el modo reposo
     */
    public void releaseWakeLock() {
        if (mWakelock != null) mWakelock.release();
        mWakelock = null;
    }

}
