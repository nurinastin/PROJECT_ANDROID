package com.project.sinisa.config;

import android.content.Context;
import android.content.SharedPreferences;

public class AuthData {
    private static AuthData mInstance;
    private static Context mCtx;
    private static final String SHARED_PREF_NAME = "sharedlogin";
    private static final String id_user = "kodeauth";
    private static final String nama_depan = "kodeuser";
    private static final String foto = "nama_user";
    private static final String lg = "lg";
    private AuthData(Context context){
        mCtx = context;
    }
    public static synchronized AuthData getInstance(Context context){
        if (mInstance == null){
            mInstance = new AuthData(context);
        }
        return mInstance;
    }
    public boolean setdatauser(String xid_user, String xnama_depan, String xfoto){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(id_user, xid_user);
        editor.putString(nama_depan, xnama_depan);
        editor.putString(foto, xfoto);
        editor.apply();

        return true;
    }
    public boolean setlg(String xlg){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(lg, xlg);
        editor.apply();

        return true;
    }
    public String getLg() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        return sharedPreferences.getString(lg, null);
    }
    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        if (sharedPreferences.getString(id_user, null) != null) {
            return true;
        }
        return false;
    }

    public String getId_user() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        return sharedPreferences.getString(id_user, null);
    }
    public String getNama_depan() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(nama_depan, null);
    }
    public String getFoto() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(foto, null);
    }
    public boolean logout(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        return true;
    }
}
