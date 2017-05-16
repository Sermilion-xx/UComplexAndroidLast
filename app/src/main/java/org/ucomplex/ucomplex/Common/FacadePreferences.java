package org.ucomplex.ucomplex.Common;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.ucomplex.ucomplex.Domain.Users.InterfaceAdapter;
import org.ucomplex.ucomplex.Domain.Users.UserInterface;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 01/11/2016.
 * Project: uComplex_v_2
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public class FacadePreferences {

    private static final String KEY_PREF_TOKEN = "token";
    private static final String KEY_PREF_LOGGED_USER = "loggedUser";

    public static String getTokenFromPref(Context mContext) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(mContext);
        return pref.getString(KEY_PREF_TOKEN, "");
    }

    public static void setTokenToPref(Context mContext, String loginData, boolean async) {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(mContext).edit();
        editor.putString(KEY_PREF_TOKEN, loginData);
        if (async) {
            editor.apply();
        } else {
            editor.commit();
        }
    }

    public static UserInterface getUserDataFromPref(Context mContext) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(mContext);
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(UserInterface.class, new InterfaceAdapter());
        Gson gson = builder.create();
        String json = pref.getString(KEY_PREF_LOGGED_USER, "");
        return gson.fromJson(json, UserInterface.class);
    }

    private static SharedPreferences.Editor makeUserDataToPrefEditor(Context mContext, UserInterface user) {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(mContext).edit();
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(UserInterface.class, new InterfaceAdapter<UserInterface>());
        Gson gson = builder.create();
        String json = gson.toJson(user, UserInterface.class);
        editor.putString(KEY_PREF_LOGGED_USER, json);
        return editor;
    }

    public static void setUserDataToPrefSync(Context mContext, UserInterface user) {
        makeUserDataToPrefEditor(mContext, user).commit();
    }

    public static void clearPref(Context context){
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.clear();
        editor.apply();
    }

}
