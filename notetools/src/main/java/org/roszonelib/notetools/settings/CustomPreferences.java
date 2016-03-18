package org.roszonelib.notetools.settings;


import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * ====================================
 * Proyecto : NotesaludR
 * Empresa  : Amedi S.a.s.
 * Autor    : Rosember Perez Mengual
 * Fecha    : 03/02/2016 17:15
 * ====================================
 */
public class CustomPreferences {

    private SharedPreferences mPreference;
    private Context mContext;
    private static String DEFAULT_PREFERENCES = "DefaultPreferences";

    public CustomPreferences(Context context, String preferenceName) {
        mContext = context;
        mPreference = mContext.getSharedPreferences(preferenceName, Context.MODE_PRIVATE);
    }

    public CustomPreferences(Context context, int idXmlPreference) {
        mContext = context;
        PreferenceManager.setDefaultValues(mContext, idXmlPreference, false);
        mPreference = PreferenceManager.getDefaultSharedPreferences(mContext);
    }

    public boolean getBooleanOrDefault(String key, boolean defaultValue) {
        return mPreference.getBoolean(key, defaultValue);
    }

    public boolean getBooleanOrDefault(int key, boolean defaultValue) {
        return getBooleanOrDefault(mContext.getString(key), defaultValue);
    }

    public Integer getIntegerOrDefault(String key, Integer defaultValue) {
        return mPreference.getInt(key, defaultValue);
    }

    public String getStringOrDefault(int key, String defaultValue) {
        return getStringOrDefault(mContext.getString(key), defaultValue);
    }

    public String getStringOrDefault(String key, String defaultValue) {
        return mPreference.getString(key, defaultValue);
    }

    public Integer getIntegerOrDefault(int key, Integer defaultValue) {
        return getIntegerOrDefault(mContext.getString(key), defaultValue);
    }

    public void putString(String key, String value) {
        SharedPreferences.Editor editor = mPreference.edit();
        editor.putString(key, value);
        editor.apply();
    }


    public void putString(int key, String value) {
        putString(mContext.getString(key), value);
    }

    public void putBoolean(String key, Boolean value) {
        SharedPreferences.Editor editor = mPreference.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public void putBoolean(int key, Boolean value) {
        putBoolean(mContext.getString(key), value);
    }

    public void putInteger(String key, Integer value) {
        SharedPreferences.Editor editor = mPreference.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public void putInteger(int key, Integer value) {
        putInteger(mContext.getString(key), value);
    }

    public static CustomPreferences newInstance(Context context) {
        return newInstance(context, DEFAULT_PREFERENCES);
    }

    public static CustomPreferences newInstance(Context context, String preferencename) {
        return new CustomPreferences(context, preferencename);
    }

}
