package com.gmailclone.utils_classes;

import android.content.Context;
import android.content.SharedPreferences;


import com.gmailclone.GmailCloneApplication;
import com.gmailclone.model.GmailUser;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class CommonPreferences {

    public static void setPreferences(String key, String value) {
        Context con = GmailCloneApplication.getContext();
        // save the data
        SharedPreferences preferences = con.getSharedPreferences("prefs_login", 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.commit();
    }
    public static GmailUser getUserData() {
        try{   GmailUser userData = new Gson().fromJson(CommonPreferences.getPreferences(AppConstants.SHARED_PREF_KEYS.USER_DATA).toString(), GmailUser.class);
            return userData;
        }
        catch (Exception e){
            return null;
        }
    }
    public static void setUserData(GmailUser gmailUser) {
        String user = new Gson().toJson(gmailUser);
        CommonPreferences.setPreferences(AppConstants.SHARED_PREF_KEYS.USER_DATA,user);

    }
    public static String getPreferences( String key) {
        Context con = GmailCloneApplication.getContext();
        /*if(con==null)
        {
            //Application Class Name Set
            con = ObjectPreference.getContext();
        }*/
        SharedPreferences sharedPreferences = con.getSharedPreferences("prefs_login", 0);
        String value = sharedPreferences.getString(key, null);
        return value;

    }

    public static void setPreferencesFilter( String key, String value) {
        Context con = GmailCloneApplication.getContext();
        // save the data
        SharedPreferences preferences = con.getSharedPreferences("prefs_filter", 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String getPreferencesFilter(String key) {
        Context con = GmailCloneApplication.getContext();
        SharedPreferences sharedPreferences = con.getSharedPreferences("prefs_filter", 0);
        return sharedPreferences.getString(key, "");

    }


    public static void clearPreferences() {
        Context con = GmailCloneApplication.getContext();
        con.getSharedPreferences("prefs_login", 0).edit().clear().commit();

    }
    public static void clearPreferencesFilter() {
        Context con = GmailCloneApplication.getContext();
        con.getSharedPreferences("prefs_filter", 0).edit().clear().apply();

    }

    public static void setPreferencesToken( String key, String value) {
        Context con = GmailCloneApplication.getContext();
        // save the data
        SharedPreferences preferences = con.getSharedPreferences("token", 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static String getPreferencesToken(String key) {
        Context con = GmailCloneApplication.getContext();
        SharedPreferences sharedPreferences = con.getSharedPreferences("token", 0);
        String value = sharedPreferences.getString(key, "0");
        return value;

    }

    public static void clearPreferencesToken() {
        Context con = GmailCloneApplication.getContext();
        con.getSharedPreferences("token", 0).edit().clear().commit();

    }

}
